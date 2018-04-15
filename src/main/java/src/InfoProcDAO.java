package src;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InfoProcDAO {
    public static int insertHotel(String name, String phone, String address, String city, int manager_id) throws SQLException {
        PreparedStatement ps = ConnectionUtils.getConnection().prepareStatement("INSERT INTO hotel(name, phone, address, city, manager_id) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, name);
        ps.setString(2, phone);
        ps.setString(3, address);
        ps.setString(4, city);
        ps.setInt(5, manager_id);

        ps.executeUpdate();

        // Get the auto-generated id and return it
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
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

    public static int insertRoom(int room_number, int hotel_id, String category, int max_occupancy, int nightly_rate) throws SQLException {
        PreparedStatement ps = ConnectionUtils.getConnection().prepareStatement("INSERT INTO room(num,hotel_id, category, max_occupancy, nightly_rate, availability) VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, room_number);
        ps.setInt(2, hotel_id);
        ps.setString(3, category);
        ps.setInt(4, max_occupancy);
        ps.setInt(5, nightly_rate);
        ps.setBoolean(6, true);

        ps.executeUpdate();

        // Get the auto-generated id and return it
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }

    public static void updateRoom(int room_id, String category, int max_occupancy, int nightly_rate) throws SQLException {
        PreparedStatement ps = ConnectionUtils.getConnection().prepareStatement("UPDATE room SET category=?, max_occupancy=?, nightly_rate=? WHERE id=?");

        ps.setString(1, category);
        ps.setInt(2, max_occupancy);
        ps.setInt(3, nightly_rate);
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
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
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

    public static int insertCustomer(String name, String date_of_birth, String phone, String email) throws SQLException {
        PreparedStatement ps = ConnectionUtils.getConnection().prepareStatement("INSERT INTO customer(name, date_of_birth, phone, email) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, name);
        ps.setString(2, date_of_birth);
        ps.setString(3, phone);
        ps.setString(4, email);

        ps.executeUpdate();

        // Get the auto-generated id and return it
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }

    public static void updateCustomer(int customer_id, String name, String date_of_birth, String phone, String email) throws SQLException {
        PreparedStatement ps = ConnectionUtils.getConnection().prepareStatement("UPDATE customer SET name=?, date_of_birth=?, phone=?, email=?, WHERE id=?");

        ps.setString(1, name);
        ps.setString(2, date_of_birth);
        ps.setString(3, phone);
        ps.setString(4, email);

        ps.setInt(5, customer_id);

        ps.executeUpdate();
    }

    public static void deleteCustomer(int customer_id) throws SQLException {
        PreparedStatement ps = ConnectionUtils.getConnection().prepareStatement("DELETE FROM customer WHERE id=?");

        ps.setInt(1, customer_id);

        ps.executeUpdate();
    }

    public static boolean checkRoomNumberAvailability(int hotel_id, int room_num) throws SQLException {
        PreparedStatement ps = ConnectionUtils.getConnection().prepareStatement("SELECT availability FROM room WHERE \n" +
                "hotel_id=? AND \n" +
                "num=?");
        ps.setInt(1, hotel_id);
        ps.setInt(2, room_num);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getBoolean(1);
        } else {
            return false;
        }
    }

    public static List<String> checkRoomTypeAvailability(String category) throws SQLException {
        PreparedStatement ps = ConnectionUtils.getConnection().prepareStatement("SELECT hotel_id, num FROM room WHERE \n" +
                "category=? AND availability=1");
        ps.setString(1, category);

        ResultSet rs = ps.executeQuery();

        List<String> roomList = new ArrayList<String>();
        int i = 1;
        while (rs.next()) {
            String room = i + ". " + rs.getInt(1) + " | " + rs.getInt(2);
            roomList.add(room);
            i++;
        }

        return roomList;
    }

    public static void assignRoomToCustomer(int hotel_id, int room_id, int customer_id, int number_of_guests, Date startdate, boolean discount) {
        Connection con = ConnectionUtils.getConnection();
        try {
            con.setAutoCommit(false);
            PreparedStatement occupyPS = con.prepareStatement("INSERT INTO occupies(hotel_id, room_id, customer_id,number_of_guest, start_date, discount) VALUES(?,?,?,?,?,?)");
            occupyPS.setInt(1, hotel_id);
            occupyPS.setInt(2, room_id);
            occupyPS.setInt(3, customer_id);
            occupyPS.setInt(4, number_of_guests);
            occupyPS.setDate(5, startdate);
            occupyPS.setBoolean(6, discount);
            occupyPS.executeUpdate();

            PreparedStatement roomPS = con.prepareStatement("UPDATE room SET availability=0 WHERE id=?");
            roomPS.setInt(1, room_id);
            roomPS.executeUpdate();

            con.commit();
        } catch (SQLException e) {
            System.out.println("Assign Room to Customer Transaction Failed");
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException e) {
                // Turning autocommit back on failed...
                e.printStackTrace();
            }
        }
    }

    public static void releaseRoom(int room_id, int customer_id) {
        Connection con = ConnectionUtils.getConnection();
        try {
            con.setAutoCommit(false);
            PreparedStatement occupyPS = con.prepareStatement("UPDATE occupies SET end_date=GETDATE() where customer_id=? AND room_id=? AND end_date=NULL;");
            occupyPS.setInt(1, customer_id);
            occupyPS.setInt(2, room_id);
            occupyPS.executeUpdate();

            PreparedStatement roomPS = con.prepareStatement("UPDATE room SET availability=1 WHERE id=?");
            roomPS.setInt(1, room_id);
            roomPS.executeUpdate();

            con.commit();
        } catch (SQLException e) {
            System.out.println("Release Room Failed");
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException e) {
                // Turning autocommit back on failed...
                e.printStackTrace();
            }
        }
    }
}
