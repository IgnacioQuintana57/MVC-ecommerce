package com.backend.backend;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootTest
class BackendApplicationTests {

	@BeforeAll
	static void setUp() {
		Dotenv dotenv = Dotenv.configure().directory("src/main/resources").load();
		dotenv.entries().forEach(entry -> {
			System.setProperty(entry.getKey(), entry.getValue());
		});

	}

	@Test
	void contextLoads() {
	}

}
