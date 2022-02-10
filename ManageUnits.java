package com.company.inventory.managment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ManageUnits {

    public static List<UnitDetails> units = new ArrayList<UnitDetails>();

    public static void showProjectUnits(int projectId) {
        if (units.size() > 0) {
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%15s %30s %30s %30s %30s  %30s  %30s  %30s  %30s  %30s  %30s  %30s", "Unit Name", "Project",
                    "Building", "Bedroom Type", "Space Type", "View Type", "Price", "Discount", "Special Price", "Area", "Furnished", "Status");
            System.out.println();
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            for (UnitDetails list : units) {
                  if (list.getProjectId() == projectId) {
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
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("No units available to display. Try again!");
            //floorActionItems();
        }
    }

    public static void showUnits(int unitId) {
        if (units.size() > 0) {
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%15s %30s %30s %30s %30s  %30s  %30s  %30s  %30s  %30s  %30s  %30s", "Unit Name", "Project",
                    "Building", "Bedroom Type", "Space Type", "View Type", "Price", "Discount", "Special Price", "Area", "Furnished", "Status");
            System.out.println();
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            for (UnitDetails list : units) {
                if (list.getUnitId() == unitId) {
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
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("No units available to display. Try again!");
            //floorActionItems();
        }
    }

    public static void deleteFloorUnits(int floorId) {
        if (units.size() > 0) {
            FloorDetails fd = ManageFloors.floors.get(ManageFloors.findIndex(ManageFloors.floors, floorId));
            showProjectUnits(fd.getProjectId());

            for (UnitDetails list : units) {
                if (list.getFloorId() == fd.getFloorId()) {
                    units.remove(findIndex(units, list.getUnitId()));
                }
            }
            System.out.println("Floor (" + fd.getUniqueFloorCode() + ") units are deleted successfully!");
        } else {
            System.out.println("No units available to delete. Try again");
        }
    }

    public static void updateUnitCode(int unitId) {
        UnitDetails ud = units.get(unitId);
        System.out.println("Current Unit Code : " + ud.getUnitCode());
        String newUnitCode = CommonUtil.printUserText("Enter New Unit Code:");
        ud.setUnitCode(newUnitCode);
        String newUniqueUnitCode = ud.getUniqueUnitCode().replace(ud.getUnitCode(), newUnitCode);
        ud.setUniqueUnitCode(newUniqueUnitCode);
        System.out.println("Record updated successfully!");
        showProjectUnits(ud.getProjectId());
        //continueManageUnits("Update", ud.projectId);
    }

    public static int findIndex(List<UnitDetails> unitDetailsList, int id) {
        int index = 0;

        for (int i = 0; i < units.size(); i++) {
            if (units.get(i).getUnitId() == id) {
                index = i;
                break;
            }
        }
        return index;
    }

    public static int getUnitCount (int projectId)
    {
        int count = 0;

        for (UnitDetails unit : units) {
            if (unit.getProjectId() == projectId) {
                count = (count + 1);
                //break;
            }
        }
        return count;
    }
        // Method which display all unit action items
        public static void unitActionItems() {
            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.printf("%50s", "Manage Units");
            System.out.println();
            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println("1) Add Unit");
            System.out.println("2) Update Unit");
            System.out.println("3) Delete Unit");
            System.out.println("4) Show All the Unit");
            System.out.println("5) Exit");

            CommonUtil.option = CommonUtil.printUserValue("Please select option: ");
            if (CommonUtil.option > 5) {
                do {
                    System.out.println("Invalid option selected! Please try again.");
                    CommonUtil.option = CommonUtil.printUserValue("Please select option: ");
                } while (CommonUtil.option > 5);
            }
            CommonUtil.selectedItemAction(CommonUtil.option, "Unit Action Items"); // Setup lookup values
        }

        public static void addUnits() {

            int projectId = CommonUtil.printUserValue("Enter Project Id: ");
            int buildingId = CommonUtil.printUserValue("Enter Building Id: ");
            int floorId = CommonUtil.printUserValue("Enter Floor Id: ");
            String bedroomType = CommonUtil.printUserText("Enter Bedroom Type: ");
            String spaceType = CommonUtil.printUserText("Enter Space Type: ");
            String viewType = CommonUtil.printUserText("Enter View Type: ");
            int price = CommonUtil.printUserValue("Enter Price: ");
            int discount = CommonUtil.printUserValue("Enter Discount: ");
            int specialPrice = (price - discount);
            int area = CommonUtil.printUserValue("Enter Area: ");
            String isFurnished = CommonUtil.printUserText("Enter Furnished Flag: ");

            BuildingDetails bd = ManageBuildings.buildings.get(ManageBuildings.findIndex(ManageBuildings.buildings, buildingId));
            FloorDetails fd = ManageFloors.floors.get(ManageFloors.findIndex(ManageFloors.floors, floorId));
            String uniqueFloorCode = bd.getBuildingCode() + "/" + fd.getFloorCode();
            int unitId = units.size() + 1;
            String unitCode = "U" + unitId;
            String uniqueUnitCode = uniqueFloorCode + "/" + unitCode;

            UnitDetails unitDetails = new UnitDetails(unitId, unitCode, uniqueUnitCode,
                    floorId, projectId, buildingId, bedroomType, spaceType, viewType,
                    price, discount, specialPrice, area, isFurnished);

            CommonUtil.option = CommonUtil.confirmation("Do you want to save this transaction?");

            if (CommonUtil.option == 1) {
                units.add(unitDetails);
                System.out.println("Unit " + uniqueUnitCode + " is added!");
                showProjectUnits(projectId);
                continueManageUnits("Add", projectId);
            } else if (CommonUtil.option == 2) {
                unitActionItems();
            } else if (CommonUtil.option == 3) {
                System.out.println("Thank you so much for using the application");
                System.exit(0);
            }
        }

        public static void updateUnits (int projectId) {
            if (units.size() > 0) {
                showProjectUnits(projectId);
                int unitId = CommonUtil.printUserValue("Enter Unit Id: ");
                System.out.printf("%15s %30s %30s %30s %30s  %30s  %30s  %30s  %30s  %30s  %30s  %30s", "Unit Name", "Project",
                        "Building", "Bedroom Type", "Space Type", "View Type", "Price", "Discount", "Special Price", "Area", "Furnished", "Status");
                System.out.println();
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                UnitDetails ud = units.get(findIndex(units, unitId));
                System.out.format("%15s %30s %30s %30s %30s  %30s  %30s  %30s  %30s  %30s  %30s  %30s", ud.getUniqueUnitCode(),
                        ManageProjects.getProjectName(ud.projectId),
                        ManageBuildings.getBuildingName(ud.projectId),
                        ud.getBedRoomType(),
                        ud.getSpaceType(),
                        ud.getViewType(),
                        ud.getPrice(),
                        ud.getDiscount(),
                        ud.getSpecialPrice(),
                        ud.getArea(),
                        CommonUtil.getFurnishedDesc(ud.getIsFurnished()),
                        CommonUtil.getStatusDesc(ud.getStatus()));
                System.out.println();
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                System.out.println("Kindly confirm which field do you want to update?");
                System.out.println("1) Bedroom Type");
                System.out.println("2) Space Type");
                System.out.println("3) View Type");
                System.out.println("4) Price");
                System.out.println("5) Discount");
                System.out.println("6) Area");
                System.out.println("7) Furnished Flag");
                System.out.println("8) In activate Unit");
                System.out.println("9) Activate Unit");

                int option = CommonUtil.printUserValue("Enter Option: ");

                if (option == 1) {
                    updateBedroomType(ud.unitId-1);
                } else if (option == 2) {
                    updateSpaceType(ud.unitId -1);
                }else if (option == 3) {
                    updateViewType(ud.unitId -1);
                }else if (option == 4) {
                    updatePrice(ud.unitId -1);
                }else if (option == 5) {
                    updateDiscount(ud.unitId -1);
                }else if (option == 6) {
                    updateArea(ud.unitId -1);
                }else if (option == 7) {
                    updateFurnishedFlag(ud.unitId -1);
                }else if (option == 8) {
                    inActivateUnit(ud.unitId -1);
                }else if (option == 9) {
                    activateUnit(ud.unitId -1);
                } else if (option > 9) {
                    System.out.println("Invalid Selection!. Please try again.");
                    unitActionItems();
                }
            } else {
                System.out.println("No units available to update. Try again");
                unitActionItems();
            }
        }

        public static void inActivateUnit(int unitId) {
            UnitDetails ud = units.get(unitId);
            ud.setStatus("I");
            System.out.println("Unit Ìn activated successfully!");
            showUnits(unitId+1);
            continueManageUnits("Update", ud.projectId);
        }

    public static void activateUnit(int unitId) {
        UnitDetails ud = units.get(unitId);
        ud.setStatus("A");
        System.out.println("Unit Activated successfully!");
        showUnits(unitId+1);
        continueManageUnits("Update", ud.projectId);
    }

    public static void updateArea(int unitId) {
        UnitDetails ud = units.get(unitId);
        System.out.println("Current Unit Area : " + ud.getArea());
        int newUnitArea = CommonUtil.printUserValue("Enter New Area:");
        ud.setArea(newUnitArea);
        System.out.println("Record updated successfully!");
        showUnits(unitId+1);
        continueManageUnits("Update", ud.projectId);
    }

    public static void updateBedroomType(int unitId) {
        UnitDetails ud = units.get(unitId);
        System.out.println("Current Unit Bedroom Type : " + ud.getBedRoomType());
        String newUnitBedroomType = CommonUtil.printUserText("Enter New Bedroom Type:");
        ud.setBedRoomType(newUnitBedroomType);
        System.out.println("Record updated successfully!");
        showUnits(unitId+1);
        continueManageUnits("Update", ud.projectId);
    }

    public static void updateSpaceType(int unitId) {
        UnitDetails ud = units.get(unitId);
        System.out.println("Current Unit Space Type : " + ud.getSpaceType());
        String newUnitSpaceType = CommonUtil.printUserText("Enter New Space Type:");
        ud.setSpaceType(newUnitSpaceType);
        System.out.println("Record updated successfully!");
        showUnits(unitId+1);
        continueManageUnits("Update", ud.projectId);
    }

    public static void updateViewType(int unitId) {
        UnitDetails ud = units.get(unitId);
        System.out.println("Current Unit View Type : " + ud.getViewType());
        String newUnitViewType = CommonUtil.printUserText("Enter New View Type:");
        ud.setViewType(newUnitViewType);
        System.out.println("Record updated successfully!");
        showUnits(unitId+1);
        continueManageUnits("Update", ud.projectId);
    }

    public static void updatePrice(int unitId) {
        UnitDetails ud = units.get(unitId);
        System.out.println("Current Unit Price : " + ud.getPrice());
        int newUnitPrice = CommonUtil.printUserValue("Enter New Price:");
        ud.setPrice(newUnitPrice);
        ud.setSpecialPrice(newUnitPrice - ud.getDiscount());
        System.out.println("Record updated successfully!");
        showUnits(unitId+1);
        continueManageUnits("Update", ud.projectId);
    }

    public static void updateDiscount(int unitId) {
        UnitDetails ud = units.get(unitId);
        System.out.println("Current Discount : " + ud.getDiscount());
        int newUnitDiscount = CommonUtil.printUserValue("Enter New Discount:");
        ud.setDiscount(newUnitDiscount);
        ud.setSpecialPrice(ud.getPrice() - newUnitDiscount);
        System.out.println("Record updated successfully!");
        showUnits(unitId+1);
        continueManageUnits("Update", ud.projectId);
    }

    public static void updateFurnishedFlag(int unitId) {
        UnitDetails ud = units.get(unitId);
        System.out.println("Current Unit Furnished Status : " + CommonUtil.getFurnishedDesc(ud.getIsFurnished()));
        String newFurnishedFlag = CommonUtil.printUserText("Enter New Furnished Flag:");
        ud.setIsFurnished(newFurnishedFlag);
        System.out.println("Record updated successfully!");
        showUnits(unitId+1);
        continueManageUnits("Update", ud.projectId);
    }

        public static void deleteUnits(int projectId) {
            if (units.size() > 0) {
                showProjectUnits(projectId);
                int unitId = CommonUtil.printUserValue("Enter Unit Id: ");
                System.out.printf("%15s %30s %30s %30s %30s  %30s  %30s  %30s  %30s  %30s  %30s  %30s", "Unit Name", "Project",
                        "Building", "Bedroom Type", "Space Type", "View Type", "Price", "Discount", "Special Price", "Area", "Furnished", "Status");
                System.out.println();
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                UnitDetails ud = units.get(findIndex(units, unitId));
                System.out.format("%15s %30s %30s %30s %30s  %30s  %30s  %30s  %30s  %30s  %30s  %30s", ud.getUniqueUnitCode(),
                        ManageProjects.getProjectName(ud.projectId),
                        ManageBuildings.getBuildingName(ud.projectId),
                        ud.getBedRoomType(),
                        ud.getSpaceType(),
                        ud.getViewType(),
                        ud.getPrice(),
                        ud.getDiscount(),
                        ud.getSpecialPrice(),
                        ud.getArea(),
                        CommonUtil.getFurnishedDesc(ud.getIsFurnished()),
                        CommonUtil.getStatusDesc(ud.getStatus()));
                System.out.println();
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                System.out.println("Are you sure, do you want to delete this lookup?");
                System.out.println("1) Yes");
                System.out.println("2) No");

                int deleteConfirmation = CommonUtil.printUserValue("Enter Option: ");
                if (deleteConfirmation == 1) {
                    units.remove(findIndex(units, unitId));
                    System.out.println("Product is deleted successfully!");
                    ManageUnits.deleteFloorUnits(projectId);
                    showProjectUnits(projectId);

                    continueManageUnits("Delete", ud.projectId);

                } else if (deleteConfirmation == 2) {
                    unitActionItems();
                }
            } else {
                System.out.println("No floors available to delete. Try again");
                unitActionItems();
            }
        }

        public static void continueManageUnits(String action, int projectId) {

            // Action will be an Add, Update, Delete
            if (Objects.equals(action, "Add")) {
                CommonUtil.option = CommonUtil.confirmation("Do you want to add another unit?");
                if (CommonUtil.option == 1) {
                    addUnits();
                } else if (CommonUtil.option == 2) {
                    unitActionItems();
                } else if (CommonUtil.option == 3) {
                    CommonUtil.commonActionItems();
                } else if (CommonUtil.option > 3) {
                    System.out.println("Invalid Selection!. Please try again.");
                    unitActionItems();
                }
            } else if (Objects.equals(action, "Update")) {
                CommonUtil.option = CommonUtil.confirmation("Do you want to update another unit?");
                if (CommonUtil.option == 1) {
                    updateUnits(projectId);
                } else if (CommonUtil.option == 2) {
                    unitActionItems();
                } else if (CommonUtil.option == 3) {
                    CommonUtil.commonActionItems();
                } else if (CommonUtil.option > 3) {
                    System.out.println("Invalid Selection!. Please try again.");
                    unitActionItems();
                }
            } else if (Objects.equals(action, "Delete")) {
                CommonUtil.option = CommonUtil.confirmation("Do you want to delete another unit?");
                if (CommonUtil.option == 1) {
                    deleteUnits(projectId);
                } else if (CommonUtil.option == 2) {
                    unitActionItems();
                } else if (CommonUtil.option == 3) {
                    CommonUtil.commonActionItems();
                } else if (CommonUtil.option > 3) {
                    System.out.println("Invalid Selection!. Please try again.");
                    unitActionItems();
                }
            } else if (Objects.equals(action, "Show All")) {
                CommonUtil.option = CommonUtil.confirmation("Do you want to continue?");
                if (CommonUtil.option == 1) {
                    unitActionItems();
                } else if (CommonUtil.option == 2 || CommonUtil.option == 3) {
                    CommonUtil.commonActionItems();
                } else if (CommonUtil.option > 3) {
                    System.out.println("Invalid Selection!. Please try again.");
                    unitActionItems();
                }
            }
        }
    }
