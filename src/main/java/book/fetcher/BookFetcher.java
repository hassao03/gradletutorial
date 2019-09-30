package book.fetcher;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

public class BookFetcher {

    private String endpointURL;
    private HttpClient client;

    public BookFetcher(String endpointURL) {
        this.endpointURL = endpointURL;
        this.client = HttpClientBuilder.create().build();
    }

    public String getFromEndpoint(String id) throws IOException {
        // Build request
        String url = endpointURL + "/books/" + id;
        HttpGet request = new HttpGet(url);

        // Make request
        HttpResponse response = client.execute(request);


        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }


        // Return response body as string
        return result.toString();
        //return response.getStatusLine().getStatusCode();

    }

    public String getAllFromEndpoint() throws IOException {
        // Build request
        String url = endpointURL + "/books/all";
        HttpGet request = new HttpGet(url);

        // Make request
        HttpResponse response = client.execute(request);

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        // Return response body as string
        return result.toString();
    }

}
