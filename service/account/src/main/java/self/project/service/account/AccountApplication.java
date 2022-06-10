package self.project.service.account;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yuanchangshuai
 * @date 2022/6/10-11:19
 * @description
 */
@Slf4j
@SpringBootApplication
public class AccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
        log.info("Account服务启动");
    }
}
