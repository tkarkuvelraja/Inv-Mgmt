package com.company.inventory.managment;

import java.util.ArrayList;
import java.util.List;
import java.lang.String;
import java.util.Objects;

public class ManageLookups {

    public static List<LookupDetails> lookups = new ArrayList<>();

    public static void addLookups() {

        String lookupValue = CommonUtil.printUserText("Enter Lookup Value: ");
        String lookupContext = CommonUtil.printUserText("Enter Lookup Context");
        String lookupCode = CommonUtil.printInitials(lookupValue);
        int lookupId = lookups.size() + 1;

        LookupDetails lookupDetails = new LookupDetails(lookupId, lookupContext, lookupValue, lookupCode);

        CommonUtil.option = CommonUtil.confirmation("Do you want to save this transaction?");

        if (CommonUtil.option == 1) {
            lookups.add(lookupDetails);
            System.out.println("Lookup " + lookupDetails.lookupValue + " is added!");
            showAllLookups();
            continueManageLookupsLoop("Add");
        } else if (CommonUtil.option == 2) {
            lookupActionItems();
        } else if (CommonUtil.option == 3) {
            System.out.println("Thank you so much for using the application");
            System.exit(0);
        }

    }
    //return 0;

    // Method which display all lookup action items
    public static void lookupActionItems() {
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.printf("%50s", "Manage Lookups");
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("1) Add Lookup");
        System.out.println("2) Update Lookup");
        System.out.println("3) Delete Lookup");
        System.out.println("4) Show All the Lookups");
        System.out.println("5) Exit");

        CommonUtil.option = CommonUtil.printUserValue("Please select option: ");
        if (CommonUtil.option > 5) {
            do {
                System.out.println("Invalid option selected! Please try again.");
                CommonUtil.option = CommonUtil.printUserValue("Please select option: ");
            } while (CommonUtil.option > 5);
        }
        CommonUtil.selectedItemAction(CommonUtil.option, "Lookup Action Items"); // Setup lookup values
    }

    public static void updateLookupValue(int lookupId) {
        LookupDetails ld = lookups.get(lookupId);
        System.out.println("Current Lookup Value : " + ld.getLookupValue());
        String newLookupValue = CommonUtil.printUserText("Enter New Lookup Value:");
        ld.setLookupValue(newLookupValue);
        String newLookupCode = CommonUtil.printInitials(newLookupValue);
        ld.setLookupCode(newLookupCode);
        System.out.println("Record updated successfully!");
        showAllLookups();
        continueManageLookupsLoop("Update");
    }

    public static void updateLookupStatus(int lookupId) {
        LookupDetails ld = lookups.get(lookupId);
        ld.setLookupStatus("I");
        System.out.println("Lookup Ìn activated successfully!");
        showAllLookups();
        continueManageLookupsLoop("Update");
    }

    public static void updateLookups() {

        if (lookups.size() > 0) {
            showAllLookups();
            int lookupId = CommonUtil.printUserValue("Enter Lookup Id: ");
            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.printf("%10s %10s %10s %8s %30s", "Lookup Id", "Lookup Code", "Lookup Value", "Lookup Context", "Status");
            System.out.println();
            System.out.println("---------------------------------------------------------------------------------------------");
            LookupDetails ld = lookups.get(findIndex(lookups, lookupId));
            System.out.format("%5s %12s %8s %10s %30s", ld.getLookupId(), ld.getLookupCode(), ld.getLookupValue(), ld.getLookupContext(),
                    ld.getLookupStatus());
            System.out.println();
            System.out.println("----------------------------------------------------------------------------------------------");

            System.out.println("Kindly confirm which field do you want to update?");
            System.out.println("1) Lookup Value");
            System.out.println("2) In activate Lookup");

            int option = CommonUtil.printUserValue("Enter Option: ");

            if (option == 1) {
                updateLookupValue(ld.lookupId - 1);
            } else if (option == 2) {
                updateLookupStatus(ld.lookupId - 1);
            } else if (option > 2) {
                System.out.println("Invalid Selection!. Please try again.");
                lookupActionItems();
            }

        } else {
            System.out.println("No lookups available to update. Try again");
            lookupActionItems();
        }
    }

