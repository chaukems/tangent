/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.tangent.web.controller;

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
public class ProjectsController {

    @RequestMapping(value = "/listProjects", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String getProjects(@RequestBody String token) {

        String url = "http://projectservice.staging.tangentmicroservices.com:80/api/v1/projects/";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("content-type", "application/json");
        params.add("Authorization", "Token " + token);
        RestTemplate restTemplate = new RestTemplate();

        String result = restTemplate.getForObject(url, String.class, params);
        System.out.println(result);

        return result;
    }
}
