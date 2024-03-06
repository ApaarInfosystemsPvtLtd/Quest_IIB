package com.pmli.iib.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

@Configuration
public class IibConfig {

    @Value("${quest.iib.soap.endpoint}")
    private String questEndpointUrl;

    @Bean
    Jaxb2Marshaller jaxb2Marshaller() {
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setContextPath("com.pmli.iib.wsdlbean");
        return jaxb2Marshaller;
    }

    @Bean
    public WebServiceTemplate iibTemplate() {
        WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
        webServiceTemplate.setMarshaller(jaxb2Marshaller());
        webServiceTemplate.setUnmarshaller(jaxb2Marshaller());
        webServiceTemplate.setDefaultUri(questEndpointUrl);
        return webServiceTemplate;
    }
}
