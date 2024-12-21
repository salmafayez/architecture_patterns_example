package com.charity_hub.application;

import com.charity_hub.domain.contracts.*;
import com.charity_hub.domain.models.account.Tokens;
import com.charity_hub.domain.models.account.Account;
import com.charity_hub.domain.exceptions.AppException;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

//TODO this is an application class that handles multiple calls (orchestrate the flow)
//TODO is it right to depend on multiple contracts like that or just on output port is enough
@Service
public class AuthenticateHandler {

    private final IAccountRepo accountRepo;

    private final IInvitationRepo invitationRepo;

    private final IAuthProvider authProvider;

    private final IJWTGenerator jwtGenerator;

    private final ILogger logger;

    public AuthenticateHandler(
            IAccountRepo accountRepo,
            IInvitationRepo invitationRepo,
            IAuthProvider authProvider,
            IJWTGenerator jwtGenerator, ILogger logger
    ) {
        this.accountRepo = accountRepo;
        this.invitationRepo = invitationRepo;
        this.authProvider = authProvider;
        this.jwtGenerator = jwtGenerator;
        this.logger = logger;
    }

    public CompletableFuture<AuthenticateResponse> handle(Authenticate command) {
        return CompletableFuture.supplyAsync(() -> {
            logger.log("Handling authentication for idToken: {}" + command.idToken());

            String mobileNumber = authProvider.getVerifiedMobileNumber(command.idToken()).join();

            logger.log("check for account: {}" + command);

            Account account = existingAccountOrNewAccount(mobileNumber, command);

            logger.log(" finish check for account: {}" + command.idToken());

            //TODO is this actually the account responsibility
            //TODO is it better to send the whole object or the needed parameters
            Tokens tokens = account.authenticate(jwtGenerator, command.deviceId(), command.deviceType());

            accountRepo.save(account);
            logger.log("Authentication successful for account: {}" + account.getMobileNumber());

            return new AuthenticateResponse(tokens.accessToken(), tokens.refreshToken());
        });
    }

    private Account existingAccountOrNewAccount(String mobileNumber, Authenticate request) {
        logger.log("get the account by mobile number");
        var account = accountRepo.getByMobileNumber(mobileNumber).join();
        if (accountExists(account)) {
            return account;
        }
        return createNewAccount(mobileNumber, request.deviceType(), request.deviceId());
    }

    private static boolean accountExists(Account existingAccount) {
        return existingAccount != null;
    }

    //TODO why you use a before the parameter name
    private Account createNewAccount(String mobileNumber, String aDeviceType, String aDeviceId) {
        boolean isAdmin = accountRepo.isAdmin(mobileNumber).join();
        boolean hasNoInvitations = !invitationRepo.hasInvitation(mobileNumber).join();
        assertIsAdminOrInvited(mobileNumber, isAdmin, hasNoInvitations);

        return Account.newAccount(mobileNumber, isAdmin, aDeviceType, aDeviceId);
    }

    private void assertIsAdminOrInvited(String mobileNumber, boolean isAdmin, boolean hasNoInvitations) {
        if (!isAdmin && hasNoInvitations) {
            logger.warn("Account not invited: {}" + mobileNumber);
            throw new AppException.RequirementException("Account not invited to use the App");
        }
    }

    //TODO is passing the account model indicator that should be inside the model itself
    private Tokens getTokens(Account account, String deviceId, String deviceType) {
        var usedDevice = account.usedDevice(deviceId, deviceType);

        var accessToken = jwtGenerator.generateAccessToken(account, usedDevice);

        var refreshToken = jwtGenerator.generateRefreshToken(account, usedDevice);
        usedDevice.updateRefreshToken(refreshToken);

        return new Tokens(refreshToken, accessToken);
    }
}