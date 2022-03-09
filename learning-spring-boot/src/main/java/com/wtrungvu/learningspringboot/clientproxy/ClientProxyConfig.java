package com.wtrungvu.learningspringboot.clientproxy;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientProxyConfig {

    @Value("${users.api.url.v1}")
    private String userEndpointUrl;

    @Bean
    public UserResourceV1 getUserResourceV1() {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(userEndpointUrl);
        UserResourceV1 proxy = target.proxy(UserResourceV1.class);
        return proxy;
    }

}
