package cc.azin.pastebin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AToolsApplication {
  private static final Logger LOGGER = LoggerFactory.getLogger(AToolsApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(AToolsApplication.class, args);
    // SwaggerUi记录日志
    LOGGER.info("SwaggerUi: http://localhost:8080/swagger-ui/index.html");
  }
}