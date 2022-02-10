package com.company.inventory.managment;

import java.util.ArrayList;
import java.util.List;
import java.lang.String;
import java.util.Objects;

public class ManageFloors {
    public static List<FloorDetails> floors = new ArrayList<>();

    public static void showBuildingFloors(int buildingId) {
        if (floors.size() > 0) {
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%15s %15s %30s %30s %30s", "Floor Id", "Floor Code", "Project", "Building", "Status");
            System.out.println();
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            for (FloorDetails list : floors) {
                if (list.getBuildingId() == buildingId) {
                    System.out.format("%15s %15s %30s %30s %30s", list.getFloorId(), list.getFloorCode(), ManageProjects.getProjectName(list.getProjectId()),
                            ManageBuildings.getBuildingName(list.getBuildingId()), CommonUtil.getStatusDesc(list.getStatus()));
                    System.out.println();
                }
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("No floors available to display. Try again!");
            //floorActionItems();
        }
    }

    public static void showProjectFloors(int projectId) {
        if (floors.size() > 0) {
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%15s %15s %30s %30s %30s", "Floor Id", "Floor Code", "Project", "Building", "Status");
            System.out.println();
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            for (FloorDetails list : floors) {
                if (list.getProjectId() == projectId) {
                    System.out.format("%15s %15s %30s %30s %30s", list.getFloorId(), list.getFloorCode(), ManageProjects.getProjectName(list.getProjectId()),
                            ManageBuildings.getBuildingName(list.getBuildingId()), CommonUtil.getStatusDesc(list.getStatus()));
                    System.out.println();
                }
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("No floors available to display. Try again!");
            //floorActionItems();
        }
    }

    // Method which display all floor action items
    public static void floorActionItems() {
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.printf("%50s", "Manage Floors");
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("1) Add Floor");
        System.out.println("2) Update Floor");
        System.out.println("3) Delete Floor");
        System.out.println("4) Show All the Floor");
        System.out.println("5) Exit");

        CommonUtil.option = CommonUtil.printUserValue("Please select option: ");
        if (CommonUtil.option > 5) {
            do {
                System.out.println("Invalid option selected! Please try again.");
                CommonUtil.option = CommonUtil.printUserValue("Please select option: ");
            } while (CommonUtil.option > 5);
        }
        CommonUtil.selectedItemAction(CommonUtil.option, "Floor Action Items"); // Setup lookup values
    }

    public static void addFloors() {

        String floorCode = CommonUtil.printUserText("Enter Floor Code: ");
        int projectId = CommonUtil.printUserValue("Enter Project Id: ");
        int buildingId = CommonUtil.printUserValue("Enter Building Id: ");
        int noOfUnits = CommonUtil.printUserValue("Enter No.of Units: ");
        BuildingDetails bd = ManageBuildings.buildings.get(ManageBuildings.findIndex(ManageBuildings.buildings, buildingId));
        String uniqueFloorCode = bd.getBuildingCode() + "/" + floorCode;
        int floorId = floors.size() + 1;

        FloorDetails floorDetails = new FloorDetails(floorId, floorCode, projectId,
                buildingId, 0, uniqueFloorCode);

        CommonUtil.option = CommonUtil.confirmation("Do you want to save this transaction?");

        if (CommonUtil.option == 1) {
            floors.add(floorDetails);
            System.out.println("Floor " + uniqueFloorCode + " is added!");
            showProjectFloors(projectId);
            if (noOfUnits > 0) {
                createUnits(noOfUnits, uniqueFloorCode, floorId, projectId, buildingId);
            }
            ManageUnits.showProjectUnits(projectId);
            continueManageFloors("Add", projectId);
        } else if (CommonUtil.option == 2) {
            floorActionItems();
        } else if (CommonUtil.option == 3) {
            System.out.println("Thank you so much for using the application");
            System.exit(0);
        }
    }

    public static void updateFloors(int projectId) {
        if (floors.size() > 0) {
            showProjectFloors(projectId);
            int floorId = CommonUtil.printUserValue("Enter Floor Id: ");
            System.out.printf("%15s %15s %30s %30s", "Floor Id", "Floor Code", "Unique Floor Code", "Status");
            System.out.println();
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            FloorDetails fd = floors.get(findIndex(floors, floorId));
            System.out.format("%15s %15s %30s %30s", fd.getFloorId(),
                    fd.getFloorCode(), fd.getUniqueFloorCode(),
                    CommonUtil.getStatusDesc(fd.getStatus()));
            System.out.println();
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            System.out.println("Kindly confirm which field do you want to update?");
            System.out.println("1) Floor Code");
            System.out.println("2) In activate Floor");

            int option = CommonUtil.printUserValue("Enter Option: ");

            if (option == 1) {
                updateFloorCode(fd.floorId - 1);
            } else if (option == 2) {
                updateFloorStatus(fd.floorId - 1);
            } else if (option > 3) {
                System.out.println("Invalid Selection!. Please try again.");
                floorActionItems();
            }
        } else {
            System.out.println("No floors available to update. Try again");
            floorActionItems();
        }
    }

    public static void updateFloorCode(int floorId) {
        FloorDetails fd = floors.get(floorId);
        System.out.println("Current Floor Code : " + fd.getFloorCode());
        String newFloorCode = CommonUtil.printUserText("Enter New Floor Code:");
        fd.setFloorCode(newFloorCode);
        String newUniqueFloorCode = BuildingDetails.getUniqueBuildingCode() + " " + newFloorCode;
        fd.setUniqueFloorCode(newUniqueFloorCode);
        System.out.println("Record updated successfully!");
        showProjectFloors(fd.getProjectId());
        continueManageFloors("Update", fd.projectId);
    }

    public static void updateFloorStatus(int floorId) {
        FloorDetails fd = floors.get(floorId);
        fd.setStatus("I");
        System.out.println("Floor Ìn activated successfully!");
        showProjectFloors(fd.getProjectId());
        continueManageFloors("Update", fd.projectId);
    }

    public static void deleteFloors(int projectId) {
        if (floors.size() > 0) {
            showProjectFloors(projectId);
            int floorId = CommonUtil.printUserValue("Enter Floor Id: ");
            System.out.printf("%15s %15s %30s %30s", "Floor Id", "Floor Code", "Unique Floor Code", "Status");
            System.out.println();
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            FloorDetails fd = floors.get(findIndex(floors, floorId));
            System.out.format("%15s %15s %30s %30s", fd.getFloorId(), fd.getFloorCode(), fd.getUniqueFloorCode(), CommonUtil.getStatusDesc(fd.getStatus()));
            System.out.println();
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            System.out.println("Are you sure, do you want to delete this lookup?");
            System.out.println("1) Yes");
            System.out.println("2) No");

            int deleteConfirmation = CommonUtil.printUserValue("Enter Option: ");
            if (deleteConfirmation == 1) {
                floors.remove(findIndex(floors, floorId));
                System.out.println("Product is deleted successfully!");
                ManageUnits.deleteFloorUnits(projectId);
                showProjectFloors(projectId);

                continueManageFloors("Delete", fd.projectId);

            } else if (deleteConfirmation == 2) {
                floorActionItems();
            }
        } else {
            System.out.println("No floors available to delete. Try again");
            floorActionItems();
        }
    }

    public static void continueManageFloors(String action, int projectId) {

        // Action will be an Add, Update, Delete
        if (Objects.equals(action, "Add")) {
            CommonUtil.option = CommonUtil.confirmation("Do you want to add another floor?");
            if (CommonUtil.option == 1) {
                addFloors();
            } else if (CommonUtil.option == 2) {
                floorActionItems();
            } else if (CommonUtil.option == 3) {
                CommonUtil.commonActionItems();
            } else if (CommonUtil.option > 3) {
                System.out.println("Invalid Selection!. Please try again.");
                floorActionItems();
            }
        } else if (Objects.equals(action, "Update")) {
            CommonUtil.option = CommonUtil.confirmation("Do you want to update another floor?");
            if (CommonUtil.option == 1) {
                updateFloors(projectId);
            } else if (CommonUtil.option == 2) {
                floorActionItems();
            } else if (CommonUtil.option == 3) {
                CommonUtil.commonActionItems();
            } else if (CommonUtil.option > 3) {
                System.out.println("Invalid Selection!. Please try again.");
                floorActionItems();
            }
        } else if (Objects.equals(action, "Delete")) {
            CommonUtil.option = CommonUtil.confirmation("Do you want to delete another floor?");
            if (CommonUtil.option == 1) {
                deleteFloors(projectId);
            } else if (CommonUtil.option == 2) {
                floorActionItems();
            } else if (CommonUtil.option == 3) {
                CommonUtil.commonActionItems();
            } else if (CommonUtil.option > 3) {
                System.out.println("Invalid Selection!. Please try again.");
                floorActionItems();
            }
        } else if (Objects.equals(action, "Show All")) {
            CommonUtil.option = CommonUtil.confirmation("Do you want to continue?");
            if (CommonUtil.option == 1) {
                floorActionItems();
            } else if (CommonUtil.option == 2 || CommonUtil.option == 3) {
                CommonUtil.commonActionItems();
            } else if (CommonUtil.option > 3) {
                System.out.println("Invalid Selection!. Please try again.");
                floorActionItems();
            }
        }
    }

    public static int findIndex(List<FloorDetails> floorDetailsList, int id) {
        int index = 0;

        for (int i = 0; i < floors.size(); i++) {
            if (floors.get(i).getFloorId() == id) {
                index = i;
                break;
            }
        }
        return index;
    }

    public static int getFloorCount(int projectId) {
        int count = 0;

        for (FloorDetails floor : floors) {
            if (floor.getProjectId() == projectId) {
                count = (count + 1);
                //break;
            }
        }
        return count;
    }
    //return 0;

    public static void createUnits (int noOfUnits, String uniqueFloorCode, int floorId, int projectId, int buildingId) {
        int unitCount = (ManageUnits.units.size());
        int count = 1;
        String unitCode;
        String uniqueUnitCode;
        for (int i = (unitCount + 1); i <= (noOfUnits + unitCount); i++) {
            unitCode = ("U" + count++);
            uniqueUnitCode = uniqueFloorCode + "/" + unitCode;
            System.out.println(uniqueUnitCode);
            ManageUnits.units.add(new UnitDetails(i, unitCode, uniqueUnitCode, floorId,
                    projectId, buildingId, "", "", "", 0, 0,
                    0, 0, ""));
        }
    }
}

