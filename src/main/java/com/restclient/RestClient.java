package com.restclient;

import com.google.gson.Gson;
import com.model.GeneratedPower;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Base64;

/**
 * Created by madhawag on 3/6/15.
 */
public class RestClient {
    Gson gson = new Gson();

    public void sendPostRequestToServer(double electricalEnergy, double voltage, double current, double frequency) {
        String url = "http://10.2.2.132:8000/api/v1/mesuments/?format=json";
        String name = "eranga";
        String password = "123";

        String authString = name + ":" + password;
        System.out.println("auth string: " + authString);
        byte[] authEncBytes = Base64.getEncoder().encode(authString.getBytes());
        String authStringEnc = new String(authEncBytes);

        GeneratedPower generatedPower = new GeneratedPower();
        generatedPower.setCurrent(current);
        generatedPower.setFrequency(frequency);
        generatedPower.setKwh(electricalEnergy);
        generatedPower.setVoltage(voltage);

        // convert java object to JSON format,
        // and returned as JSON formatted string
        String json = gson.toJson(generatedPower);

        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        //newly
        post.setHeader("Authorization", "Basic " + authStringEnc);

        // add header
        String USER_AGENT = "PowerMonitorUser";
        post.setHeader("User-Agent", USER_AGENT);

        try {
            StringEntity postingString = new StringEntity(json);
            post.setEntity(postingString);
            post.setHeader("Content-type", "application/json");

            HttpResponse response = client.execute(post);
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("Response Code : "
                    + statusCode);
            //Request to server has become successful
            if (statusCode == 201) {
                System.out.println("Data sent to REST API");
            }
//            BufferedReader rd = new BufferedReader(
//                    new InputStreamReader(response.getEntity().getContent()));
//
//            StringBuffer result = new StringBuffer();
//            String line = "";
//            while ((line = rd.readLine()) != null) {
//                result.append(line);
//            }

            String content = EntityUtils.toString(response.getEntity());
            System.out.println(content);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
}
