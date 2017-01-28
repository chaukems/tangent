/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.tangent.web.controller;

/**
 *
 * @author F4829689
 */
public class TaskSet {

    private String id;
    private String title;
    private String due_date;
    private String estimated_hours;
    private String project;

    private Project project_data;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public String getEstimated_hours() {
        return estimated_hours;
    }

    public void setEstimated_hours(String estimated_hours) {
        this.estimated_hours = estimated_hours;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Project getProject_data() {
        return project_data;
    }

    public void setProject_data(Project project_data) {
        this.project_data = project_data;
    }

}
