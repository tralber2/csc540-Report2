package src;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillingAcctsUtils {
	
	public static int insertBillingInfo(String ssn, String address, String creditCardNumber, String paymentMethod) throws SQLException {
        PreparedStatement ps = ConnectionUtils.getConnection().prepareStatement("INSERT INTO billing_info(ssn, address, credit_card_number, credit_card_type) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, ssn);
        ps.setString(2, address);
        ps.setString(3, creditCardNumber);
        ps.setString(4, paymentMethod);

        ps.executeUpdate();

        // Get the auto-generated id and return it
        return ps.getGeneratedKeys().getInt(1);
	}
	
	public static void updateBillingInfo(int id, String ssn, String address, String creditCardNumber, String paymentMethod) throws SQLException {
        PreparedStatement ps = ConnectionUtils.getConnection().prepareStatement("UPDATE billing_info SET ssn = ?, address = ?, credit_card_number = ?, credit_card_type = ? WHERE id = ?");
        ps.setString(1, ssn);
        ps.setString(2, address);
        ps.setString(3, creditCardNumber);
        ps.setString(4, paymentMethod);

        ps.setInt(5, id);
        ps.executeUpdate();
	}
	
	public static void deleteBillingInfo(int id) throws SQLException {
        PreparedStatement ps = ConnectionUtils.getConnection().prepareStatement("DELETE FROM billing_info WHERE id=?");

        ps.setInt(1, id);

        ps.executeUpdate();
	}
	
	public static void insertHasBillingInfo(int customerId, int billingId) throws SQLException {
        PreparedStatement ps = ConnectionUtils.getConnection().prepareStatement("INSERT INTO has_billing_info(customer_id, billing_id) VALUES (?,?)");
        ps.setInt(1, customerId);
        ps.setInt(2, billingId);

        ps.executeUpdate();
	}
	
	public static void updateHasBillingInfo(int customerId, int billingId) throws SQLException {
        PreparedStatement ps = ConnectionUtils.getConnection().prepareStatement("UPDATE has_billing_info SET billing_id = ? WHERE customer_id = ?");
        ps.setInt(1, billingId);
        ps.setInt(2, customerId);

        ps.executeUpdate();
	}
	
	public static void deleteHasBillingInfo(int customerId) throws SQLException {
        PreparedStatement ps = ConnectionUtils.getConnection().prepareStatement("DELETE FROM has_billing_info WHERE customer_id=?");

        ps.setInt(1, customerId);

        ps.executeUpdate();
	}
	
	public static float generateTotalBill(int customerId, Date endDate) throws SQLException {
        PreparedStatement ps = ConnectionUtils.getConnection().prepareStatement("" +
                "SELECT IF(discount=1, .95, 1.0)*(datediff(end_date, start_date)* nightly_rate + coalesce((SELECT SUM(fee) " +
                "FROM service " +
                "JOIN purchase ON service_id = service.id JOIN occupies ON occupies.customer_id = purchase.customer_id " +
                "WHERE purchase.customer_id = ? AND purchase_date >= occupies.start_date AND occupies.end_date >= purchase_date), 0.0)) " +
                "AS Cost FROM occupies JOIN room ON room_number = room.id AND occupies.hotel_id = room.hotel_id " +
                "WHERE occupies.customer_id = ? AND end_date = ?");
        ps.setInt(1, customerId);
        ps.setInt(2, customerId);
        ps.setDate(3, endDate);

        ResultSet rs = ps.executeQuery();
        return rs.getFloat(1);
    }

	public static List<String> generateItemizedBill(int customerId, Date endDate) throws SQLException {
        PreparedStatement ps = ConnectionUtils.getConnection().prepareStatement("SELECT “Room” AS Type_Column, " +
                "IF(discount=1, .95, 1.0)*(datediff(end_date, start_date)* nightly_rate) AS Cost FROM occupies " +
                "JOIN room ON room_number = room.id AND occupies.hotel_id = room.hotel_id " +
                "WHERE occupies.customer_id = ? AND end_date = ? " +
                "UNION SELECT name AS Type_Column, fee AS Cost FROM service JOIN purchase ON service_id = service.id " +
                "JOIN occupies ON occupies.customer_id = purchase.customer_id " +
                "WHERE purchase.customer_id = ? AND purchase_date >= occupies.start_date AND occupies.end_date >= purchase_date");
        ps.setInt(1, customerId);
        ps.setDate(2, endDate);
        ps.setInt(3, customerId);

        ResultSet rs = ps.executeQuery();

        List<String> itemList = new ArrayList<String>();
        int i = 0;
        while(rs.next()) {
            String row = i + ". " + rs.getString(1) + " | " + rs.getFloat(2);
            itemList.add(row);
            i++;
        }
        return itemList;
	}
}