    public static void deleteLookups() {
        if (lookups.size() > 0) {
            showAllLookups();
            int lookupId = CommonUtil.printUserValue("Enter Lookup Id: ");
            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.printf("%10s %10s %10s %8s %30s", "Lookup Id", "Lookup Code", "Lookup Value", "lookup Context", "Status");
            System.out.println();
            System.out.println("---------------------------------------------------------------------------------------------");
            LookupDetails ld = lookups.get(findIndex(lookups, lookupId));
            System.out.format("%5s %12s %8s %10s %30s", ld.getLookupId(), ld.getLookupCode(), ld.getLookupValue(), ld.getLookupContext(),
                    ld.getLookupStatus());
            System.out.println();
            System.out.println("----------------------------------------------------------------------------------------------");

            System.out.println("Are you sure, do you want to delete this lookup?");
            System.out.println("1) Yes");
            System.out.println("2) No");

            int deleteConfirmation = CommonUtil.printUserValue("Enter Option: ");
            if (deleteConfirmation == 1) {
                lookups.remove(findIndex(lookups, lookupId));
                System.out.println("Product is deleted successfully!");
                showAllLookups();

                continueManageLookupsLoop("Delete");


            } else if (deleteConfirmation == 2) {
                lookupActionItems();
            }
        } else {
            System.out.println("No lookups available to delete. Try again");
            lookupActionItems();
        }
    }

    public static void showAllLookups() {
        if (lookups.size() > 0) {
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%15s %15s %30s %30s %30s", "Lookup Id", "Lookup Code", "Lookup Value", "Lookup Context", "Lookup Status");
            System.out.println();
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
            for (LookupDetails list : lookups) {
                System.out.format("%15s %15s %30s %30s %30s", list.getLookupId(), list.getLookupCode(), list.getLookupValue(),
                        list.getLookupContext(), CommonUtil.getStatusDesc(list.getLookupStatus()));
                System.out.println();
            }
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("No lookups available to display. Try again!");
            lookupActionItems();
        }
    }

    public static void continueManageLookupsLoop(String action) {

        // Action will be Add, Update, Delete
        if (Objects.equals(action, "Add")) {
            CommonUtil.option = CommonUtil.confirmation("Do you want to add another lookup?");
            if (CommonUtil.option == 1) {
                addLookups();
            } else if (CommonUtil.option == 2) {
                lookupActionItems();
            } else if (CommonUtil.option == 3) {
                CommonUtil.commonActionItems();
            } else if (CommonUtil.option > 3) {
                System.out.println("Invalid Selection!. Please try again.");
                lookupActionItems();
            }
        } else if (Objects.equals(action, "Update")) {
            CommonUtil.option = CommonUtil.confirmation("Do you want to update another lookup?");
            if (CommonUtil.option == 1) {
                updateLookups();
            } else if (CommonUtil.option == 2) {
                lookupActionItems();
            } else if (CommonUtil.option == 3) {
                CommonUtil.commonActionItems();
            } else if (CommonUtil.option > 3) {
                System.out.println("Invalid Selection!. Please try again.");
                lookupActionItems();
            }
        } else if (Objects.equals(action, "Delete")) {
            CommonUtil.option = CommonUtil.confirmation("Do you want to delete another lookup?");
            if (CommonUtil.option == 1) {
                deleteLookups();
            } else if (CommonUtil.option == 2) {
                lookupActionItems();
            } else if (CommonUtil.option == 3) {
                CommonUtil.commonActionItems();
            } else if (CommonUtil.option > 3) {
                System.out.println("Invalid Selection!. Please try again.");
                lookupActionItems();
            }
        } else if (Objects.equals(action, "Show All")) {
            CommonUtil.option = CommonUtil.confirmation("Do you want to continue?");
            if (CommonUtil.option == 1) {
                lookupActionItems();
            } else if (CommonUtil.option == 2 || CommonUtil.option == 3) {
                CommonUtil.commonActionItems();
            } else if (CommonUtil.option > 3) {
                System.out.println("Invalid Selection!. Please try again.");
                lookupActionItems();
            }
        }
    }

    public static int findIndex(List<LookupDetails> lookupDetailsList, int id) {
        int index = 0;

        for (int i = 0; i < lookups.size(); i++) {
            if (lookups.get(i).getLookupId() == id) {
                index = i;
                break;
            }
        }
        return index;
    }
}