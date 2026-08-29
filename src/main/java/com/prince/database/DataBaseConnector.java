package com.prince.database;

import com.prince.model.PickUpPoint;

import java.sql.*;
import java.util.ArrayList;

public class DataBaseConnector {

    private final String URL;
    private final Connection CONN;

    public DataBaseConnector(String url) {
        this.URL = url;
        try {
            this.CONN = getConnection(URL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        initializeSchema();
    }

    public void killConnection() {
        try {
            CONN.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Connection getConnection(String url) throws SQLException {
        Connection conn = DriverManager.getConnection(url);
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

        try (Statement stmt = CONN.createStatement()) {
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

        try(PreparedStatement stmt = CONN.prepareStatement(addStore)){

            CONN.setAutoCommit(false);

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

        try (PreparedStatement stmt = CONN.prepareStatement(getName);){

            CONN.setAutoCommit(false);
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

    public void updatePickUpPoint(String storeName, int crates) {

        String name = storeName;
        int creates = crates;

        String updateUsage = "UPDATE pickUpPoints SET usage = (?) WHERE name = (?)";

        try (PreparedStatement stmt = CONN.prepareStatement(updateUsage);) {

            CONN.setAutoCommit(false);

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

        try {

            ResultSet rs = CONN.createStatement().executeQuery(getStores);

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
