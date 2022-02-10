package com.company.inventory.managment;

public class Dashboards {

    public static void projectDashboard() {
        if (ManageProjects.projects.size() > 0) {
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%15s %15s %30s %30s %30s %30s %30s %30s %30s %30s %30s %30s %30s", "Project Id", "Project Code", "Project Name", "Area", "UOM", "Currency", "City", "District", "Country", "No. of Buildings", "No. of Floors", "No. of Units", "Status");
            System.out.println();
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            for (ProjectDetails list : ManageProjects.projects) {
                System.out.format("%15s %15s %30s %30s %30s %30s %30s %30s %30s %30s %30s %30s %30s", list.getProjectId(), list.getProjectCode(), list.getProjectName(), list.getArea(), list.getUom(), list.getCurrency(), list.getCity(), list.getDistrict(), list.getCountry(),
                        ManageBuildings.getBuildingCount(list.projectId), ManageFloors.getFloorCount(list.projectId),
                        ManageUnits.getUnitCount(list.projectId),
                        CommonUtil.getStatusDesc(list.getStatus()));
                System.out.println();
            }
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            continueProjectDashboard();
        } else {
            System.out.println("No projects available to display. Please do setup entities!");
            CommonUtil.commonActionItems();
        }

    }

    public static void unitDashboard() {
        if (ManageUnits.units.size() > 0) {

            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%15s %30s %30s %30s %30s  %30s  %30s  %30s  %30s  %30s  %30s  %30s", "Unit Name", "Project",
                    "Building", "Bedroom Type", "Space Type", "View Type", "Price", "Discount", "Special Price", "Area", "Furnished", "Status");
            System.out.println();
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            for (UnitDetails list : ManageUnits.units) {
                System.out.format("%15s %30s %30s %30s %30s  %30s  %30s  %30s  %30s  %30s  %30s  %30s", list.getUniqueUnitCode(),
                        ManageProjects.getProjectName(list.projectId),
                        ManageBuildings.getBuildingName(list.projectId),
                        list.getBedRoomType(),
                        list.getSpaceType(),
                        list.getViewType(),
                        list.getPrice(),
                        list.getDiscount(),
                        list.getSpecialPrice(),
                        list.getArea(),
                        CommonUtil.getFurnishedDesc(list.getIsFurnished()),
                        CommonUtil.getStatusDesc(list.getStatus()));
                System.out.println();
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            continueUnitDashboard();
        } else {
            System.out.println("No units available to display. Please do setup entities!");
            CommonUtil.commonActionItems();
        }
    }

    public static void continueProjectDashboard() {
        CommonUtil.option = CommonUtil.confirmation("Do you want to continue?");
        if (CommonUtil.option == 1) {
            Dashboards.projectDashboard();
        } else if (CommonUtil.option == 2) {
            CommonUtil.commonActionItems();
        } else if (CommonUtil.option == 3) {
            CommonUtil.commonActionItems();
        } else if (CommonUtil.option > 3) {
            System.out.println("Invalid Selection!. Please try again.");
            CommonUtil.commonActionItems();
        }
    }

    public static void continueUnitDashboard() {
        CommonUtil.option = CommonUtil.confirmation("Do you want to continue?");
        if (CommonUtil.option == 1) {
            Dashboards.unitDashboard();
        } else if (CommonUtil.option == 2) {
            CommonUtil.commonActionItems();
        } else if (CommonUtil.option == 3) {
            CommonUtil.commonActionItems();
        } else if (CommonUtil.option > 3) {
            System.out.println("Invalid Selection!. Please try again.");
            CommonUtil.commonActionItems();
        }
    }
}