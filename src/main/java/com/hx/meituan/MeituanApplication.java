package com.hx.meituan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan("com.hx")
@MapperScan("com.hx.meituan.mapper")
@ServletComponentScan // 自动扫描@WebServlet、@WebFilter、@WebListener
public class MeituanApplication {

  public static void main(String[] args) {
    SpringApplication.run(MeituanApplication.class, args);
  }

}
