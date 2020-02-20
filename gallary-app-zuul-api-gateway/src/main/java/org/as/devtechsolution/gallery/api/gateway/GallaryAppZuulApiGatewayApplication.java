package org.as.devtechsolution.gallery.api.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
@ComponentScan(basePackages= {"org.as.devtechsolution.gallery.api.gateway"})
public class GallaryAppZuulApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GallaryAppZuulApiGatewayApplication.class, args);
	}

}
