package com.company.inventory.managment;

public class ProjectDetails {

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getUniqueProjectName() {
        return uniqueProjectName;
    }

    public void setUniqueProjectName(String uniqueProjectName) {
        this.uniqueProjectName = uniqueProjectName;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ProjectDetails(int projectId, String projectCode, String projectName, String uniqueProjectName, double area, String uom, String currency, String city, String district, String country) {
        this.projectId = projectId;
        this.projectCode = projectCode;
        this.projectName = projectName;
        this.uniqueProjectName = uniqueProjectName;
        this.area = area;
        this.uom = uom;
        this.currency = currency;
        this.city = city;
        this.district = district;
        this.country = country;
    }

    public int projectId;
    public String projectCode;
    public String projectName;
    public String uniqueProjectName;
    public double area;
    public String uom;
    public String currency;
    public String city;
    public String district;
    public String country;
    public String status = "A";
}