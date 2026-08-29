package com.prince.database;

import com.prince.model.PickUpPoint;

import java.sql.*;
import java.util.ArrayList;

public class DataBaseConnector {

    private final String URL;

    public DataBaseConnector() {
        this.URL = "jdbc:sqlite:pickUpPoints.db";
        initializeSchema();
    }

    private Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(URL);
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("PRAGMA foreign_keys = ON;");
        }
        return conn;
    }

    private void initializeSchema() {
        String createTasksTable = "CREATE TABLE IF NOT EXISTS pickUpPoints ("
                + "name TEXT PRIMARY KEY NOT NULL, "
                + "address TEXT NOT NULL,"
                + "usage INTEGER NOT NULL"
                + ");";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(createTasksTable);
        } catch (SQLException e) {
            System.err.println("Database initialization error: " + e.getMessage());
        }
    }

    public void addPickUpPoint(PickUpPoint store){
        String name = store.getNAME();
        String address = store.getADDRESS();
        int usage = store.getCurrentUsage();

        String addStore = "INSERT INTO pickUpPoints (name, address, usage) VALUES (?, ?, ?)";

        try(Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(addStore)){

            conn.setAutoCommit(false);

            stmt.setString(1, name);
            stmt.setString(2, address);
            stmt.setInt(3, usage);

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Add task failed: " + e.getMessage());
        }
    }

    public PickUpPoint getPickUpPoint(String name){

        String getName = "SELECT * FROM pickUpPoints WHERE name = (?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(getName);){

            conn.setAutoCommit(false);
            stmt.setString(1,name);
            ResultSet rs = stmt.executeQuery();

            String storeName = rs.getString("name");
            String storeAddress = rs.getString("address");
            int usage = rs.getInt("usage");

            return new PickUpPoint(storeName, storeAddress, usage);

        } catch (SQLException e) {
            System.err.println("getStore task failed: " + e.getMessage());
        }
        return null;
    }

    public void updatePickUpPoint(PickUpPoint store) {

        String name = store.getNAME();
        int creates = store.getCurrentUsage();

        String updateUsage = "UPDATE pickUpPoints SET usage = (?) WHERE name = (?)";

        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(updateUsage);) {

            conn.setAutoCommit(false);

            stmt.setInt(1, creates);
            stmt.setString(2, name);

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Add task failed: " + e.getMessage());
        }
    }

    public ArrayList<PickUpPoint> getPickUpPoints(){

        String getStores = "SELECT * FROM pickUpPoints";
        ArrayList<PickUpPoint> stores = new ArrayList<>();

        try(Connection conn = getConnection()) {

            ResultSet rs = conn.createStatement().executeQuery(getStores);

            while (rs.next()) {
                String name = rs.getString("name");
                String address = rs.getString("address");
                int usage = rs.getInt("usage");

                stores.add(new PickUpPoint(name, address, usage));
            }


        } catch (SQLException e) {
            System.err.print("Get task failed: " + e.getMessage());
        }
        return stores;
    }
}
