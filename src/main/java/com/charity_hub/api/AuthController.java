package com.charity_hub.api;

import com.charity_hub.api.common.DeferredResults;
import com.charity_hub.application.Authenticate;
import com.charity_hub.application.AuthenticateHandler;
import com.charity_hub.domain.contracts.ILogger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

//TODO why there is no package called controllers
//TODO why you don't use @Autowired
@RestController
public class AuthController {

    private final AuthenticateHandler authenticateHandler;

    private final ILogger log;

    public AuthController(AuthenticateHandler authenticateHandler, ILogger log) {
        this.authenticateHandler = authenticateHandler;
        this.log = log;
    }

    //TODO there is no data validation on the input from the endpoint
    @PostMapping("/v1/accounts/authenticate")
    public DeferredResult<ResponseEntity<?>> login(@RequestBody Authenticate authenticate) {
        log.log("Processing authentication request");
        return DeferredResults.from(
                authenticateHandler.handle(authenticate)
                        .thenApply(ResponseEntity::ok)
        );
    }
}

