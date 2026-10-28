package org.example.Repository;



import org.example.Domain.User;

import java.sql.*;
import java.util.ArrayList;

public class RepoUser {

    public static Connection conexiune;

    public static Connection con() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/ps_tema2";
            String utilizator = "root";
            String parola = "j5TZyhHV5@";
            conexiune = DriverManager.getConnection(url, utilizator, parola);
            return conexiune;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static int authenticateUser(String username, String password) {

        con();

        try {
            String query = "SELECT tip FROM utilizatori WHERE username = ? AND password = ?";
            try (PreparedStatement preparedStatement =conexiune.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    int tip = resultSet.getInt("tip");
                    return tip;
                } else {
                    return -1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conexiune != null) {
                    conexiune.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }


    public static User getUser(String id) { con();

        User user = new User();
        try {
            String query = "SELECT * FROM utilizatori WHERE  id=?";
            try (PreparedStatement preparedStatement = conexiune.prepareStatement(query)) {
                preparedStatement.setString(1,id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    user.setId(resultSet.getInt("id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setParola(resultSet.getString("password"));
                    user.setTip(resultSet.getInt("tip"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;

    }


    public static  boolean updateUser(User user) { con();

        String query = "UPDATE utilizatori SET username=?, password=? , tip=? where id=?";
        try (PreparedStatement preparedStatement = conexiune.prepareStatement(query)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getParola());
            preparedStatement.setString(3, user.getTip().toString());
            preparedStatement.setString(4, user.getId().toString());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (conexiune != null) {
                    conexiune.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public static boolean stergeUser(String userId) { con();

        try {
            String query = "DELETE FROM utilizatori WHERE id = ?";
            try (PreparedStatement preparedStatement = conexiune.prepareStatement(query)) {
                preparedStatement.setString(1, userId);
                preparedStatement.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean nou(User user) { con();

        String query = "INSERT INTO utilizatori (username, password, tip) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = conexiune.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getParola());
            preparedStatement.setString(3, user.getTip().toString());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Inserția a eșuat, nicio înregistrare nu a fost creată.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    System.out.println("Un nou utilizator a fost inserat cu ID-ul: " + generatedId);
                    return true;
                } else {
                    throw new SQLException();

                }
            }

        } catch ( SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conexiune != null) {
                    conexiune.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static ArrayList<User> getUsersAut() { con();
        ArrayList<User> users = new ArrayList<>();

        try {
            String query = "SELECT * FROM utilizatori WHERE tip IN ('1', '2')";
            try (PreparedStatement preparedStatement = conexiune.prepareStatement(query)) {

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setParola(resultSet.getString("password"));
                    user.setTip(resultSet.getInt("tip"));
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }



    public static ArrayList<User> getUsersAutTip(String val) { con();
        ArrayList<User> users = new ArrayList<>();

        try {
            String query = "SELECT * FROM utilizatori WHERE tip ="+"\'"+val+"\'";
            try (PreparedStatement preparedStatement = conexiune.prepareStatement(query)) {

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setParola(resultSet.getString("password"));
                    user.setTip(resultSet.getInt("tip"));
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }


}
