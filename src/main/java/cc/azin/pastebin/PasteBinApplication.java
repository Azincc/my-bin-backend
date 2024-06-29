package cc.azin.pastebin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class PasteBinApplication {
  public static void main(String[] args) {
    SpringApplication.run(PasteBinApplication.class, args);
  }
}
