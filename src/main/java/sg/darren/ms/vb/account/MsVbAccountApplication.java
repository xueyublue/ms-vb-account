package sg.darren.ms.vb.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
public class MsVbAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsVbAccountApplication.class, args);
    }

}
