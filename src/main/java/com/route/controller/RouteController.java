package com.route.controller;



import com.route.RouteApplication;
import com.route.services.ServiceRoute;
import com.route.util.CountryReader;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;


@RestController

public class RouteController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RouteController.class);

    @Autowired
    private ServiceRoute serviceRoute;

    @RequestMapping(value = "/routing/{origin}/{destination}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getRoute(@PathVariable String origin, @PathVariable String destination) {
        JSONObject resp = new JSONObject();
        LOGGER.info("routing from {} to {}:",origin,destination);
        boolean isValid = serviceRoute.checkValidity(origin, destination);
        if(isValid) {
            List<String> route = serviceRoute.calculateRoute(origin,destination);
            if(!route.isEmpty()) {
                resp.put("routes", route);
                return new ResponseEntity<String>(resp.toString(), HttpStatus.CREATED);
            }else{
                return new ResponseEntity<String>("not land crossing between the chosen countries", HttpStatus.BAD_REQUEST);
            }
        }else {
            resp.put("result", origin.concat("-").concat(destination).concat(" is valid:").concat("" + isValid));
            return new ResponseEntity<String>(resp.toString(), HttpStatus.CREATED);
        }
    }




}
