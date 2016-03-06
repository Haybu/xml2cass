package com.repoc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by hmohamed on 3/6/16.
 */
@Configuration
@ConfigurationProperties(prefix = "mozenda10.service")
@Data
public class Mozenda10Properties {
    private String base;
    private String pagesize;
    private String getlist;
    private String getitem;
}
