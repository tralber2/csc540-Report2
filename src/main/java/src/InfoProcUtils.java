package src;

public class InfoProcUtils {
    public static int insertHotel(String name, String phone, String address, String city, int manager_id) {
        return 0;
    }

    public static void updateHotel(int hotel_id, String name, String phone, String address, String city, int manager_id) {

    }

    public static void deleteHotel(int hotel_id) {

    }

    public static int insertRoom(int room_number, int hotel_id, String category, int max_occupancy, float nightly_rate) {
        return 0;
    }

    public static void updateRoom(int room_id, int room_number, String category, int max_occupancy, float nightly_rate) {

    }

    public static void deleteRoom(int room_id) {

    }

    public static int insertStaff(String name, int age, String department, String phone, String address, String job_title) {
        return 0;
    }

    public static void updateStaff(int staff_id, String name, int age, String department, String phone, String address, String job_title) {

    }

    public static void deleteStaff(int staff_id) {

    }

    public static int insertCustomer(String name, String date_of_birth, String phone, String email) {
        return 0;
    }

    public static void updateCustomer(int customer_id, String name, String date_of_birth, String phone, String email) {

    }

    public static void deleteCustomer(int customer_id) {

    }

    public static boolean checkRoomNumberAvailability(int room_id) {
        return false;
    }

    public static boolean checkRoomTypeAvailability(String category) {
        return false;
    }

    public static void assignRoomToCustomer(int room_id, int customer_id) {

    }

    public static void releaseRoom(int room_id) {

    }
}
