package lk.mobility.rapidapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;

@Controller

public class MainController {

    @RequestMapping(value = {
            "",
            "/",
            "/**"

    },method = RequestMethod.POST)
    public ResponseEntity<?> controller(HttpServletRequest request, @RequestBody String str) {
        Gson gson = new GsonBuilder().create();

        String test = request.getRequestURI();

        String path = (String) request.getAttribute(
                HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        String bestMatchPattern = (String ) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);

        AntPathMatcher apm = new AntPathMatcher();
        String finalPath = apm.extractPathWithinPattern(bestMatchPattern, path);


        JsonObject jsonObject = (JsonObject) new JsonParser().parse(str);

        JsonObject requst = jsonObject.get("body").getAsJsonObject();
        Integer response_code = jsonObject.get("response_code").getAsInt();
        JsonObject responseObj = jsonObject.get("response").getAsJsonObject();

       HttpStatus httpStatus = HttpStatus.valueOf(response_code);


        System.out.println(test);
        System.out.println("Reqst : "+requst);
        System.out.println("Response : "+responseObj);
        System.out.println(httpStatus);

        return new ResponseEntity <JsonObject> (responseObj, httpStatus);
    }
}
