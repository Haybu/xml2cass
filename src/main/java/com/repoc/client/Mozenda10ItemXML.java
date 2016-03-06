package com.repoc.client;

import lombok.Data;

import java.util.List;

/**
 * Created by hmohamed on 3/6/16.
 */
@Data
public class Mozenda10ItemXML {

    private String result;
    private int pageItemCount;
    private int pageCount;
    private int pageNumber;

    List<Mozenda10Item> itemList;
}
