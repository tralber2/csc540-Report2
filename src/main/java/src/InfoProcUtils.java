package src;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class InfoProcUtils {
    public static int insertHotel(String name, String phone, String address, String city, int manager_id) throws SQLException {
        PreparedStatement ps = ConnectionUtils.getConnection().prepareStatement("INSERT INTO hotel(name, phone, address, city, manager_id) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, name);
        ps.setString(2, phone);
        ps.setString(3, address);
        ps.setString(4, city);
        ps.setInt(5, manager_id);

        ps.executeUpdate();

        // Get the auto-generated id and return it
        return ps.getGeneratedKeys().getInt(1);
    }

    public static void updateHotel(int hotel_id, String name, String phone, String address, String city, int manager_id) throws SQLException {
        PreparedStatement ps = ConnectionUtils.getConnection().prepareStatement("UPDATE hotel SET name=?,phone=?,address=?,city=?,manager_id=? WHERE id=?");

        ps.setString(1, name);
        ps.setString(2, phone);
        ps.setString(3, address);
        ps.setString(4, city);
        ps.setInt(5, manager_id);

        ps.setInt(6, hotel_id);

        ps.executeUpdate();
    }

    public static void deleteHotel(int hotel_id) throws SQLException {
        PreparedStatement ps = ConnectionUtils.getConnection().prepareStatement("DELETE FROM hotel WHERE id=?");

        ps.setInt(1, hotel_id);

        ps.executeUpdate();
    }

    public static int insertRoom(int room_number, int hotel_id, String category, int max_occupancy, float nightly_rate) throws SQLException {
        PreparedStatement ps = ConnectionUtils.getConnection().prepareStatement("INSERT INTO room(num,hotel_id, category, max_occupancy, nightly_rate, availability) VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, room_number);
        ps.setInt(2, hotel_id);
        ps.setString(3, category);
        ps.setInt(4, max_occupancy);
        ps.setFloat(5, nightly_rate);
        ps.setBoolean(6, true);

        ps.executeUpdate();

        // Get the auto-generated id and return it
        return ps.getGeneratedKeys().getInt(1);
    }

    public static void updateRoom(int room_id, String category, int max_occupancy, float nightly_rate) throws SQLException {
        PreparedStatement ps = ConnectionUtils.getConnection().prepareStatement("UPDATE room SET category=?, max_occupancy=?, nightly_rate=? WHERE id=?");

        ps.setString(1, category);
        ps.setInt(2, max_occupancy);
        ps.setFloat(3, nightly_rate);
        ps.setInt(4, room_id);

        ps.executeUpdate();
    }

    public static void deleteRoom(int room_id) throws SQLException {
        PreparedStatement ps = ConnectionUtils.getConnection().prepareStatement("DELETE FROM room WHERE id=?");

        ps.setInt(1, room_id);

        ps.executeUpdate();
    }

    public static int insertStaff(String name, int age, String department, String phone, String address, String job_title) throws SQLException {
        PreparedStatement ps = ConnectionUtils.getConnection().prepareStatement("INSERT INTO staff(name, age, department, phone, address, job_title) VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, name);
        ps.setInt(2, age);
        ps.setString(3, department);
        ps.setString(4, phone);
        ps.setString(5, address);
        ps.setString(6, job_title);

        ps.executeUpdate();

        // Get the auto-generated id and return it
        return ps.getGeneratedKeys().getInt(1);
    }

    public static void updateStaff(int staff_id, String name, int age, String department, String phone, String address, String job_title) throws SQLException {
        PreparedStatement ps = ConnectionUtils.getConnection().prepareStatement("UPDATE staff SET name=?, age=?, department=?, phone= ?, address=?, job_title=? WHERE id=?");

        ps.setString(1, name);
        ps.setInt(2, age);
        ps.setString(3, department);
        ps.setString(4, phone);
        ps.setString(5, address);
        ps.setString(6, job_title);

        ps.setInt(7, staff_id);

        ps.executeUpdate();
    }

    public static void deleteStaff(int staff_id) throws SQLException {
        PreparedStatement ps = ConnectionUtils.getConnection().prepareStatement("DELETE FROM staff WHERE id=?");

        ps.setInt(1, staff_id);

        ps.executeUpdate();
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
