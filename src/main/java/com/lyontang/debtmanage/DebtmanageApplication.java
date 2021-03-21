package com.lyontang.debtmanage;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lyontang.debtmanage.mapper")
public class DebtmanageApplication {

    public static void main(String[] args) {
        SpringApplication.run(DebtmanageApplication.class, args);
    }

}
