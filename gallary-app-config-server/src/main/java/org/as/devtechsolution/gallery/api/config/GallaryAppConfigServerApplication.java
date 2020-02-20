package org.as.devtechsolution.gallery.api.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class GallaryAppConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GallaryAppConfigServerApplication.class, args);
	}

}
