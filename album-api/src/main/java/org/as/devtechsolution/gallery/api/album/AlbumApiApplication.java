package org.as.devtechsolution.gallery.api.album;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AlbumApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlbumApiApplication.class, args);
	}

}
