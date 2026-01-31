package sg.darren.ms.vb.account.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ApplicationException extends RuntimeException {

    public ApplicationException(String message) {
        super(message);
    }

    public static ApplicationException retryFailed(String uniqueReferenceNo) {
        return new ApplicationException(String.format("Balance update failed after max retried! uniqueReferenceNo=%s", uniqueReferenceNo));
    }

}
