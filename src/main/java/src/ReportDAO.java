package src;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportDAO {
	
	public static String reportOccupancyByHotel() throws SQLException {
		PreparedStatement ps = ConnectionUtils.getConnection().prepareStatement("" +
				"SELECT total_table.hotel_id as hotel_id, " +
				"IFNULL(hotel_table.occupied, 0) as occupied, total_table.total as total, " +
				"IFNULL(concat(round(hotel_table.occupied/total_table.total * 100, 2), '%'),'0%') as percentage from " +
				"(SELECT COUNT(*) as occupied, hotel_id, availability from room where availability=0 GROUP BY hotel_id, availability ) " +
				"as hotel_table " +
				"RIGHT JOIN (SELECT COUNT(*) as total, hotel_id from room GROUP BY hotel_id) " +
				"as total_table ON total_table.hotel_id=hotel_table.hotel_id");

		ResultSet rs = ps.executeQuery();

		StringBuilder returnString = new StringBuilder(String.format("%-10s%-10s%-10s%-15s\n", "hotel id", "occupied", "total", "percentage"));

		while(rs.next()) {
			returnString.append(String.format("%-10d%-10d%-10d%-15.2f\n", rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getFloat(4))).append("\n");
		}
		return returnString.toString();
	}
	
	public static String reportOccupancyByRoomType() throws SQLException {
		PreparedStatement ps = ConnectionUtils.getConnection().prepareStatement("" +
				"SELECT total_table.category as room_type, IFNULL(cat_table.occupied, 0) as occupied, " +
				"total_table.total as total, IFNULL(concat(round(cat_table.occupied/total_table.total * 100, 2), '%'),'0%') " +
				"as percentage from (SELECT COUNT(*) as occupied, category, availability from room where availability=0 " +
				"GROUP BY category, availability ) as cat_table RIGHT JOIN (SELECT COUNT(*) as total, category from room " +
				"GROUP BY category) as total_table ON total_table.category=cat_table.category");

		ResultSet rs = ps.executeQuery();

		StringBuilder returnString = new StringBuilder(String.format("%-10s%-10s%-10s%-15s\n", "room type", "occupied", "total", "percentage"));

		while(rs.next()) {
			returnString.append(String.format("%-10d%-10d%-10d%-15.2f\n", rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getFloat(4))).append("\n");
		}
		return returnString.toString();
	}
	
	public static String reportOccupancyByDateRange(Date startDate, Date endDate) throws SQLException {
        PreparedStatement ps = ConnectionUtils.getConnection().prepareStatement("" +
                "SELECT IFNULL(date_table.occupied, 0) as occupied, total_table.total as total, " +
                "IFNULL(concat(round(date_table.occupied/total_table.total * 100, 2), '%'),'0%') as percentage from " +
                "(SELECT COUNT(*) as occupied, 0 as temp from occupies where (? >= start_date AND ? <= end_date) " +
                "OR (? >= start_date AND ? <= end_date) OR (? <= start_date AND ? >= end_date)) as date_table " +
                "RIGHT JOIN (SELECT 0 as temp, COUNT(*) as total from room) as total_table on total_table.temp=date_table.temp");

        ps.setDate(1, startDate);
        ps.setDate(2, startDate);
        ps.setDate(3, endDate);
        ps.setDate(4, endDate);
        ps.setDate(5, startDate);
        ps.setDate(6, endDate);

        ResultSet rs = ps.executeQuery();

        StringBuilder returnString = new StringBuilder(String.format("%-10s%-10s%-15s\n", "occupied", "total", "percentage"));

        while(rs.next()) {
            returnString.append(String.format("%-10d%-10d%-15.2f\n", rs.getInt(1), rs.getInt(2), rs.getFloat(3))).append("\n");
        }
        return returnString.toString();
	}
	
	public static String reportOccupancyByCity() throws SQLException {
        PreparedStatement ps = ConnectionUtils.getConnection().prepareStatement("" +
                "SELECT Total_table.city, IFNULL(city_table.occupied,0) as occupied, total_table.total, " +
                "IFNULL(concat(round(city_table.occupied/total_table.total * 100, 2), '%'),'0%') as percentage from " +
                "(SELECT hotel.city as city, COUNT(*) as total from room INNER JOIN hotel ON hotel.id=room.hotel_id GROUP BY hotel.city) as total_table \n" +
                "LEFT JOIN (SELECT hotel.city as city, COUNT(*) as occupied, availability from room " +
                "INNER JOIN hotel ON hotel.id=room.hotel_id GROUP BY hotel.city, room.availability) " +
                "as city_table on city_table.city=total_table.city AND availability=0");

        ResultSet rs = ps.executeQuery();

        StringBuilder returnString = new StringBuilder(String.format("%-10s%-10s%-10s%-15s\n", "city", "occupied", "total", "percentage"));

        while(rs.next()) {
            returnString.append(String.format("%-10s%-10d%-10d%-15.2f\n", rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getFloat(4))).append("\n");
        }
        return returnString.toString();
	}
	
	public static String reportStaffGroupedByRole() throws SQLException {
        return null;
	}
	
	public static String reportAllStaffServingCustomer(int customerId) throws SQLException {
        PreparedStatement ps = ConnectionUtils.getConnection().prepareStatement("" +
                "SELECT * from (SELECT occupies.stay_id as stay_id, occupies.customer_id as customer_id, purchase.staff_id as staff_id from occupies " +
                "INNER JOIN purchase on occupies.start_date <= purchase.purchase_date AND occupies.end_date >= purchase.purchase_date AND purchase.customer_id=occupies.customer_id) " +
                "as staff_table INNER join staff on staff.id=staff_table.staff_id");

        ResultSet rs = ps.executeQuery();

        StringBuilder returnString = new StringBuilder(String.format("%-8s%-12s%-10s%-5s%-7s%-5s%-13s%-7s%-10s%-12s\n", "stay id", "customer id", "staff id", "id", "name", "age",  "department", "phone", "address", "job title"));

        while(rs.next()) {
            returnString.append(String.format("%-8s%-12s%-10s%-5s%-7s%-5s%-13s%-7s%-10s%-12s\n",
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getInt(4),
                    rs.getString(5),
                    rs.getInt(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getString(9),
                    rs.getString(10)))
                    .append("\n");
        }
        return returnString.toString();
	}
	
	public static String reportRevenueGeneratedByHotelForGivenDateRange(int hotelID, Date startDate, Date endDate) throws SQLException {
        PreparedStatement ps = ConnectionUtils.getConnection().prepareStatement("" +
                "SELECT " +
                "coalesce((SELECT IF(discount=1, .95, 1.0)*SUM(fee) " +
                "FROM service JOIN purchase ON service_id = service.id JOIN occupies ON occupies.customer_id = purchase.customer_id " +
                "WHERE purchase_date >= ? AND purchase_date  <= ?), 0.0) + " +
                "coalesce((SELECT SUM(IF(discount=1, .95, 1.0)*(datediff(end_date, start_date)* nightly_rate )) " +
                "FROM occupies JOIN room ON room_number = room.id AND occupies.hotel_id = room.hotel_id AND occupies.hotel_id =?" +
                "WHERE ? <= end_date AND ? >= end_date), 0.0) AS Revenue");

        ps.setDate(1, startDate);
        ps.setDate(2, endDate);
        ps.setInt(3, hotelID);
        ps.setDate(4, startDate);
        ps.setDate(5, endDate);

        ResultSet rs = ps.executeQuery();

        StringBuilder returnString = new StringBuilder(String.format("%-10s\n", "revenue"));

        if(rs.next()) {
            returnString.append(String.format("%-10s\n", rs.getFloat(1))).append("\n");
        }
        return returnString.toString();
	}
}
