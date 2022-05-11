package com.route.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;


import com.google.gson.Gson;

import com.google.gson.stream.JsonReader;
import com.route.model.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
//@Scope("singleton")
@PropertySource(value={"application.properties"})
public class CountryReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(CountryReader.class);
    @Value("${country.navigation}")
    private String serviceUrl;
    public  HashMap map = new HashMap();

    public  HashMap getMap() {
        return map;
    }

    public  void readCountries() throws IOException {
        LOGGER.info("===> Reading countries");
        try (
                InputStream inputStream = new URL(serviceUrl).openStream();
                JsonReader reader = new JsonReader(new InputStreamReader(inputStream));
        ) {
            reader.beginArray();
            reader.setLenient(true);
            while (reader.hasNext()) {
                Country country = new Gson().fromJson(reader, Country.class);
                map.put(country.getCca3(),country.getBorders());
            }
            reader.endArray();
        }

    }

/*
    public static void main(String[] args) throws IOException, JSONException {
        CountryReader cr = new CountryReader();

        Set<String> intersect = new HashSet<String>((Collection<? extends String>) cr.getMap().get("ITA"));
        intersect.retainAll((Collection<? extends String>) cr.getMap().get("FRA"));
        System.out.println(intersect.size()); // prints "2"
        System.out.println(intersect);


    }

 */

}

