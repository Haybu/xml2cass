package com.repoc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

/**
 * Created by hmohamed on 3/6/16.
 */
@Configuration
@EnableCassandraRepositories(basePackages = {"com.repoc.client", "com.repoc.delegates"})
public class CassandraConfig {
}
