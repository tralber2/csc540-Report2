package src;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class ServiceRecordsCMD {
    public static void execute() {
        Scanner inputScanner = InputUtils.getScanner();
        showMenu();
        try {
            int option = Integer.parseInt(inputScanner.nextLine());
            if (option == 1) {
                System.out.println("Purchase Service");

                System.out.println("Service ID: ");
                int serviceID = Integer.parseInt(inputScanner.nextLine());
                System.out.println("Staff ID: ");
                int staffID = Integer.parseInt(inputScanner.nextLine());
                System.out.println("Customer ID: ");
                int customerID = Integer.parseInt(inputScanner.nextLine());
                System.out.println("Purchase Date: ");
                Date purchaseDate = new Date(new SimpleDateFormat("MM/dd/yyyy HH:mm").parse(inputScanner.nextLine()).getTime());

                ServiceRecordsDAO.purchaseService(serviceID, staffID, customerID, purchaseDate);
            } else if (option == 2) {
                System.out.println("Update Service Record");

                System.out.println("Purchase ID: ");
                int purchaseID = Integer.parseInt(inputScanner.nextLine());
                System.out.println("Service ID: ");
                int serviceID = Integer.parseInt(inputScanner.nextLine());
                System.out.println("Staff ID: ");
                int staffID = Integer.parseInt(inputScanner.nextLine());
                System.out.println("Customer ID: ");
                int customerID = Integer.parseInt(inputScanner.nextLine());
                System.out.println("Purchase Date: ");
                Date purchaseDate = new Date(new SimpleDateFormat("MM/dd/yyyy HH:mm").parse(inputScanner.nextLine()).getTime());

                ServiceRecordsDAO.updateServiceRecord(purchaseID, serviceID, staffID, customerID, purchaseDate);
            } else {
                System.out.println("Invalid value, try again");
            }
        } catch (Exception e) {

        }
    }

    private static void showMenu() {
        System.out.println("Maintaining Service Records Menu");
        System.out.println("1. Purchase Service");
        System.out.println("2. Update Service Record");
        System.out.println("Select option: ");
    }
}
