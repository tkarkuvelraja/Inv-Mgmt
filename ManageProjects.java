package com.company.inventory.managment;

import java.util.ArrayList;
import java.util.List;
import java.lang.String;
import java.util.Objects;

public class ManageProjects {

    public static List<ProjectDetails> projects = new ArrayList<>();

    // Method which display all project action items
    public static void projectActionItems() {
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.printf("%50s", "Manage Projects");
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("1) Add Project");
        System.out.println("2) Update Project");
        System.out.println("3) Delete Project");
        System.out.println("4) Show All the Project");
        System.out.println("5) Exit");

        CommonUtil.option = CommonUtil.printUserValue("Please select option: ");
        if (CommonUtil.option > 5) {
            do {
                System.out.println("Invalid option selected! Please try again.");
                CommonUtil.option = CommonUtil.printUserValue("Please select option: ");
            } while (CommonUtil.option > 5);
        }
        CommonUtil.selectedItemAction(CommonUtil.option, "Project Action Items"); // Setup lookup values
    }

    public static void addProjects() {

        String projectCode = CommonUtil.printUserText("Enter Project Code: ");
        String projectName = CommonUtil.printUserText("Enter Project Name: ");
        double area = CommonUtil.printUserValue("Enter Area: ");
        String uom = CommonUtil.printUserText("Enter Project UOM: ");
        String currency = CommonUtil.printUserText("Enter Project Currency: ");
        String city = CommonUtil.printUserText("Enter Project City: ");
        String district = CommonUtil.printUserText("Enter Project District: ");
        String country = CommonUtil.printUserText("Enter Project Country: ");
        String uniqueProjectName = projectCode;
        int projectId = projects.size() + 1;

        ProjectDetails projectDetails = new ProjectDetails(projectId,
                projectCode, projectName, uniqueProjectName, area, uom, currency, city, district, country);

        CommonUtil.option = CommonUtil.confirmation("Do you want to save this transaction?");

        if (CommonUtil.option == 1) {
            projects.add(projectDetails);
            System.out.println("Project " + projectDetails.projectName + " is added!");
            showAllProjects();
            continueManageProjects("Add");
        } else if (CommonUtil.option == 2) {
            projectActionItems();
        } else if (CommonUtil.option == 3) {
            System.out.println("Thank you so much for using the application");
            System.exit(0);
        }

    }

