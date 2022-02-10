package com.company.inventory.managment;

import java.util.ArrayList;
import java.util.List;
import java.lang.String;
import java.util.Objects;

public class ManageBuildings {

    public static List<BuildingDetails> buildings = new ArrayList<>();

    // Method which display all building action items
    public static void buildingActionItems() {
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.printf("%50s", "Manage Buildings");
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("1) Add Building");
        System.out.println("2) Update Building");
        System.out.println("3) Delete Building");
        System.out.println("4) Show All the Building");
        System.out.println("5) Exit");

        CommonUtil.option = CommonUtil.printUserValue("Please select option: ");
        if (CommonUtil.option > 5) {
            do {
                System.out.println("Invalid option selected! Please try again.");
                CommonUtil.option = CommonUtil.printUserValue("Please select option: ");
            } while (CommonUtil.option > 5);
        }
        CommonUtil.selectedItemAction(CommonUtil.option, "Building Action Items"); // Setup lookup values
    }

    public static void addBuildings() {

        String buildingCode = CommonUtil.printUserText("Enter Building Code: ");
        String buildingName = CommonUtil.printUserText("Enter Building Name: ");
        int area = CommonUtil.printUserValue("Enter Area: ");
        int projectId = CommonUtil.printUserValue("Enter Project Id: ");
        ProjectDetails pd = ManageProjects.projects.get(ManageProjects.findIndex(ManageProjects.projects, projectId));
        String uniqueBuildingCode = pd.getProjectCode() + "/" + buildingCode;
        int buildingId = buildings.size() + 1;
        int noOfFloors = CommonUtil.printUserValue("Enter No. of Floors: ");
        int noOfParking = CommonUtil.printUserValue("Enter No. of Parking: ");
        int noOfBasements = CommonUtil.printUserValue("Enter No. of Basements: ");

        BuildingDetails buildingDetails = new BuildingDetails(buildingId, buildingCode, buildingName,
                uniqueBuildingCode, projectId, area, noOfFloors, noOfParking, noOfBasements);

        CommonUtil.option = CommonUtil.confirmation("Do you want to save this transaction?");

        if (CommonUtil.option == 1) {
            buildings.add(buildingDetails);
            System.out.println("Building " + buildingDetails.buildingName + " is added!");
            showAllBuildings();

            if (noOfBasements > 0) {
                createBasement(noOfBasements, projectId, buildingId);
            }
            // Adding GroundFloor
            ManageFloors.floors.add(new FloorDetails((ManageFloors.floors.size() + 1), "G", projectId,
                    buildingId, 0, BuildingDetails.getUniqueBuildingCode() + "/G"));

            if (noOfParking > 0) {
                createParking(noOfParking, projectId, buildingId);
            }
            if (noOfFloors > 0) {
                createFloor(noOfParking, noOfFloors, noOfBasements, projectId, buildingId);
            }
            ManageFloors.showBuildingFloors(buildingId);
            continueManageBuildings("Add");
        } else if (CommonUtil.option == 2) {
            buildingActionItems();
        } else if (CommonUtil.option == 3) {
            System.out.println("Thank you so much for using the application");
            System.exit(0);
        }
    }

