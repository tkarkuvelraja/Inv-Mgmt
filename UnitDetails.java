package com.company.inventory.managment;

public class UnitDetails {

    public int unitId;
    public String unitCode;
    public String uniqueUnitCode;
    public int floorId;
    public int projectId;
    public int buildingId;
    public String bedRoomType;
    public String spaceType;
    public String viewType;
    public int price;
    public int discount;
    public int specialPrice;
    public int area;
    public String isFurnished;
    public String status = "A";

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getUniqueUnitCode() {
        return uniqueUnitCode;
    }

    public void setUniqueUnitCode(String uniqueUnitCode) {
        this.uniqueUnitCode = uniqueUnitCode;
    }

    public int getFloorId() {
        return floorId;
    }

    public void setFloorId(int floorId) {
        this.floorId = floorId;
    }
    public int getProjectId() {
        return projectId;
    }

    public void setProjecctId (int projectId) {
        this.projectId = projectId;
    }
    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }

    public String getBedRoomType() {
        return bedRoomType;
    }

    public void setBedRoomType(String bedRoomType) {
        this.bedRoomType = bedRoomType;
    }

    public String getSpaceType() {
        return spaceType;
    }

    public void setSpaceType(String spaceType) {
        this.spaceType = spaceType;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(int specialPrice) {
        this.specialPrice = specialPrice;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getIsFurnished() {
        return isFurnished;
    }

    public void setIsFurnished(String isFurnished) {
        this.isFurnished = isFurnished;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public UnitDetails (int unitId, String unitCode, String uniqueUnitCode, int floorId, int projectId, int buildingId, String bedRoomType, String spaceType, String viewType, int price, int discount, int specialPrice, int area, String isFurnished) {
        this.unitId = unitId;
        this.unitCode = unitCode;
        this.uniqueUnitCode = uniqueUnitCode;
        this.floorId = floorId;
        this.projectId = projectId;
        this.buildingId = buildingId;
        this.bedRoomType = bedRoomType;
        this.spaceType = spaceType;
        this.viewType = viewType;
        this.price = price;
        this.discount = discount;
        this.specialPrice = specialPrice;
        this.area = area;
        this.isFurnished = isFurnished;
    }
}
