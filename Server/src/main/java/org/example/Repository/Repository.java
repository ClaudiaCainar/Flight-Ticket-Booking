package org.example.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Repository {
    // Instanța unică a clasei
    private static Repository instance;
    private Connection connection;
    private String url = "jdbc:mysql://localhost:3306/ps_tema2";
    private String username = "root";
    private String password = "root";

    // Constructor privat pentru a preveni instanțierea
    private Repository() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Metodă publică statică pentru a obține instanța unică
    public static Repository getInstance() {
        if (instance == null) {
            synchronized (Repository.class) {
                if (instance == null) {
                    instance = new Repository();
                }
            }
        }
        return instance;
    }

    // Metodă pentru a obține conexiunea
    public Connection getConnection() {
        return connection;
    }
}