    public static void showAllProjects() {
        if (projects.size() > 0) {
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%15s %15s %30s %30s %30s %30s %30s %30s %30s %30s", "Project Id", "Project Code", "Project Name", "Area", "UOM", "Currency", "City", "District", "Country", "Status");
            System.out.println();
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            for (ProjectDetails list : projects) {
                System.out.format("%15s %15s %30s %30s %30s %30s %30s %30s %30s %30s", list.getProjectId(), list.getProjectCode(), list.getProjectName(), list.getArea(), list.getUom(), list.getCurrency(), list.getCity(), list.getDistrict(), list.getCountry(), CommonUtil.getStatusDesc(list.getStatus()));
                System.out.println();
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("No project available to display. Try again!");
            projectActionItems();
        }
    }

    public static void updateProjects() {
        if (projects.size() > 0) {
            showAllProjects();
            int lookupId = CommonUtil.printUserValue("Enter Project Id: ");
            System.out.printf("%15s %15s %30s %30s %30s %30s %30s %30s %30s %30s", "Project Id", "Project Code", "Project Name", "Area", "UOM", "Currency", "City", "District", "Country", "Status");
            System.out.println();
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            ProjectDetails pd = projects.get(findIndex(projects, lookupId));
            System.out.format("%15s %15s %30s %30s %30s %30s %30s %30s %30s %30s", pd.getProjectId(), pd.getProjectCode(),
                    pd.getProjectName(), pd.getArea(), pd.getUom(), pd.getCurrency(),
                    pd.getCity(), pd.getDistrict(), pd.getCountry(), CommonUtil.getStatusDesc(pd.getStatus()));
            System.out.println();
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            System.out.println("Kindly confirm which field do you want to update?");
            System.out.println("1) Project Name");
            System.out.println("2) Area");
            System.out.println("3) UOM");
            System.out.println("4) Currency");
            System.out.println("5) City");
            System.out.println("6) District");
            System.out.println("7) Country");
            System.out.println("8) In activate Project");

            int option = CommonUtil.printUserValue("Enter Option: ");

            if (option == 1) {
                updateProjectName(pd.projectId - 1);
            } else if (option == 2) {
                updateArea(pd.projectId - 1);
            } else if (option == 3) {
                updateUom(pd.projectId - 1);
            } else if (option == 4) {
                updateCurrency(pd.projectId - 1);
            } else if (option == 5) {
                updateCity(pd.projectId - 1);
            } else if (option == 6) {
                updateDistrict(pd.projectId - 1);
            } else if (option == 7) {
                updateCountry(pd.projectId - 1);
            } else if (option == 8) {
                updateProjectStatus(pd.projectId - 1);
            } else if (option > 8) {
                System.out.println("Invalid Selection!. Please try again.");
                projectActionItems();
            }

        } else {
            System.out.println("No projects available to update. Try again");
            projectActionItems();
        }
    }

    public static void updateProjectName(int projectId) {
        ProjectDetails pd = projects.get(projectId);
        System.out.println("Current Project Name : " + pd.getProjectName());
        String newProjectName = CommonUtil.printUserText("Enter New Project Name:");
        pd.setProjectName(newProjectName);
        String newProjectCode = CommonUtil.printInitials(newProjectName);
        pd.setProjectCode(newProjectCode);
        System.out.println("Record updated successfully!");
        showAllProjects();
        continueManageProjects("Update");
    }

    public static void updateArea(int projectId) {
        ProjectDetails pd = projects.get(projectId);
        System.out.println("Current Project Area : " + pd.getArea());
        int newProjectArea = CommonUtil.printUserValue("Enter New Project Area:");
        pd.setArea(newProjectArea);
        System.out.println("Record updated successfully!");
        showAllProjects();
        continueManageProjects("Update");
    }

    public static void updateCurrency(int projectId) {
        ProjectDetails pd = projects.get(projectId);
        System.out.println("Current Project Currency : " + pd.getCurrency());
        String newProjectCurrency = CommonUtil.printUserText("Enter New Project Currency:");
        pd.setCurrency(newProjectCurrency);
        System.out.println("Record updated successfully!");
        showAllProjects();
        continueManageProjects("Update");
    }

    public static void updateCity(int projectId) {
        ProjectDetails pd = projects.get(projectId);
        System.out.println("Current Project City : " + pd.getCity());
        String newProjectCity = CommonUtil.printUserText("Enter New Project City:");
        pd.setCity(newProjectCity);
        System.out.println("Record updated successfully!");
        showAllProjects();
        continueManageProjects("Update");
    }

    public static void updateDistrict(int projectId) {
        ProjectDetails pd = projects.get(projectId);
        System.out.println("Current Project District : " + pd.getDistrict());
        String newProjectDistrict = CommonUtil.printUserText("Enter New Project District:");
        pd.setDistrict(newProjectDistrict);
        System.out.println("Record updated successfully!");
        showAllProjects();
        continueManageProjects("Update");
    }

    public static void updateCountry(int projectId) {
        ProjectDetails pd = projects.get(projectId);
        System.out.println("Current Project Country : " + pd.getCountry());
        String newProjectCountry = CommonUtil.printUserText("Enter New Project Country:");
        pd.setCountry(newProjectCountry);
        System.out.println("Record updated successfully!");
        showAllProjects();
        continueManageProjects("Update");
    }

    public static void updateUom(int projectId) {
        ProjectDetails pd = projects.get(projectId);
        System.out.println("Current Project UOM : " + pd.getUom());
        String newProjectUom = CommonUtil.printUserText("Enter New Project UOM:");
        pd.setUom(newProjectUom);
        System.out.println("Record updated successfully!");
        showAllProjects();
        continueManageProjects("Update");
    }

    public static void updateProjectStatus(int projectId) {
        ProjectDetails pd = projects.get(projectId);
        pd.setStatus("I");
        System.out.println("Project Ìn activated successfully!");
        showAllProjects();
        continueManageProjects("Update");
    }

    public static void deleteProjects() {
        if (projects.size() > 0) {
            showAllProjects();
            int projectId = CommonUtil.printUserValue("Enter Project Id: ");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%15s %15s %30s %30s %30s %30s %30s %30s %30s %30s", "Project Id", "Project Code", "Project Name", "Area", "UOM", "Currency", "City", "District", "Country", "Status");
            System.out.println();
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            ProjectDetails pd = projects.get(findIndex(projects, projectId));
            System.out.format("%15s %15s %30s %30s %30s %30s %30s %30s %30s %30s", pd.getProjectId(), pd.getProjectCode(),
                    pd.getProjectName(), pd.getArea(), pd.getUom(), pd.getCurrency(),
                    pd.getCity(), pd.getDistrict(), pd.getCountry(), CommonUtil.getStatusDesc(pd.getStatus()));
            System.out.println();
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            System.out.println("Are you sure, do you want to delete this lookup?");
            System.out.println("1) Yes");
            System.out.println("2) No");

            int deleteConfirmation = CommonUtil.printUserValue("Enter Option: ");
            if (deleteConfirmation == 1) {
                projects.remove(findIndex(projects, projectId));
                System.out.println("Product is deleted successfully!");
                showAllProjects();

                continueManageProjects("Delete");

            } else if (deleteConfirmation == 2) {
                projectActionItems();
            }
        } else {
            System.out.println("No projects available to delete. Try again");
            projectActionItems();
        }
    }

    public static void continueManageProjects(String action) {

        // Action will be an Add, Update, Delete
        if (Objects.equals(action, "Add")) {
            CommonUtil.option = CommonUtil.confirmation("Do you want to add another project?");
            if (CommonUtil.option == 1) {
                addProjects();
            } else if (CommonUtil.option == 2) {
                projectActionItems();
            } else if (CommonUtil.option == 3) {
                CommonUtil.commonActionItems();
            } else if (CommonUtil.option > 3) {
                System.out.println("Invalid Selection!. Please try again.");
                projectActionItems();
            }
        } else if (Objects.equals(action, "Update")) {
            CommonUtil.option = CommonUtil.confirmation("Do you want to update another project?");
            if (CommonUtil.option == 1) {
                updateProjects();
            } else if (CommonUtil.option == 2) {
                projectActionItems();
            } else if (CommonUtil.option == 3) {
                CommonUtil.commonActionItems();
            } else if (CommonUtil.option > 3) {
                System.out.println("Invalid Selection!. Please try again.");
                projectActionItems();
            }
        } else if (Objects.equals(action, "Delete")) {
            CommonUtil.option = CommonUtil.confirmation("Do you want to delete another project?");
            if (CommonUtil.option == 1) {
                deleteProjects();
            } else if (CommonUtil.option == 2) {
                projectActionItems();
            } else if (CommonUtil.option == 3) {
                CommonUtil.commonActionItems();
            } else if (CommonUtil.option > 3) {
                System.out.println("Invalid Selection!. Please try again.");
                projectActionItems();
            }
        } else if (Objects.equals(action, "Show All")) {
            CommonUtil.option = CommonUtil.confirmation("Do you want to continue?");
            if (CommonUtil.option == 1) {
                projectActionItems();
            } else if (CommonUtil.option == 2 || CommonUtil.option == 3) {
                CommonUtil.commonActionItems();
            } else if (CommonUtil.option > 3) {
                System.out.println("Invalid Selection!. Please try again.");
                projectActionItems();
            }
        }
    }

    public static int findIndex(List<ProjectDetails> projectDetailsList, int id) {
        int index = 0;

        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getProjectId() == id) {
                index = i;
                break;
            }
        }
        return index;
    }

    public static String getProjectName (int projectId)
    {
        ProjectDetails pd = projects.get(findIndex(projects, projectId));
        return pd.getProjectName();
    }

    public static int getProjectCount (int projectId)
    {
        int count = 0;

        for (ProjectDetails project : projects) {
            if (project.getProjectId() == projectId) {
                count = (count + 1);
                //break;
            }
        }
        return count;
    }
    //return 0;
}
