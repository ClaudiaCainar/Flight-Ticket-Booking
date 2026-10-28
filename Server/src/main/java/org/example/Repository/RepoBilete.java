package org.example.Repository;

import org.example.Domain.Bilet;

import java.sql.*;
import java.util.ArrayList;

public class RepoBilete {

    private static Connection getConnection() {
        return Repository.getInstance().getConnection();
    }

    public static ArrayList<Bilet> getBilete() {
        Connection conexiune = getConnection();
        ArrayList<Bilet> zboruri = new ArrayList<>();
        try {
            String query = "SELECT * FROM bilete";
            try (PreparedStatement preparedStatement = conexiune.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Bilet zbor = new Bilet();
                    zbor.setNrBilet(resultSet.getInt("nrbilet"));
                    zbor.setNumePasager(resultSet.getString("nume"));
                    zbor.setNrZbor(Integer.valueOf(resultSet.getString("nrzbor")));
                    zboruri.add(zbor);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return zboruri;
    }

    public static ArrayList<Bilet> getBilet(String nume) {
        Connection conexiune = getConnection();
        ArrayList<Bilet> bilete = new ArrayList<>();
        try {
            String query = "SELECT * FROM bilete WHERE nume = ?";
            try (PreparedStatement preparedStatement = conexiune.prepareStatement(query)) {
                preparedStatement.setString(1, nume);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Bilet bilet = new Bilet();
                    bilet.setNrBilet(resultSet.getInt("nrbilet"));
                    bilet.setNumePasager(resultSet.getString("nume"));
                    bilet.setNrZbor(Integer.valueOf(resultSet.getString("nrZbor")));
                    bilete.add(bilet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bilete;
    }

    public static boolean stergeBilet(String nrBilet) {
        Connection conexiune = getConnection();
        try {
            String query = "DELETE FROM bilete WHERE nrbilet = ?";
            try (PreparedStatement preparedStatement = conexiune.prepareStatement(query)) {
                preparedStatement.setString(1, nrBilet);
                preparedStatement.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Bilet getBiletCuNr(String nrBilet) {
        Connection conexiune = getConnection();
        Bilet rez = null;
        try {
            String query = "SELECT * FROM bilete WHERE nrbilet = ?";
            try (PreparedStatement preparedStatement = conexiune.prepareStatement(query)) {
                preparedStatement.setString(1, nrBilet);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    rez = new Bilet();
                    rez.setNrBilet(resultSet.getInt("nrbilet"));
                    rez.setNumePasager(resultSet.getString("nume"));
                    rez.setNrZbor(resultSet.getInt("nrzbor"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rez;
    }

    public static boolean insertBilet(Bilet z) {
        Connection conexiune = getConnection();
        String query = "INSERT INTO bilete (nrbilet, nume, nrzbor) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = conexiune.prepareStatement(query)) {
            preparedStatement.setString(1, z.getNrBilet().toString());
            preparedStatement.setString(2, z.getNume());
            preparedStatement.setString(3, z.getNrZbor());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateBilet(Bilet z) {
        Connection conexiune = getConnection();
        String query = "UPDATE bilete SET nrzbor=?, nume=? WHERE nrbilet=?";
        try (PreparedStatement preparedStatement = conexiune.prepareStatement(query)) {
            preparedStatement.setString(1, z.getNrZbor());
            preparedStatement.setString(2, z.getNume());
            preparedStatement.setString(3, z.getNrBilet().toString());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
