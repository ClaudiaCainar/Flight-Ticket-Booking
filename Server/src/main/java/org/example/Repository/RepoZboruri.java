package org.example.Repository;



import org.example.Domain.Zbor;

import java.sql.*;
import java.util.ArrayList;

public class RepoZboruri {

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

    public static ArrayList<Zbor> getZboruriStartDest(String plecare, String destinatie) {
        con();
        ArrayList<Zbor> zboruri = new ArrayList<>();

        try {
            String query = "SELECT * FROM zboruri WHERE plecare = ? AND destinatie = ?";
            try (PreparedStatement preparedStatement = conexiune.prepareStatement(query)) {
                preparedStatement.setString(1, plecare);
                preparedStatement.setString(2, destinatie);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Zbor zbor = new Zbor();
                    zbor.setNrZbor(resultSet.getInt("id"));
                    zbor.setStart(resultSet.getString("plecare"));
                    zbor.setDestinatie(resultSet.getString("destinatie"));
                    zbor.setNrLocuri(resultSet.getInt("nrLocuri"));
                    zboruri.add(zbor);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return zboruri;
    }

    public static ArrayList<Zbor> getZboruri() {
        con();
        ArrayList<Zbor> zboruri = new ArrayList<>();
        try {
            String query = "SELECT * FROM zboruri";
            try (PreparedStatement preparedStatement =conexiune.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Zbor zbor = new Zbor();
                    zbor.setNrZbor(resultSet.getInt("id"));
                    zbor.setStart(resultSet.getString("plecare"));
                    zbor.setDestinatie(resultSet.getString("destinatie"));
                    zbor.setNrLocuri(resultSet.getInt("nrLocuri"));
                    zboruri.add(zbor);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return zboruri;
    }


    public static Zbor getZborCuNr(String id)
    { con();
        Zbor rez=null;
        try {
            String query = "SELECT * FROM zboruri WHERE id = ?";
            try (PreparedStatement preparedStatement =conexiune.prepareStatement(query)) {
                preparedStatement.setString(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    rez = new Zbor();
                    rez.setNrZbor(resultSet.getInt("id"));
                    rez.setStart(resultSet.getString("plecare"));
                    rez.setDestinatie(resultSet.getString("destinatie"));
                    rez.setNrLocuri(resultSet.getInt("nrLocuri"));

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rez;
    }


    public static boolean  stergeZbor(String nrZbor)
    { con();
        try {
            String query = "DELETE FROM zboruri WHERE id = ?";
            try (PreparedStatement preparedStatement =conexiune.prepareStatement(query)) {
                preparedStatement.setString(1, nrZbor);
                preparedStatement.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static boolean insertZbor(Zbor z) {
        con();
        try {
            String query = "INSERT INTO zboruri (id, plecare, destinatie,nrLocuri) VALUES (?, ?, ?,?)";
            try (PreparedStatement preparedStatement =conexiune.prepareStatement(query)) {
                preparedStatement.setString(1, z.getNrZbor().toString());
                preparedStatement.setString(2, z.getStart());
                preparedStatement.setString(3, z.getDestinatie());
                preparedStatement.setString(4,"200");
                preparedStatement.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }





    public static boolean updateZbor(Zbor z) { con();
        try {

            String query = "UPDATE zboruri SET plecare=?, destinatie=?,nrLocuri=? WHERE id=?";
            try (PreparedStatement preparedStatement =conexiune.prepareStatement(query)) {
                preparedStatement.setString(1, z.getStart());
                preparedStatement.setString(2, z.getDestinatie());
                preparedStatement.setString(3, z.getNrLocuri().toString());
                preparedStatement.setString(4, z.getNrZbor().toString());

                preparedStatement.executeUpdate();

            }
        } catch ( SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (conexiune != null) {
                    conexiune.close();
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }




}
