package be.technifutur.spring.demo;

import be.technifutur.spring.demo.other.DemoIOC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class SpringDemoApplication {

	public static void main(String[] args) {
		ApplicationContext ctxt = SpringApplication.run(SpringDemoApplication.class, args);
//		Scanner scanner = ctxt.getBean(Scanner.class);
	}


}
