package src;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class InfoProcCMD {
    public static void execute() {
        Scanner inputScanner = InputUtils.getScanner();
        showMenu();
        try {
            int option = Integer.parseInt(inputScanner.nextLine());
            if (option == 1) {
                System.out.println("Enter Hotel Info");

                System.out.println("Name: ");
                String hotelName = inputScanner.nextLine();
                System.out.println("Phone Number: ");
                String phone = inputScanner.nextLine();
                System.out.println("Address: ");
                String address = inputScanner.nextLine();
                System.out.println("City");
                String city = inputScanner.nextLine();
                System.out.println("Manager ID: ");
                int managerID = Integer.parseInt(inputScanner.nextLine());

                InfoProcDAO.insertHotel(hotelName, phone, address, city, managerID);

            } else if (option == 2) {
                System.out.println("Update Hotel Info");

                System.out.println("Hotel ID");
                int hotelID = Integer.parseInt(inputScanner.nextLine());
                System.out.println("Name: ");
                String hotelName = inputScanner.nextLine();
                System.out.println("Phone Number: ");
                String phone = inputScanner.nextLine();
                System.out.println("Address: ");
                String address = inputScanner.nextLine();
                System.out.println("City: ");
                String city = inputScanner.nextLine();
                System.out.println("Manager ID: ");
                int managerID = Integer.parseInt(inputScanner.nextLine());

                InfoProcDAO.updateHotel(hotelID, hotelName, phone, address, city, managerID);

            } else if (option == 3) {
                System.out.println("Delete Hotel Info");

                System.out.println("Hotel ID: ");
                int hotelID = Integer.parseInt(inputScanner.nextLine());

                InfoProcDAO.deleteHotel(hotelID);
            } else if (option == 4) {
                System.out.println("Enter Room Info");

                System.out.println("Hotel ID: ");
                int hotelID = Integer.parseInt(inputScanner.nextLine());
                System.out.println("Room Number: ");
                int roomNum = Integer.parseInt(inputScanner.nextLine());
                System.out.println("Category: ");
                String category = inputScanner.nextLine();
                System.out.println("Max Occupancy: ");
                int maxOccupancy = Integer.parseInt(inputScanner.nextLine());
                System.out.println("Nightly Rate: ");
                int nightlyRate = Integer.parseInt(inputScanner.nextLine());

                InfoProcDAO.insertRoom(roomNum, hotelID, category, maxOccupancy, nightlyRate);
            } else if (option == 5) {
                System.out.println("Update Room Info");

                System.out.println("Room ID: ");
                int roomID = Integer.parseInt(inputScanner.nextLine());
                System.out.println("Category: ");
                String category = inputScanner.nextLine();
                System.out.println("Max Occupancy: ");
                int maxOccupancy = Integer.parseInt(inputScanner.nextLine());
                System.out.println("Nightly Rate: ");
                int nightlyRate = Integer.parseInt(inputScanner.nextLine());

                InfoProcDAO.updateRoom(roomID, category, maxOccupancy, nightlyRate);
            } else if (option == 6) {
                System.out.println("Delete Room Info");

                System.out.println("Room ID: ");
                int roomID = Integer.parseInt(inputScanner.nextLine());

                InfoProcDAO.deleteRoom(roomID);
            } else if (option == 7) {
                System.out.println("Enter Staff Info");

                System.out.println("Name: ");
                String name = inputScanner.nextLine();
                System.out.println("Age: ");
                int age = Integer.parseInt(inputScanner.nextLine());
                System.out.println("Department: ");
                String department = inputScanner.nextLine();
                System.out.println("Phone Number: ");
                String phone = inputScanner.nextLine();
                System.out.println("Address: ");
                String address = inputScanner.nextLine();
                System.out.println("Job Title: ");
                String jobTitle = inputScanner.nextLine();

                InfoProcDAO.insertStaff(name, age, department, phone, address, jobTitle);
            } else if (option == 8) {
                System.out.println("Update Staff Info");

                System.out.println("Staff ID: ");
                int staffID = Integer.parseInt(inputScanner.nextLine());
                System.out.println("Name: ");
                String name = inputScanner.nextLine();
                System.out.println("Age: ");
                int age = Integer.parseInt(inputScanner.nextLine());
                System.out.println("Department: ");
                String department = inputScanner.nextLine();
                System.out.println("Phone Number: ");
                String phone = inputScanner.nextLine();
                System.out.println("Address: ");
                String address = inputScanner.nextLine();
                System.out.println("Job Title: ");
                String jobTitle = inputScanner.nextLine();

                InfoProcDAO.updateStaff(staffID, name, age, department, phone, address, jobTitle);
            } else if (option == 9) {
                System.out.println("Delete Staff Info");

                System.out.println("Staff ID: ");
                int staffID = Integer.parseInt(inputScanner.nextLine());

                InfoProcDAO.deleteStaff(staffID);
            } else if (option == 10) {
                System.out.println("Enter Customer Info");

                System.out.println("Name: ");
                String name = inputScanner.nextLine();
                System.out.println("Date of Birth: ");
                String dateOfBirth = inputScanner.nextLine();
                System.out.println("Phone Number: ");
                String phone = inputScanner.nextLine();
                System.out.println("Email: ");
                String email = inputScanner.nextLine();

                InfoProcDAO.insertCustomer(name, dateOfBirth, phone, email);
            } else if (option == 11) {
                System.out.println("Update Customer Info");

                System.out.println("Customer ID: ");
                int customerID = Integer.parseInt(inputScanner.nextLine());
                System.out.println("Name: ");
                String name = inputScanner.nextLine();
                System.out.println("Date of Birth: ");
                String dateOfBirth = inputScanner.nextLine();
                System.out.println("Phone Number: ");
                String phone = inputScanner.nextLine();
                System.out.println("Email: ");
                String email = inputScanner.nextLine();

                InfoProcDAO.updateCustomer(customerID, name, dateOfBirth, phone, email);
            } else if (option == 12) {
                System.out.println("Delete Customer Info");

                System.out.println("Customer ID: ");
                int customerID = Integer.parseInt(inputScanner.nextLine());

                InfoProcDAO.deleteCustomer(customerID);
            } else if (option == 13) {
                System.out.println("Check room number availability");

                System.out.println("Hotel ID: ");
                int hotelID = Integer.parseInt(inputScanner.nextLine());
                System.out.println("Room Number : ");
                int roomNumber = Integer.parseInt(inputScanner.nextLine());

                if(InfoProcDAO.checkRoomNumberAvailability(hotelID, roomNumber)) {
                    System.out.println("Room IS available.");
                } else {
                    System.out.println("Room is NOT available.");
                }
            } else if (option == 14) {
                System.out.println("Check room category availability");

                System.out.println("Category: ");
                String category = inputScanner.nextLine();


                InfoProcDAO.checkRoomTypeAvailability(category);
            } else if (option == 15) {
                System.out.println("Assign room to customer");

                System.out.println("Hotel ID: ");
                int hotelID = Integer.parseInt(inputScanner.nextLine());
                System.out.println("Room ID : ");
                int roomID = Integer.parseInt(inputScanner.nextLine());
                System.out.println("Customer ID: ");
                int customerID = Integer.parseInt(inputScanner.nextLine());
                System.out.println("Number of Guests: ");
                int numberOfGuests = Integer.parseInt(inputScanner.nextLine());
                System.out.println("Start Datetime (mm/dd/yyyy HH:mm): ");
                Date startDate = new Date(new SimpleDateFormat("MM/dd/yyyy HH:mm").parse(inputScanner.nextLine()).getTime());
                System.out.println("Customer has Hotel Credit Card (T/F): ");
                String hasDiscountString = inputScanner.nextLine();
                boolean hasDiscount = hasDiscountString.equalsIgnoreCase("T");

                InfoProcDAO.assignRoomToCustomer(hotelID, roomID, customerID, numberOfGuests, startDate, hasDiscount);
            } else if (option == 16) {
                System.out.println("Release room from customer");

                System.out.println("Room ID : ");
                int roomID = Integer.parseInt(inputScanner.nextLine());
                System.out.println("Customer ID: ");
                int customerID = Integer.parseInt(inputScanner.nextLine());

                InfoProcDAO.releaseRoom(roomID, customerID);
            } else {
                System.out.println("Invalid value, try again");

            }
        } catch (Exception e) {
            System.out.println("Invalid value, try again");
            e.printStackTrace();
        }
    }

    private static void showMenu() {
        System.out.println("Information Processing Menu");
        System.out.println("1. Enter hotel info");
        System.out.println("2. Update hotel info");
        System.out.println("3. Delete hotel info");
        System.out.println("4. Enter room info");
        System.out.println("5. Update room info");
        System.out.println("6. Delete room info");
        System.out.println("7. Enter staff info");
        System.out.println("8. Update staff info");
        System.out.println("9. Delete staff info");
        System.out.println("10. Enter customer info");
        System.out.println("11. Update customer info");
        System.out.println("12. Delete customer info");
        System.out.println("13. Check room number availability");
        System.out.println("14. Check room type availability");
        System.out.println("15. Assign customer to room");
        System.out.println("16. Release room");
        System.out.println("Select option: ");
    }
}
