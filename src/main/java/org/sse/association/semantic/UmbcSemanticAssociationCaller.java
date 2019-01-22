package org.sse.association.semantic;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;

public class UmbcSemanticAssociationCaller extends SemanticAssociationCaller {
    
    public Double getSemanticSimilarity(String para1, String para2) {

        String uri = "http://swoogle.umbc.edu/StsService/GetStsSim";

        try {

            HttpClient client = HttpClients.createDefault();

            URIBuilder builder = new URIBuilder(uri);

            builder.addParameter("operation", "api");
            builder.addParameter("phrase1", para1);
            builder.addParameter("phrase2", para2);

            String listStubsUri = builder.build().toString();
            HttpGet getStubMethod = new HttpGet(listStubsUri);


            HttpResponse getStubResponse = client.execute(getStubMethod);

            String responseBody = EntityUtils.toString(getStubResponse.getEntity());

            return (Double.parseDouble(responseBody.toString()));

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
