package com.repoc.client;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Component;
import org.xml.sax.InputSource;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

/**
 * Created by hmohamed on 3/6/16.
 */
@Data
@Slf4j
public class Mozenda10Converter {

    private Marshaller marshaller;
    private Unmarshaller unMarshaller;

    public Mozenda10CollectionXML convertFromXMLToLawyerCollections(String xml) {

        try{
            Source xmlInput = new StreamSource(new StringReader(xml));
            return (Mozenda10CollectionXML) unMarshaller.unmarshal(xmlInput);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public Mozenda10ItemXML convertFromXMLToLawyerItems(String xml) {

        try{
            Source xmlInput = new StreamSource(new StringReader(xml));
            return (Mozenda10ItemXML) unMarshaller.unmarshal(xmlInput);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

}
