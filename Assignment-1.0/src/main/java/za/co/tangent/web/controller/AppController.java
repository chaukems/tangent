package za.co.tangent.web.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import za.co.tangent.service.MyUserDetailsModel;

@Controller
public class AppController {

    @Autowired
    Gson gson;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView welcomePage() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String token = ((MyUserDetailsModel) principal).getToken();

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", "application/json");
        headers.add("Authorization", "Token " + token);

        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<String> response = restTemplate.exchange("http://projectservice.staging.tangentmicroservices.com:80/api/v1/projects/", HttpMethod.GET, request, String.class);
        String result = response.getBody();

        List<Project> projects = gson.fromJson(result, new TypeToken<List<Project>>() {
        }.getType());

        StringBuilder tableRows = new StringBuilder();

        for (Project project : projects) {

            String viewTaskButton = "";

            if (!project.getTask_set().isEmpty()) {
                TaskSet task = project.getTask_set().get(0);
                viewTaskButton = "<td><button type=\"button\" id = \"" + task.getId() + "\" class=\"btn btnViewTasks btn-info btn-sm\" data-toggle=\"modal\" data-target=\"#tasksModal\">View Tasks</button></td>";
            }

            tableRows.append("<tr>" + "<td>").append(project.getPk()).append("</td>\n" + " <td>")
                    .append(project.getTitle()).append("</td>" + " <td>")
                    .append(project.getDescription()).append("</td>" + " <td>")
                    .append(project.getStart_date()).append("</td>" + " <td>")
                    .append(project.getEnd_date()).append("</td>" + " <td>")
                    .append(project.getIs_billable()).append("</td>" + " <td>")
                    .append(project.getIs_active()).append("</td>")
                    .append(viewTaskButton).append("</tr>");
        }

        ModelAndView model = new ModelAndView();
        model.addObject("projectsList", tableRows.toString());
        model.addObject("token", token);
        model.setViewName("welcome");
        return model;
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
