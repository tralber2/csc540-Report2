package src;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ServiceRecordsDAO {
    public static int purchaseService(int service_id, int staff_id, int customer_id, Date purchase_date) throws SQLException {
        PreparedStatement ps = ConnectionUtils.getConnection().prepareStatement("INSERT INTO purchase(service_id, staff_id, customer_id, purchase_date) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, service_id);
        ps.setInt(2, staff_id);
        ps.setInt(3, customer_id);
        ps.setDate(4, purchase_date);

        ps.executeUpdate();

        // Get the auto-generated id and return it
        return ps.getGeneratedKeys().getInt(1);
    }

    public static void updateServiceRecord(int purchase_id, int service_id, int staff_id, int customer_id, Date purchase_date) throws SQLException {
        PreparedStatement occupyPS = ConnectionUtils.getConnection().prepareStatement("UPDATE purchase SET service_id = ?, staff_id = ?, customer_id = ?, purchase_date = ? WHERE id = ?");
        occupyPS.setInt(1, service_id);
        occupyPS.setInt(2, staff_id);
        occupyPS.setInt(3, customer_id);
        occupyPS.setDate(4, purchase_date);

        occupyPS.setInt(5, purchase_id);
        occupyPS.executeUpdate();
    }
}
