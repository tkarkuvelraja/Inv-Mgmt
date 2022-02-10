package com.company.inventory.managment;

// Import required java utilities

import java.util.Objects;
import java.util.Scanner;

public class CommonUtil {
    // Members declaration
    public static Scanner sc = new Scanner(System.in);
    public static String str;
    public static int value;
    public static boolean isValidString;
    public static String username;
    public static String password;
    public static boolean isLoggedIn;
    public static int option;

    // Methods

    // Method which display all the action items
    public static void commonActionItems() {
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.printf("%50s", "Inventory Management");
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("1) Manage Lookups");
        System.out.println("2) Manage Projects");
        System.out.println("3) Manage Buildings");
        System.out.println("4) Manage Floors");
        System.out.println("5) Manage Units");
        System.out.println("6) Project Dashboard");
        System.out.println("7) Unit Dashboard");
        System.out.println("8) Exit");

        option = CommonUtil.printUserValue("Please select option: ");
        if (option > 8) {
            do {
                System.out.println("Invalid option selected! Please try again.");
                option = CommonUtil.printUserValue("Please select option: ");
            } while (option > 8);
        }

        if (option == 1) {
            ManageLookups.lookupActionItems();
        } else if (option == 2) {
            ManageProjects.projectActionItems();
        } else if (option == 3) {
            ManageBuildings.buildingActionItems();
        } else if (option == 4) {
            ManageFloors.floorActionItems();
        } else if (option == 5) {
            ManageUnits.unitActionItems();
        } else if (option == 6) {
            Dashboards.projectDashboard();
        } else if (option == 7) {
            Dashboards.unitDashboard();
        } else if (option == 8) {
            System.out.println("Are you sure, do you want to exit from the application?");
            System.out.println("1) Yes");
            System.out.println("2) No");

            int deleteConfirmation = CommonUtil.printUserValue("Enter Option: ");
            if (deleteConfirmation == 1) {
                System.out.println("Thank you for using Inventory Management Application");
                System.exit(0);

            } else if (deleteConfirmation == 2) {
                commonActionItems();
            }

        }
    }

    // Get user inputs from the scanner text field
    public static String getUserText() {
        String str = sc.nextLine();
        return str;
    }

    // This function checks if a string contains any null values
    public static boolean isValidString(String str) {
        if (str.equals(null) && str.equals("")) {
            System.out.println("Please enter valid inputs!");
            isValidString = false;
        } else {
            isValidString = true;
        }
        return isValidString;
    }

    // Function to print user text
    public static String printUserText(String text) {
        System.out.println(text);

        do {
            str = getUserText();
            isValidString = isValidString(str);
        } while (!isValidString);

        return str;
    }

