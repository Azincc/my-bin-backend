package cc.azin.atools;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author azin
 */
@SpringBootApplication
@Slf4j
public class AToolsApplication {
    public static void main(String[] args) {
        SpringApplication.run(AToolsApplication.class, args);
        log.info("SwaggerUi: http://localhost:8080/swagger-ui/index.html");
    }
}