package com.untucapital.usuite.utg.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.untucapital.usuite.utg.controller.MusoniController;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
@Getter
@Setter
@AllArgsConstructor
public class MusoniSyncClientsService {
    @Autowired
    private static final String DB_URL = "${spring.datasource.url}";
    @Autowired
    private static final String USER = "${spring.datasource.username}";
    @Autowired
    private static final String PASS = "${spring.datasource.password}";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    MusoniController musoniController;

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JsonNode readJsonFromUrl(String url) throws IOException {
        InputStream is = new URL(url).openStream();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readTree(is);
        } finally {
            is.close();
        }
    }

    public static JsonNode CMSreadJsonFromUrl(String url) throws IOException {
        InputStream is = new URL(url).openStream();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readTree(is);
        } finally {
            is.close();
        }
    }

    public static JsonNode MobilereadJsonFromUrl(String url) throws IOException {
        InputStream is = new URL(url).openStream();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readTree(is);
        } finally {
            is.close();
        }
    }
//    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
//        InputStream is = new URL(url).openStream();
//        try {
//            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
//            String jsonText = readAll(rd);
//            JSONObject json = new JSONObject(jsonText);
//            return json;
//        } finally {
//            is.close();
//        }
//    }
//
//    public static JSONArray CMSreadJsonFromUrl(String url) throws IOException, JSONException {
//        InputStream is = new URL(url).openStream();
//        try {
//            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
//            String jsonText = readAll(rd);
//            JSONArray CMSjson = new JSONArray(jsonText.toString());
//
//            return CMSjson;
//        } finally {
//            is.close();
//        }
//    }
//
//    public static JSONObject MobilereadJsonFromUrl(String url) throws IOException, JSONException {
//        InputStream is = new URL(url).openStream();
//        try {
//            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
//            String jsonText = readAll(rd);
//            JSONObject Mobjson = new JSONObject(jsonText);
//
//            return Mobjson;
//        } finally {
//            is.close();
//        }
//    }


    public static void main(String[] args) throws IOException {
        List<String> PhoneArray = new ArrayList<String>();
        List<Integer> CMSPhoneArray = new ArrayList<Integer>();

        // Replace the CMS JSON parsing with Jackson
        JsonNode CMSjson = readJsonFromUrl("http://localhost:7878/api/utg/users");

        for (JsonNode jsonObject : CMSjson) {
            JsonNode ContactDetail = jsonObject.get("contactDetail");
            String CMSphone = ContactDetail.get("mobileNumber").asText();
            CMSPhoneArray.add(Integer.valueOf(CMSphone));
            System.out.println("From CMS  " + CMSphone);
        }

        JsonNode json = readJsonFromUrl("http://localhost:7878/api/utg/musoni/clients");
        JsonNode pageItems = json.get("pageItems");

        for (JsonNode page : pageItems) {
            int ClientID = page.get("id").asInt();
            String PhoneNumber = page.get("mobileNo").asText();
            PhoneArray.add(PhoneNumber);

            List<Integer> arr = CMSPhoneArray;
            int searchValue = Integer.parseInt(PhoneNumber);
            boolean found = false;

            for (int a = 0; a < arr.size(); a++) {
                if (arr.get(a) == searchValue) {
                    found = true;
                    break;
                }
            }

            if (found) {
                System.out.println("The value " + searchValue + " was found in the array.");

                // Continue with the rest of your logic
            } else {
                System.out.println("The value " + searchValue + " was not found in the array.");
            }
        }
    }
}
