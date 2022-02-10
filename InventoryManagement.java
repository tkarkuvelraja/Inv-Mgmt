package com.company.inventory.managment;

/*
@author: Karkuvelraja Thangamariappan
@Created On: 01-Feb-2022
@Objective: Inventory Management (Login Window, Managing Lookups, Managing Entities (Projects, Building,
Floors, Units), Managing Units and Dashboards (Project, Building and Units)
*/

// Import required java utilities

public class InventoryManagement {

    public static void main(String[] args) {

        // Login window - Execute this until login is successful
        do {
            CommonUtil.username = CommonUtil.printUserText("Enter Username: "); // Get username
            CommonUtil.password = CommonUtil.printUserText("Enter Password"); // Get password
            CommonUtil.isLoggedIn = CommonUtil.isValidCredentials(CommonUtil.username, CommonUtil.password); // Check credentials are valid
            if (CommonUtil.isLoggedIn) {
                System.out.println("Login successful! Go ahead");
                System.out.println("Before creating entities, make sure to setup lookups!");
                CommonUtil.option = CommonUtil.confirmation("Do you want to add lookups?");
                CommonUtil.selectedItemAction(CommonUtil.option, "Manage Lookups"); // Setup lookup values
            } else {
                System.out.println("Invalid Credentials! Please try again");
            }
        } while (!CommonUtil.isLoggedIn);
    }
}
