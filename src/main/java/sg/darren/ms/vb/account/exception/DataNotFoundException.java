package sg.darren.ms.vb.account.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException(String message) {
        super(message);
    }

    public static DataNotFoundException accountNumberNotFound(String accountNumber) {
        return new DataNotFoundException(String.format("Account Number %s not found!", accountNumber));
    }

    public static DataNotFoundException accountNotActive(String accountNumber) {
        return new DataNotFoundException(String.format("Account Number %s not found!", accountNumber));
    }

}
