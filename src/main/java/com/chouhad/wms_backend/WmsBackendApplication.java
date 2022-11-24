package com.chouhad.wms_backend;

import com.chouhad.wms_backend.Entities.Client;
import com.chouhad.wms_backend.Repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class WmsBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(WmsBackendApplication.class, args);
	}

}
