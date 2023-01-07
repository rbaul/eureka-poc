package com.example.demo;

import com.netflix.appinfo.InstanceInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRenewedEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaRegistryAvailableEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaServerStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Slf4j
@Component
public class EurekaStateChangeListener {
    /**
           * EurekaInstanceCanceledEvent service offline event
     * @param event
     */
    @EventListener
    public void listen(EurekaInstanceCanceledEvent event) {
		log.info(MessageFormat.format("{0} {1} Service offline", event.getServerId(), event.getAppName()));
    }
 
    /**
           * EurekaInstanceRegisteredEvent service registration event
     * @param event
     */
    @EventListener
    public void listen(EurekaInstanceRegisteredEvent event) {
        InstanceInfo instanceInfo = event.getInstanceInfo();
		log.info(MessageFormat.format("{0} register", instanceInfo.getAppName()));
    }
 
    /**
           * EurekaInstanceRenewedEvent service renewal event
     * @param event
     */
    @EventListener
    public void listen(EurekaInstanceRenewedEvent event) {
		log.info(MessageFormat.format("{0} {1} Service Renewal", event.getServerId(), event.getAppName()));
    }
 
    /**
           * EurekaRegistryAvailableEvent Eureka Registration Center Launch Event
     * @param event
     */
    @EventListener
    public void listen(EurekaRegistryAvailableEvent event) {
		log.info("Registration Startup");
    }
 
    /**
           * EurekaServerStartedEvent Eureka Server startup event
     * @param event
     */
    @EventListener
    public void listen(EurekaServerStartedEvent event) {
		log.info("Eureka Server Startup");
    }
}