package sg.darren.ms.vb.account.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidInputException extends RuntimeException {

	public InvalidInputException(String message) {
        super(message);
    }
}