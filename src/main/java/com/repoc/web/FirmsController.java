package com.repoc.web;

import com.google.common.collect.Iterables;
import com.repoc.client.Mozenda10Collection;
import com.repoc.client.Mozenda10Item;
import com.repoc.delegates.Mozenda10Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hmohamed on 3/7/16.
 */
@RestController
@Slf4j
public class FirmsController {

    @Autowired
    Mozenda10Service service;

    @RequestMapping("/firms")
    public ResponseEntity<Iterable<Mozenda10Collection>> allfirms(){
        log.info("getting all firms");
        return new ResponseEntity<Iterable<Mozenda10Collection>>(service.getAllFirms(), HttpStatus.OK);
    }

    @RequestMapping("/firms/{firmId}")
    public ResponseEntity<Mozenda10Collection> OneFirm(@PathVariable("firmId") Integer firmId) {
        log.info("getting firm with id : " + firmId);
        return new ResponseEntity<Mozenda10Collection>(service.getOneFirm(firmId), HttpStatus.OK);
    }

    @RequestMapping("/firms/{firmId}/lawyers")
    public ResponseEntity<Iterable<Mozenda10Item>> allLawyers(@PathVariable("firmId") Integer firmId){
        log.info("getting all lawyers for firm with id : " + firmId);
        return new ResponseEntity<Iterable<Mozenda10Item>>(service.getLawyers(firmId), HttpStatus.OK);
    }

    @RequestMapping("/firms/{firmId}/lawyers/{profileId}")
    public ResponseEntity<Mozenda10Item> OneLawyer(@PathVariable("firmId") Integer firmId
            , @PathVariable("profileId") Integer profileId)
    {
        log.info("getting a lawyer for firm with id : " + firmId + " and lawyer with id : " + profileId);
        return new ResponseEntity<Mozenda10Item>(service.getOneLawyer(firmId, profileId), HttpStatus.OK);
    }

    @RequestMapping(value = "/firms/download")
    public ResponseEntity<String> download() {
        service.retrieveLawyers();
        return new ResponseEntity<String>("data is downloading ...", HttpStatus.OK);
    }
}
