/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.tangent.web.controller;

import java.util.List;

/**
 *
 * @author F4829689
 */
public class Project {

    private String pk;
    private String title;
    private String description;
    private String start_date;
    private String end_date;
    private String is_billable;
    private String is_active;

    private List<TaskSet> task_set;
    private List<ResourceSet> resource_set;

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getIs_billable() {
        return is_billable;
    }

    public void setIs_billable(String is_billable) {
        this.is_billable = is_billable;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }

    public List<TaskSet> getTask_set() {
        return task_set;
    }

    public void setTask_set(List<TaskSet> task_set) {
        this.task_set = task_set;
    }

    public List<ResourceSet> getResource_set() {
        return resource_set;
    }

    public void setResource_set(List<ResourceSet> resource_set) {
        this.resource_set = resource_set;
    }

}
