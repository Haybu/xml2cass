package com.repoc.config;

import com.repoc.client.Mozenda10Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.oxm.castor.CastorMarshaller;
import org.springframework.web.client.RestTemplate;

/**
 * Created by hmohamed on 3/6/16.
 */
@Configuration
public class Mozenda10Config {

    @Bean
    public RestTemplate restTemplate () {
        return new RestTemplate();
    }

    @Bean
    public CastorMarshaller castorMarshaller() {
        Resource resource = new ClassPathResource("mapping.xml");
        CastorMarshaller marshaller = new CastorMarshaller();
        marshaller.setMappingLocation(resource);
        return marshaller;
    }

    @Bean
    @Autowired
    public Mozenda10Converter converter(CastorMarshaller castorMarshaller) {
        Mozenda10Converter converter = new Mozenda10Converter();
        converter.setMarshaller(castorMarshaller);
        converter.setUnMarshaller(castorMarshaller);
        return converter;
    }


}
