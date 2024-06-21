package cn.hq.chatbot.api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//20240616 hq
//启动入口
@SpringBootApplication
@ComponentScan(basePackages = {"cn.hq.chatbot"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
