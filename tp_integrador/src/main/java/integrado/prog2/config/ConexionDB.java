package integrado.prog2.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionDB {

    private static final String URL = "jdbc:sqlite:foodstore.db";

    public static Connection getConexion() throws SQLException {

        Connection conn = DriverManager.getConnection(URL);

        try (Statement stmt = conn.createStatement()) {
            stmt.execute("PRAGMA foreign_keys = ON");
        }

        return conn;
    }
}