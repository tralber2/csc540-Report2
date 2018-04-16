package src;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class BillingAcctsCMD {
    public static void execute() {
        Scanner inputScanner = InputUtils.getScanner();
        showMenu();
        try {
            int option = Integer.parseInt(inputScanner.nextLine());
            if (option == 1) {
                System.out.println("Add Billing Info: ");

                System.out.println("Social Security Number: ");
                String ssn = inputScanner.nextLine();
                System.out.println("Billing Address: ");
                String address = inputScanner.nextLine();
                System.out.println("Credit Card Number: ");
                String cardNumber = inputScanner.nextLine();
                System.out.println("Payment Method: ");
                String paymentMethod = inputScanner.nextLine();

                BillingAcctsDAO.insertBillingInfo(ssn, address, cardNumber, paymentMethod);
            } else if (option == 2) {
                System.out.println("Update Billing Info: ");

                System.out.println("Billing Info ID");
                int billingInfoID = Integer.parseInt(inputScanner.nextLine());
                System.out.println("Social Security Number: ");
                String ssn = inputScanner.nextLine();
                System.out.println("Billing Address: ");
                String address = inputScanner.nextLine();
                System.out.println("Credit Card Number: ");
                String cardNumber = inputScanner.nextLine();
                System.out.println("Payment Method: ");
                String paymentMethod = inputScanner.nextLine();

                BillingAcctsDAO.updateBillingInfo(billingInfoID, ssn, address, cardNumber, paymentMethod);
            } else if (option == 3) {
                System.out.println("Delete Billing Info: ");

                System.out.println("Billing Info ID");
                int billingInfoID = Integer.parseInt(inputScanner.nextLine());

                BillingAcctsDAO.deleteBillingInfo(billingInfoID);
            } else if (option == 4) {
                System.out.println("Link Billing Info to Customer: ");

                System.out.println("Billing Info ID");
                int billingInfoID = Integer.parseInt(inputScanner.nextLine());
                System.out.println("Customer ID");
                int customerID = Integer.parseInt(inputScanner.nextLine());

                BillingAcctsDAO.insertHasBillingInfo(customerID, billingInfoID);
            } else if (option == 5) {
                System.out.println("Unlink Billing Info from Customer: ");

                System.out.println("Billing Info ID");
                int billingInfoID = Integer.parseInt(inputScanner.nextLine());
                System.out.println("Customer ID");
                int customerID = Integer.parseInt(inputScanner.nextLine());

                BillingAcctsDAO.deleteHasBillingInfo(customerID, billingInfoID);
            } else if (option == 6) {
                System.out.println("Generate Total Bill: ");

                System.out.println("Customer ID");
                int customerID = Integer.parseInt(inputScanner.nextLine());
                Date endDate = new Date(new SimpleDateFormat("MM/dd/yyyy HH:mm").parse(inputScanner.nextLine()).getTime());

                System.out.println(BillingAcctsDAO.generateTotalBill(customerID, endDate));
            } else if (option == 7) {
                System.out.println("Generate Itemized Bill: ");

                System.out.println("Customer ID");
                int customerID = Integer.parseInt(inputScanner.nextLine());
                Date endDate = new Date(new SimpleDateFormat("MM/dd/yyyy HH:mm").parse(inputScanner.nextLine()).getTime());

                System.out.println(BillingAcctsDAO.generateItemizedBill(customerID, endDate));
            } else {

                System.out.println("Invalid value, try again");
            }
        } catch (Exception e) {

        }
    }

    private static void showMenu() {
        System.out.println("Maintaining Billing Records Menu");
        System.out.println("1. Add Billing Info");
        System.out.println("2. Update Billing Info");
        System.out.println("3. Delete Billing Info");
        System.out.println("4. Link Billing Info to Customer");
        System.out.println("5. Unlink Billing Info from Customer");
        System.out.println("6. Generate Total Bill");
        System.out.println("7. Generate Itemized Bill");
        System.out.println("Select option: ");
    }
}
