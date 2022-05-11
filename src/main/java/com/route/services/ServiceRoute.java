package com.route.services;

import com.route.RouteApplication;
import com.route.controller.RouteController;
import com.route.util.CountryReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class ServiceRoute {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceRoute.class);
    private  static HashMap mapCountry;
    private Stack stack = new Stack();

    @Autowired
    private CountryReader countryReader;
    static
    {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(RouteApplication.class);
        CountryReader countryReader = ctx.getBean(CountryReader.class);
        try {
            countryReader.readCountries();
            mapCountry = countryReader.getMap();
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("error in reading country's json");
        }

    }

    public List<String> calculateRoute(String origin, String destination) {
        List<String> routes = new ArrayList<>();
        List<String> path  = processBorders(origin,destination);
        if(path.contains(destination))
            routes =path;
        stack.clear();
        return routes;
    }

    private List<String> processBorders(String origin, String destination) {
        List<String> arr = new ArrayList();
        List<String> originBorders = (List<String>) mapCountry.get(origin);
        List<String> destinationBorders = (List<String>) mapCountry.get(destination);
        if(destinationBorders.contains(origin)) {
            arr.add(origin);
            arr.add(destination);
            return arr;
        }
        if(!originBorders.isEmpty() && !destinationBorders.isEmpty()) {
            Set<String> intersect = new HashSet<String>((Collection<? extends String>) originBorders);
            intersect.retainAll((Collection<? extends String>) destinationBorders);
            stack.add(origin);
            arr.add(origin);
            if (!intersect.isEmpty()) {
                intersect.stream().forEach(elem -> {
                    arr.add(elem);
                });
                arr.add(destination);
                return arr;
            }else {
                for(String childOrign:originBorders) {
                    if(!stack.contains(childOrign)) {
                        List<String> tmp = processBorders(childOrign, destination);
                        if (!tmp.isEmpty()) {
                            arr.addAll(tmp);
                            return arr;
                        }
                    }
                }
            }
        }
        return arr;
    }

    public boolean checkValidity(String origin, String destination) {
        boolean isValid = false;
        if(mapCountry.containsKey(origin) && mapCountry.containsKey(destination))
            isValid = true;
        return isValid;
    }

}
