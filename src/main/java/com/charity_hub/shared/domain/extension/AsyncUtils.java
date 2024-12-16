package com.charity_hub.shared.domain.extension;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

@SuppressWarnings({"unused", "VisibilityModifier"})
public final class AsyncUtils {

    private static final ExecutorService DEFAULT_EXECUTOR = Executors.newCachedThreadPool();

    private AsyncUtils() {
        // Private constructor to prevent instantiation
    }

    /**
     * Executes a block asynchronously in a new thread pool.
     * <p>
     * Don't use it inside an existing CompletableFuture unless you need to execute
     * async block outside from this Future.
     * <p>
     * Example usage:
     * <pre>
     * {@code
     * // Execute multiple async operations
     * CompletableFuture<String> future1 = doAsync(() -> block1());
     * CompletableFuture<String> future2 = doAsync(() -> block2());
     *
     * // Wait for both results
     * String result = future1.thenCombine(future2, (r1, r2) -> r1 + r2).join();
     * }
     * </pre>
     *
     * @param task the code to be executed asynchronously
     * @return CompletableFuture representing the async operation
     */
    public static <T> CompletableFuture<T> doAsync(Supplier<T> task) {
        return CompletableFuture.supplyAsync(task);
    }

    /**
     * Executes a block asynchronously in a new thread pool with a custom executor.
     *
     * @param task     the code to be executed asynchronously
     * @param executor the custom executor service to use
     * @return CompletableFuture representing the async operation
     */
    public static <T> CompletableFuture<T> doAsync(Supplier<T> task, Executor executor) {
        return CompletableFuture.supplyAsync(task, executor);
    }

    /**
     * Executes a runnable block asynchronously in a new thread pool.
     *
     * @param task the code to be executed asynchronously
     * @return CompletableFuture<Void> representing the async operation
     */
    public static CompletableFuture<Void> doAsync(Runnable task) {
        return CompletableFuture.runAsync(task, DEFAULT_EXECUTOR);
    }

    /**
     * Executes a runnable block asynchronously with a custom executor.
     *
     * @param task     the code to be executed asynchronously
     * @param executor the custom executor service to use
     * @return CompletableFuture<Void> representing the async operation
     */
    public static CompletableFuture<Void> doAsync(Runnable task, Executor executor) {
        return CompletableFuture.runAsync(task, executor);
    }

    /**
     * Shuts down the default executor service.
     * Should be called when the application is shutting down.
     */
    public static void shutdown() {
        DEFAULT_EXECUTOR.shutdown();
    }
}