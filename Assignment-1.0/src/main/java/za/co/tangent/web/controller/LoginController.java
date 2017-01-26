/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.tangent.web.controller;

import java.nio.charset.Charset;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author F4829689
 */
@RestController
public class LoginController {

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String authenticate(@RequestBody LoginBean loginBean) {

        String url = "http://userservice.staging.tangentmicroservices.com:80/api-token-auth/";

        RestTemplate restTemplate = new RestTemplate();

// Create a multimap to hold the named parameters
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        parameters.add("username", loginBean.getUsername());
        parameters.add("password", loginBean.getPassword());


// Create the http entity for the request
        HttpEntity<MultiValueMap<String, String>> entity
                = new HttpEntity<MultiValueMap<String, String>>(parameters, createHeaders(loginBean.getUsername(), loginBean.getPassword()));
       // Get the response as a string
        String response = restTemplate.postForObject(url, entity, String.class);
        System.out.println(response);

        return response;
    }

    HttpHeaders createHeaders(final String username, final String password) {
        return new HttpHeaders() {
            {
                String auth = username + ":" + password;
                byte[] encodedAuth = Base64.encodeBase64(
                        auth.getBytes(Charset.forName("US-ASCII")));
                String authHeader = "Basic " + new String(encodedAuth);
                set("Authorization", authHeader);
            }
        };
    }

}
