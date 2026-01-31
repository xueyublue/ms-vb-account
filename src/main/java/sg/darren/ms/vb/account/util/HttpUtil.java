package sg.darren.ms.vb.account.util;

import sg.darren.ms.vb.account.model.http.HttpResponse;

public class HttpUtil {

    public static HttpResponse errorResponse(String errorCode) {
        return HttpResponse.builder()
                .status("ERROR")
                .errorCode(errorCode)
                .errorMessage(errorCode)
                .build();
    }
}