    public static void showAllBuildings() {
        if (buildings.size() > 0) {
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%15s %15s %30s %30s %30s %30s %30s %30s %30s", "Building Id", "Building Code", "Unique Building Code", "Building Name", "Area", "No. of Floors", "No. of Parking", "No. of Basement", "Status");
            System.out.println();
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            for (BuildingDetails list : buildings) {
                System.out.format("%15s %15s %30s %30s %30s %30s %30s %30s %30s", list.getBuildingId(), list.getBuildingCode(), BuildingDetails.getUniqueBuildingCode(), list.getBuildingName(), list.getArea(),
                        list.getNoOfFloors(), list.getNoOfParking(), list.getNoOfBasement(), CommonUtil.getStatusDesc(list.getStatus()));
                System.out.println();
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("No building available to display. Try again!");
            buildingActionItems();
        }
    }

    public static void updateBuildings() {
        if (buildings.size() > 0) {
            showAllBuildings();
            int buildingId = CommonUtil.printUserValue("Enter Building Id: ");
            System.out.printf("%15s %15s %30s %30s %30s %30s %30s %30s %30s", "Building Id", "Building Code", "Unique Building Code", "Building Name", "Area", "No. of Floors", "No. of Parking", "No. of Basement", "Status");
            System.out.println();
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            BuildingDetails bd = buildings.get(findIndex(buildings, buildingId));
            System.out.format("%15s %15s %30s %30s %30s %30s %30s %30s %30s", bd.getBuildingId(), bd.getBuildingCode(), BuildingDetails.getUniqueBuildingCode(), bd.getBuildingName(), bd.getArea(),
                    bd.getNoOfFloors(), bd.getNoOfParking(), bd.getNoOfBasement(), CommonUtil.getStatusDesc(bd.getStatus()));
            System.out.println();
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            System.out.println("Kindly confirm which field do you want to update?");
            System.out.println("1) Building Name");
            System.out.println("2) Area");
            System.out.println("3) In activate Buildings");

            int option = CommonUtil.printUserValue("Enter Option: ");

            if (option == 1) {
                updateBuildingName(bd.buildingId - 1);
            } else if (option == 2) {
                updateArea(bd.buildingId - 1);
            } else if (option == 3) {
                updateBuildingStatus(bd.buildingId - 1);
            } else if (option > 3) {
                System.out.println("Invalid Selection!. Please try again.");
                buildingActionItems();
            }
        } else {
            System.out.println("No buildings available to update. Try again");
            buildingActionItems();
        }
    }

    public static void updateBuildingName(int buildingId) {
        BuildingDetails bd = buildings.get(buildingId);
        System.out.println("Current Building Name : " + bd.getBuildingName());
        String newBuildingName = CommonUtil.printUserText("Enter New Building Name:");
        bd.setBuildingName(newBuildingName);
        String newBuildingCode = CommonUtil.printInitials(newBuildingName);
        bd.setBuildingCode(newBuildingCode);
        System.out.println("Record updated successfully!");
        showAllBuildings();
        continueManageBuildings("Update");
    }

    public static void updateArea(int buildingId) {
        BuildingDetails bd = buildings.get(buildingId);
        System.out.println("Current Building Area : " + bd.getArea());
        int newBuildingArea = CommonUtil.printUserValue("Enter New Building Area:");
        bd.setArea(newBuildingArea);
        System.out.println("Record updated successfully!");
        showAllBuildings();
        continueManageBuildings("Update");
    }

    public static void updateBuildingStatus(int buildingId) {
        BuildingDetails bd = buildings.get(buildingId);
        bd.setStatus("I");
        System.out.println("Building Ìn activated successfully!");
        showAllBuildings();
        continueManageBuildings("Update");
    }

    public static void deleteBuildings() {
        if (buildings.size() > 0) {
            showAllBuildings();
            int buildingId = CommonUtil.printUserValue("Enter Building Id: ");
            System.out.printf("%15s %15s %30s %30s %30s %30s %30s %30s %30s", "Building Id", "Building Code", "Unique Building Code", "Building Name", "Area", "No. of Floors", "No. of Parking", "No. of Basement", "Status");
            System.out.println();
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            BuildingDetails bd = buildings.get(findIndex(buildings, buildingId));
            System.out.format("%15s %15s %30s %30s %30s %30s %30s %30s %30s", bd.getBuildingId(), bd.getBuildingCode(), BuildingDetails.getUniqueBuildingCode(), bd.getBuildingName(),
                    bd.getArea(), bd.getNoOfFloors(), bd.getNoOfParking(), bd.getNoOfBasement(), CommonUtil.getStatusDesc(bd.getStatus()));
            System.out.println();
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            System.out.println("Are you sure, do you want to delete this lookup?");
            System.out.println("1) Yes");
            System.out.println("2) No");

            int deleteConfirmation = CommonUtil.printUserValue("Enter Option: ");
            if (deleteConfirmation == 1) {
                buildings.remove(findIndex(buildings, buildingId));
                System.out.println("Product is deleted successfully!");
                showAllBuildings();

                continueManageBuildings("Delete");

            } else if (deleteConfirmation == 2) {
                buildingActionItems();
            }
        } else {
            System.out.println("No buildings available to delete. Try again");
            buildingActionItems();
        }
    }

    public static void continueManageBuildings(String action) {

        // Action will be an Add, Update, Delete
        if (Objects.equals(action, "Add")) {
            CommonUtil.option = CommonUtil.confirmation("Do you want to add another building?");
            if (CommonUtil.option == 1) {
                addBuildings();
            } else if (CommonUtil.option == 2) {
                buildingActionItems();
            } else if (CommonUtil.option == 3) {
                CommonUtil.commonActionItems();
            } else if (CommonUtil.option > 3) {
                System.out.println("Invalid Selection!. Please try again.");
                buildingActionItems();
            }
        } else if (Objects.equals(action, "Update")) {
            CommonUtil.option = CommonUtil.confirmation("Do you want to update another building?");
            if (CommonUtil.option == 1) {
                updateBuildings();
            } else if (CommonUtil.option == 2) {
                buildingActionItems();
            } else if (CommonUtil.option == 3) {
                CommonUtil.commonActionItems();
            } else if (CommonUtil.option > 3) {
                System.out.println("Invalid Selection!. Please try again.");
                buildingActionItems();
            }
        } else if (Objects.equals(action, "Delete")) {
            CommonUtil.option = CommonUtil.confirmation("Do you want to delete another building?");
            if (CommonUtil.option == 1) {
                deleteBuildings();
            } else if (CommonUtil.option == 2) {
                buildingActionItems();
            } else if (CommonUtil.option == 3) {
                CommonUtil.commonActionItems();
            } else if (CommonUtil.option > 3) {
                System.out.println("Invalid Selection!. Please try again.");
                buildingActionItems();
            }
        } else if (Objects.equals(action, "Show All")) {
            CommonUtil.option = CommonUtil.confirmation("Do you want to continue?");
            if (CommonUtil.option == 1) {
                buildingActionItems();
            } else if (CommonUtil.option == 2 || CommonUtil.option == 3) {
                CommonUtil.commonActionItems();
            } else if (CommonUtil.option > 3) {
                System.out.println("Invalid Selection!. Please try again.");
                buildingActionItems();
            }
        }
    }

    public static int findIndex(List<BuildingDetails> buildingDetailsList, int id) {
        int index = 0;

        for (int i = 0; i < buildings.size(); i++) {
            if (buildings.get(i).getBuildingId() == id) {
                index = i;
                break;
            }
        }
        return index;
    }

    public static String getBuildingName(int buildingId) {
        BuildingDetails bd = buildings.get(findIndex(buildings, buildingId));
        return bd.getBuildingName();
    }

    public static int getBuildingCount(int projectId) {
        int count = 0;

        for (BuildingDetails building : buildings) {
            if (building.getProjectId() == projectId) {
                count = (count + 1);
                //break;
            }
        }
        return count;
    }

    public static void createBasement(int noOfBasements, int projectId, int buildingId) {
        int floorCount;
        int floorCountLoop;
        int basementNumber = 0;
        if (ManageFloors.floors.size() == 0) {
            floorCount = 1;
            floorCountLoop = noOfBasements;
        } else {
            floorCount = ManageFloors.floors.size();
            floorCountLoop = (noOfBasements + floorCount - 1);
        }
        //System.out.println("Number of Basement " + noOfBasements);
        for (int i = floorCount; i <= floorCountLoop; i++) {
            String floorCode = "B" + ++basementNumber;
            ManageFloors.floors.add(new FloorDetails(i, floorCode, projectId,
                    buildingId, 0, BuildingDetails.getUniqueBuildingCode() + "/" + floorCode));
        }
    }

    public static void createParking(int noOfParking, int projectId, int buildingId) {
        int floorCount = (ManageFloors.floors.size());
        int parkingNumber = 0;
        //System.out.println("Number of Parking " + noOfParking);

        for (int i = (ManageFloors.floors.size() + 1); i <= (noOfParking + floorCount); i++) {
            String floorCode = "P" + ++parkingNumber;
            ManageFloors.floors.add(new FloorDetails(i, floorCode, projectId,
                    buildingId, 0, BuildingDetails.getUniqueBuildingCode() + "/" + floorCode));
        }
    }

    public static void createFloor (int noOfParking, int noOfFloors, int noOfBasements, int projectId, int buildingId) {
        int floorCount = (ManageFloors.floors.size());
        int floorNumber = noOfParking;
        System.out.println("Number of floors " + noOfFloors);
        for (int i = (ManageFloors.floors.size() + 1); i <= (noOfFloors + floorCount - noOfBasements - noOfParking - 1); i++) {
            String floorCode = "F" + ++floorNumber;
            ManageFloors.floors.add(new FloorDetails(i, floorCode, projectId,
                    buildingId, 0, BuildingDetails.getUniqueBuildingCode() + "/" + floorCode));
        }
    }
}
