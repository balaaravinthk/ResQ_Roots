import com.resqroots.database.DBConnection;
import java.sql.Connection;

public class DBTest {

    public static void main(String[] args) {

        Connection connection = DBConnection.getConnection();

        if (connection != null) {
            System.out.println("ResQ_Roots is ready!");
        }
    }
}