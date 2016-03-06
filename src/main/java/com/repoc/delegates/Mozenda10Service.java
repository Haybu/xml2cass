package com.repoc.delegates;

import com.repoc.client.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hmohamed on 3/6/16.
 */
@Service
@Slf4j
public class Mozenda10Service {

    @Autowired
    Mozenda10Client client;

    @Autowired
    FirmsRepository firmsRepository;

    @Autowired
    ProfilesRepository profilesRepository;

    public void retrieveLawyers() throws Exception {
        Mozenda10CollectionXML collectionXML = client.getCollectionXML();

        List<Mozenda10Collection> collections = collectionXML.getCollectionList();

        int pageNumber = 1;
        int pageCount = 0;
        for(Mozenda10Collection collection: collections) {
            log.info("processing collection with view Id: " + collection.getDefaultViewId());
            processCollection(collection);
            do {
                Mozenda10ItemXML itemsXML = client.getItems(collection.getDefaultViewId(), pageNumber);
                if (itemsXML == null) {
                    continue;
                }
                pageNumber = itemsXML.getPageNumber();
                pageCount = itemsXML.getPageCount();  // always one value
                if (itemsXML.getItemList() == null || itemsXML.getItemList().size() ==0) {
                    continue;
                }
                itemsXML.getItemList().stream().forEach(item -> {
                    processItem(item, collection.getCollectionId());
                });
            } while (pageNumber < pageCount);

        }
    }

    private void processCollection(Mozenda10Collection collection) {
        log.info("saving a firm (id: " + collection.getCollectionId() + ")");
        firmsRepository.save(collection);
    }

    private void processItem(Mozenda10Item item, int collectionId) {
        item.setFirmId(collectionId);
        log.info("saving a profile (id: " + item.getItemId() + ", firmId: " + collectionId + ")");
        profilesRepository.save(item);
    }

    private void printItem(Mozenda10Item item){
        StringBuffer buf = new StringBuffer("Lawyer:\n");
        buf.append("itemId: "+ item.getItemId() + "\n");
        buf.append("locationUrl: "+ item.getLocationUrl() + "\n");
        buf.append("name: "+ item.getName() + "\n");
        buf.append("firstName: "+ item.getFname() + "\n");
        buf.append("middleName: "+ item.getMname() + "\n");
        buf.append("lastName: "+ item.getLname() + "\n");
        buf.append("position: "+ item.getPosition() + "\n");
        buf.append("office: "+ item.getOffice() + "\n");

        log.info(buf.toString());
    }


}
