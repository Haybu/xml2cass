package com.repoc;

import com.repoc.web.Mozenda10Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Xml2cassApplication implements CommandLineRunner {

	@Autowired
	Mozenda10Service service;

	public static void main(String[] args) {
		SpringApplication.run(Xml2cassApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		service.retrieveLawyers();
	}
}