    // Get user inputs from the scanner integer field and check values are integer
    public static int printUserValue(String text) {

        boolean flag = false;

        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.println(text);
                value = sc.nextInt();
                flag = false;
            } catch (Exception e) {
                System.out.println("Please enter only integer value.." + e);
                flag = true;
            }
        }
        while (flag);

        return value;
    }

    // The function checks whether the credentials entered by the user are valid
    public static boolean isValidCredentials(String username, String password) {
        return username.equals("Admin") && password.equals("Damac$123");
    }

    // Confirmation window
    public static int confirmation(String text) {

        System.out.println(text);
        System.out.println("1) Yes");
        System.out.println("2) No");
        System.out.println("3) Exit");

        option = printUserValue("Please select option: ");
        if (option > 3) {
            do {
                System.out.println("Invalid option selected! Please try again.");
                option = printUserValue("Please select option: ");
            } while (option > 3);
        }
        return option;
    }

    // Based on the module and option selections, the actual operations will be carried out
    public static void selectedItemAction(int actionItem, String module) {
        //if (Objects.equals(module, "Manage Lookups")) {
            if (module.equalsIgnoreCase("Manage Lookups")) {
            if (actionItem == 1) {
                ManageLookups.lookupActionItems();
            } else if (actionItem == 2) {
                commonActionItems();
            } else if (actionItem == 3) {
                System.out.println("Thank you so much for using the application");
                System.exit(0);
            }
        } else if (Objects.equals(module, "Lookup Action Items")) {
            if (actionItem == 1) {
                ManageLookups.addLookups();
            } else if (actionItem == 2) {
                ManageLookups.updateLookups();
            } else if (actionItem == 3) {
                ManageLookups.deleteLookups();
            } else if (actionItem == 4) {
                ManageLookups.showAllLookups();
                ManageLookups.continueManageLookupsLoop("Show All");
            } else if (actionItem == 5) {
                commonActionItems();
            }
        } else if (Objects.equals(module, "Project Action Items")) {
            if (actionItem == 1) {
                ManageProjects.addProjects();
            } else if (actionItem == 2) {
                ManageProjects.updateProjects();
            } else if (actionItem == 3) {
                ManageProjects.deleteProjects();
            } else if (actionItem == 4) {
                ManageProjects.showAllProjects();
                ManageProjects.continueManageProjects("Show All");
            } else if (actionItem == 5) {
                commonActionItems();
            }
        } else if (Objects.equals(module, "Building Action Items")) {
            if (actionItem == 1) {
                ManageBuildings.addBuildings();
            } else if (actionItem == 2) {
                ManageBuildings.updateBuildings();
            } else if (actionItem == 3) {
                ManageBuildings.deleteBuildings();
            } else if (actionItem == 4) {
                ManageBuildings.showAllBuildings();
                ManageBuildings.continueManageBuildings("Show All");
            } else if (actionItem == 5) {
                commonActionItems();
            }
        } else if (Objects.equals(module, "Floor Action Items")) {
            if (actionItem == 1) {
                ManageFloors.addFloors();
            } else if (actionItem == 2) {
                int projectId = CommonUtil.printUserValue("Enter Project Id: ");
                ManageFloors.updateFloors(projectId);
            } else if (actionItem == 3) {
                int projectId = CommonUtil.printUserValue("Enter Project Id: ");
                ManageFloors.deleteFloors(projectId);
            } else if (actionItem == 4) {
                int projectId = CommonUtil.printUserValue("Enter Project Id: ");
                ManageFloors.showProjectFloors(projectId);
                ManageFloors.continueManageFloors("Show All", projectId);
            } else if (actionItem == 5) {
                commonActionItems();
            }
        } else if (Objects.equals(module, "Unit Action Items")) {
            if (actionItem == 1) {
                ManageUnits.addUnits();
            } else if (actionItem == 2) {
                int projectId = CommonUtil.printUserValue("Enter Project Id: ");
                ManageUnits.updateUnits(projectId);
            } else if (actionItem == 3) {
                int projectId = CommonUtil.printUserValue("Enter Project Id: ");
                ManageUnits.deleteUnits(projectId);
            } else if (actionItem == 4) {
                int projectId = CommonUtil.printUserValue("Enter Project Id: ");
                ManageUnits.showProjectUnits(projectId);
                ManageUnits.continueManageUnits("Show All", projectId);
            } else if (actionItem == 5) {
                commonActionItems();
            }
        }
    }

    public static String getStatusDesc(String statusCode) {
        return statusCode.equalsIgnoreCase("A") ? "Active" : "Inactive";
    }

    public static String getFurnishedDesc(String furnishedFlag) {
        return furnishedFlag.equalsIgnoreCase("F") ? "Fully Furnished" :
                furnishedFlag.equalsIgnoreCase("S") ? "Semi Furnished" : "Un Furnished";
    }


    public static String printInitials(String text) {
        StringBuilder initials = new StringBuilder();
        String[] myString = text.split(" ");
        for (String str : myString) {
            initials.append(str.charAt(0));
        }
        // System.out.println(initials.trim());
        return initials.toString().trim();
    }
}

