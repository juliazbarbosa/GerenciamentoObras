import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    private static ConexaoBD instance;
    private Connection connection;
    private String url = "jdbc:mysql://localhost:3306/sua_base_de_dados";
    private String username = "seu_usuario";
    private String password = "sua_senha";

    private ConexaoBD() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            throw new SQLException("Erro ao conectar com o banco de dados", ex);
        }
    }

    public static ConexaoBD getInstance() throws SQLException {
        if (instance == null) {
            instance = new ConexaoBD();
        } else if (instance.getConnection().isClosed()) {
            instance = new ConexaoBD();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
