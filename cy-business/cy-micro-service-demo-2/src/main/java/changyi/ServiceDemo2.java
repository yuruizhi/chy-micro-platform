package changyi;

import com.changyi.cloud.dispose.starter.annotation.EnableGlobalDispose;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableGlobalDispose
public class ServiceDemo2 {

    public static void main(String[] args) {
        SpringApplication.run(ServiceDemo2.class, args);
    }
}
