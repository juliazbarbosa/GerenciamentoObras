import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    private static ConexaoBD instance;
    private Connection connection;
    private String url = "jdbc:mysql://localhost:3306/sua_base_de_dados";
    private String username = "seu_usuario";
    private String password = "sua_senha";

    public static ConexaoBD getInstance() throws SQLException {
        if (instance == null) {
            instance = new ConexaoBD();
        } else if (instance.getConnection().isClosed()) {
            instance = new ConexaoBD();
        }
        return instance;
    }

     public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
