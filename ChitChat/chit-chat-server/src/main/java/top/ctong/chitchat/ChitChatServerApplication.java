package top.ctong.chitchat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "top.ctong.chitchat")
@MapperScan({"top.ctong.chitchat.**.mapper"})
public class ChitChatServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChitChatServerApplication.class, args);
    }

}
