package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        ConnectionUtils.init();
        InputUtils.init();

        executeMain();

        ConnectionUtils.finish();
        InputUtils.finish();
    }



    private static void executeMain() {
        Scanner inputScanner = InputUtils.getScanner();
        while(true) {
            showMainMenu();
            try {
                // parse the user's input. If it isn't an int, try again
                int option = Integer.parseInt(inputScanner.nextLine());
                switch (option) {
                    case 1:
                        InfoProcCMD.execute();
                        break;
                    case 2:
                        ServiceRecordsCMD.execute();
                        break;
                    case 3:
                        BillingAcctsCMD.execute();
                        break;
                    case 4:
                        ReportCMD.execute();
                        break;
                    default:
                        System.out.println("Invalid value, try again");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Invalid value, try again");
            }

        }
    }

    private static void showMainMenu() {
        System.out.println("Welcome to Wolf Inn");
        System.out.println("Please select one of the options below:");
        System.out.println("1. Information Processing");
        System.out.println("2. Maintaining Service Records");
        System.out.println("3. Maintaining Billing Accounts");
        System.out.println("4. Reports");
    }


}
