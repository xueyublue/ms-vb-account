package sg.darren.ms.vb.account.model.http;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HttpResponse {

    String status;

    String errorCode;

    String errorMessage;

}
