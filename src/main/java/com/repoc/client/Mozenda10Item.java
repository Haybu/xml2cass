package com.repoc.client;

import lombok.Data;
import org.springframework.cassandra.core.Ordering;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import java.util.Date;

/**
 * Created by hmohamed on 3/6/16.
 */
@Table("profiles")
@Data
public class Mozenda10Item {

    @PrimaryKeyColumn(name = "firmId", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private Integer firmId;

    @PrimaryKeyColumn(name = "profileId", ordinal = 1, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private Integer itemId;

    private String locationUrl;
    private String name;
    private String nameOrder;
    private String fname;
    private String lname;
    private String mname;
    private String position;
    private String office;
    private String phone;
    private String biography;
    private String practiceAreas;
    private String email;
    private String education;
    private String jdyear;
    private String lawyerImageUrl;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String officePhone;
    private Date created;
    private String createdBy;
    private Integer itemId1;
    private Date modified;
    private String modifiedBy;
    private Date refreshed;
    private String refreshedBy;
    private Integer itemSourceItemID;
    private String itemSourceName;
    private String itemSourceType;
}
