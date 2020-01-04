package org.as.devtechsolution.gallery.servicediscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class GallaryAppDiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GallaryAppDiscoveryServiceApplication.class, args);
	}

}
