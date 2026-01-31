package sg.darren.ms.vb.account.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DataDuplicateException extends RuntimeException {

    public DataDuplicateException(String message) {
        super(message);
    }
}
