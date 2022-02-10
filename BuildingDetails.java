package com.company.inventory.managment;

public class BuildingDetails {
    public int buildingId;
    public String buildingCode;
    public String buildingName;
    public static String uniqueBuildingCode;
    public int projectId;
    public int area;
    public int noOfFloors;
    public String status = "A";
    public int noOfParking;
    public int noOfBasement;

    public int getNoOfFloors() {
        return noOfFloors;
    }

    public int getNoOfParking() {
        return noOfParking;
    }

    public int getNoOfBasement() {
        return noOfBasement;
    }


    public BuildingDetails(int buildingId, String buildingCode, String buildingName, String uniqueBuildingCode, int projectId, int area, int noOfFloors, int noOfParking, int noOfBasements) {
        this.buildingId = buildingId;
        this.buildingCode = buildingCode;
        this.buildingName = buildingName;
        this.uniqueBuildingCode = uniqueBuildingCode;
        this.projectId = projectId;
        this.area = area;
        this.noOfFloors = noOfFloors;
        this.noOfParking = noOfParking;
        this.noOfBasement = noOfBasements;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingCode() {
        return buildingCode;
    }

    public void setBuildingCode(String buildingCode) {
        this.buildingCode = buildingCode;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public static String getUniqueBuildingCode() {
        return uniqueBuildingCode;
    }

    public void setUniqueBuildingCode(String uniqueBuildingCode) {
        this.uniqueBuildingCode = uniqueBuildingCode;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
