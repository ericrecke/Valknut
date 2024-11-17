package service.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // URL para la conexión
	//String url ="jdbc:sqlserver://PC01\inst01;databaseName=DB01";

    private static final String URL = "jdbc:sqlserver://DATABAS3:62952;databaseName=Valknut;encrypt=false;trustServerCertificate=false;";
    private static final String USER = "valknut_user";
    private static final String PASSWORD = "Valknut123!";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
