package com.repoc.client;

import com.repoc.config.Mozenda10Properties;
import lombok.Data;
import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import javax.net.ssl.*;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;


/**
 * Created by hmohamed on 3/6/16.
 */
@Component
@Data
@Slf4j
public class Mozenda10Client {

    @Autowired
    Mozenda10Properties props;

    @Autowired
    RestTemplate template;

    @Autowired
    Mozenda10Converter converter;

    public Mozenda10CollectionXML getCollectionXML()  {
        try {
            log.info("Getting Mozinda Collections via URL:  " + props.getGetlist());
            doTrustToCertificates();
            String data = template.getForObject(props.getGetlist(), String.class);
            log.info("Successfully obtained Mozinda collections data. \n" + data);
            return converter.convertFromXMLToLawyerCollections(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Mozenda10ItemXML getItems(int viewId, int pageNumber) {
        String url = new String(props.getGetitem());
        url = url.replace("?1", viewId+"");
        url = url.replace("?2", pageNumber+"");

        try {
            log.info("Getting Mozinda Items via URL: " + url);
            doTrustToCertificates();
            String data = template.getForObject(url, String.class);
            log.info("Successfully obtained Mozinda items data. \n" + data);
            return converter.convertFromXMLToLawyerItems(data);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // trusting all certificate
    public void doTrustToCertificates() throws Exception {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
                        return;
                    }

                    public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
                        return;
                    }
                }
        };

        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        HostnameVerifier hv = new HostnameVerifier() {
            public boolean verify(String urlHostName, SSLSession session) {
                if (!urlHostName.equalsIgnoreCase(session.getPeerHost())) {
                    System.out.println("Warning: URL host '" + urlHostName + "' is different to SSLSession host '" + session.getPeerHost() + "'.");
                }
                return true;
            }
        };
        HttpsURLConnection.setDefaultHostnameVerifier(hv);
    }

}
