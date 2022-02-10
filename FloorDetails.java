package com.company.inventory.managment;

public class FloorDetails {
    public int floorId;
    public String floorCode;
    public int projectId;
    public int buildingId;
    public int noOfUnits;
    public String uniqueFloorCode;
    public String status = "A";

    public FloorDetails(int floorId, String floorCode, int projectId, int buildingId, int noOfUnits, String uniqueFloorCode) {
        this.floorId = floorId;
        this.floorCode = floorCode;
        this.projectId = projectId;
        this.buildingId = buildingId;
        this.noOfUnits = noOfUnits;
        this.uniqueFloorCode = uniqueFloorCode;
    }

    public int getFloorId() {
        return floorId;
    }

    public String getFloorCode() {
        return floorCode;
    }

    public void setFloorCode(String floorCode) {
        this.floorCode = floorCode;
    }

    public int getProjectId() {
        return projectId;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUniqueFloorCode() {
        return uniqueFloorCode;
    }

    public void setUniqueFloorCode(String uniqueFloorCode) {
        this.uniqueFloorCode = uniqueFloorCode;
    }
}
