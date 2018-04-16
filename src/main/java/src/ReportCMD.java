package src;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class ReportCMD {
    public static void execute() {
        Scanner inputScanner = InputUtils.getScanner();
        showMenu();
        try {
            int option = Integer.parseInt(inputScanner.nextLine());
            if (option == 1) {
                System.out.println(ReportDAO.reportOccupancyByHotel());
            } else if(option == 2) {
                System.out.println(ReportDAO.reportOccupancyByRoomType());
            } else if(option == 3) {
                System.out.println("Report Occupancy by Date Range");
                System.out.println("Start Date: ");
                Date startDate = new Date(new SimpleDateFormat("MM/dd/yyyy HH:mm").parse(inputScanner.nextLine()).getTime());

                System.out.println("End Date: ");
                Date endDate = new Date(new SimpleDateFormat("MM/dd/yyyy HH:mm").parse(inputScanner.nextLine()).getTime());

                System.out.println(ReportDAO.reportOccupancyByDateRange(startDate, endDate));
            } else if(option == 4) {
                System.out.println(ReportDAO.reportOccupancyByCity());
            } else if(option == 5) {
                ///////TODO
            } else if(option == 6) {
                System.out.println("Report All Staff Serving Customer");
                System.out.println("Customer ID: ");
                int customerID = Integer.parseInt(inputScanner.nextLine());

                System.out.println(ReportDAO.reportAllStaffServingCustomer(customerID));
            } else if (option == 7) {
                System.out.println("Report Hotel Revenue");
                System.out.println("Hotel ID: ");
                int hotelID = Integer.parseInt(inputScanner.nextLine());
                System.out.println("Start Date: ");
                Date startDate = new Date(new SimpleDateFormat("MM/dd/yyyy HH:mm").parse(inputScanner.nextLine()).getTime());
                System.out.println("End Date: ");
                Date endDate = new Date(new SimpleDateFormat("MM/dd/yyyy HH:mm").parse(inputScanner.nextLine()).getTime());

                System.out.println(ReportDAO.reportRevenueGeneratedByHotelForGivenDateRange(hotelID, startDate, endDate));
            }
        } catch (Exception e) {

        }
    }

    private static void showMenu() {
        System.out.println("Maintaining Service Records Menu");
        System.out.println("1. Report Occupancy by Hotel");
        System.out.println("2. Report Occupancy by Room Type");
        System.out.println("3. Report Occupancy by Date Range");
        System.out.println("4. Report Occupancy by City");
        System.out.println("5. Report Staff Info by Role");
        System.out.println("6. Report All Staff Serving Customer");
        System.out.println("7. Report Hotel Revenue");
        System.out.println("Select option: ");
    }
}
