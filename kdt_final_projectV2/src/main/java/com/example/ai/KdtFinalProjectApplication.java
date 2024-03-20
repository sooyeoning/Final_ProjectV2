package com.example.ai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = {"travelspot", "User", "controller","community","FAQ", "errors"})
@MapperScan(basePackages = {"travelspot"})
@EnableScheduling //스케줄링 활성화
public class KdtFinalProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(KdtFinalProjectApplication.class, args);
		
	}

}
