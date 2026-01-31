package sg.darren.ms.vb.account.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AccountPostingRetryListener implements RetryListener {
    @Override
    public <T, E extends Throwable> boolean open(RetryContext context, RetryCallback<T, E> callback) {
        int retryCount = context.getRetryCount();
        if (retryCount > 0) {
            log.warn("retry count={}", context.getRetryCount());
        }
        return RetryListener.super.open(context, callback);
    }

    @Override
    public <T, E extends Throwable> void close(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
        int retryCount = context.getRetryCount();
        if (retryCount > 0) {
            log.warn("retry completed after max retry={}", context.getRetryCount());
        }
        RetryListener.super.close(context, callback, throwable);
    }
}
