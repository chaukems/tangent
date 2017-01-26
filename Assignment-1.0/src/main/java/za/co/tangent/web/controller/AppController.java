package za.co.tangent.web.controller;

import java.nio.charset.Charset;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import za.co.tangent.service.MyUserDetailsModel;

@Controller
public class AppController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView welcomePage() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String token = ((MyUserDetailsModel) principal).getToken();

        /*
         "pk": 47,
         "title": "Tangent Solutions",
         "description": "Solutions",
         "start_date": "2017-01-23",
         "end_date": "2017-02-23",
         "is_billable": true,
         "is_active": true,
         */
        String url = "http://projectservice.staging.tangentmicroservices.com:80/api/v1/projects/";

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        parameters.add("content-type", "application/json");
        parameters.add("Authorization", "Token " + token);
        RestTemplate restTemplate = new RestTemplate();
       
        HttpEntity<MultiValueMap<String, String>> entity
                = new HttpEntity<MultiValueMap<String, String>>(parameters, createHeaders(token));
        
        Object result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        System.out.println("projects = " + result);

        ModelAndView model = new ModelAndView();
        model.addObject("projectsList", result);
        model.setViewName("welcome");
        return model;
    }

    HttpHeaders createHeaders(final String token) {
        return new HttpHeaders() {
            {               
                byte[] encodedAuth = Base64.encodeBase64(
                        token.getBytes(Charset.forName("US-ASCII")));
                String authHeader = "Token " + new String(encodedAuth);
                set("Authorization", authHeader);
            }
        };
    }

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login");

        return model;
    }

}
