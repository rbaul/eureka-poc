package com.example.demo;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;
import com.netflix.eureka.EurekaServerContextHolder;
import com.netflix.eureka.registry.PeerAwareInstanceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@EnableEurekaServer
@SpringBootApplication
public class DemoApplication {

    @Autowired
    private PeerAwareInstanceRegistry peerAwareInstanceRegistry;

    @Autowired
    private EurekaClient eurekaClient;

//    @Autowired
//    private DiscoveryClient discoveryClient;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

    @RequestMapping("/home")
    public String home() {
	    eurekaClient.registerEventListener(event -> {
            System.out.println(">>>>>>>>>>>" + event);
        });


        PeerAwareInstanceRegistry registry = EurekaServerContextHolder.getInstance().getServerContext().getRegistry();
        Applications applications = registry.getApplications();

        applications.getRegisteredApplications().forEach((registeredApplication) -> {
            registeredApplication.getInstances().forEach((instance) -> {
                System.out.println(instance.getAppName() + " (" + instance.getInstanceId() + ") : " + instance.getHostName());
            });
        });
        Application client = peerAwareInstanceRegistry.getApplications().getRegisteredApplications("client");
        List<InstanceInfo> instances = client.getInstances();
        instances.forEach(instanceInfo -> {
            System.out.println(instanceInfo.getHostName());
        });

        return "Hello world";
    }

}
