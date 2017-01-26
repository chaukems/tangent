/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.tangent.web.controller;

import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author F4829689
 */
@RestController
public class ProjectsController {

    @RequestMapping(value = "/listUsers", method = RequestMethod.GET)
    public @ResponseBody
    List listUsers() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/SpringServiceJsonSample/service/user/";

        List<LinkedHashMap> users = restTemplate.getForObject(url, List.class);

        return users;
    }

    @RequestMapping(value = "/dispUser/{userid}", method = RequestMethod.GET)
    public @ResponseBody
    ModelAndView dispUser(@PathVariable("userid") int userid) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/SpringServiceJsonSample/service/user/{userid}";
        User user = restTemplate.getForObject(url, User.class, userid);
        return new ModelAndView("dispUser", "user", user);
    }

}
