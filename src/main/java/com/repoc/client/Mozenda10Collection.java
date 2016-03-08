package com.repoc.client;

import lombok.Data;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

/**
 * Created by hmohamed on 3/6/16.
 */
@Table("firms")
@Data
public class Mozenda10Collection {

    @PrimaryKeyColumn(name = "firmId", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private Integer collectionId;

    private String name;
    private String description;
    private Integer defaultViewId;
    private Integer agentId;
}
