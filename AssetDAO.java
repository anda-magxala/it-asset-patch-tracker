package dao;

import model.Asset;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class AssetDAO {

    public void addAsset(Asset asset) {

        String sql = "INSERT INTO assets " +
                "(asset_name, asset_type, serial_number, assigned_to, purchase_date, status) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, asset.getAssetName());
            ps.setString(2, asset.getAssetType());
            ps.setString(3, asset.getSerialNumber());
            ps.setString(4, asset.getAssignedTo());
            ps.setString(5, asset.getPurchaseDate());
            ps.setString(6, asset.getStatus());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Asset added successfully!");
            }

            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewAssets() {

    String sql = "SELECT * FROM assets";

    try {
        Connection con = DBConnection.getConnection();

        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        System.out.println("\n===== ALL ASSETS =====");

        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("asset_id"));
            System.out.println("Name: " + rs.getString("asset_name"));
            System.out.println("Type: " + rs.getString("asset_type"));
            System.out.println("Serial Number: " + rs.getString("serial_number"));
            System.out.println("Assigned To: " + rs.getString("assigned_to"));
            System.out.println("Purchase Date: " + rs.getDate("purchase_date"));
            System.out.println("Status: " + rs.getString("status"));
            System.out.println("---------------------------");
        }

        rs.close();
        ps.close();
        con.close();

    } catch (Exception e) {
        e.printStackTrace();
    }
}

public void updateAsset(int id, String status, String assignedTo) {

    String sql = "UPDATE assets SET status = ?, assigned_to = ? WHERE asset_id = ?";

    try {
        Connection con = DBConnection.getConnection();

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, status);
        ps.setString(2, assignedTo);
        ps.setInt(3, id);

        int rows = ps.executeUpdate();

        if (rows > 0) {
            System.out.println("✅ Asset updated successfully!");
        } else {
            System.out.println("❌ Asset not found.");
        }

        ps.close();
        con.close();

    } catch (Exception e) {
        e.printStackTrace();
    }
}

public void deleteAsset(int id) {

    String sql = "DELETE FROM assets WHERE asset_id = ?";

    try {
        Connection con = DBConnection.getConnection();

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, id);

        int rows = ps.executeUpdate();

        if (rows > 0) {
            System.out.println("✅ Asset deleted successfully!");
        } else {
            System.out.println("❌ Asset not found.");
        }

        ps.close();
        con.close();

    } catch (Exception e) {
        e.printStackTrace();
    }
}

public void searchAsset(String serialNumber) {

    String sql = "SELECT * FROM assets WHERE serial_number = ?";

    try {
        Connection con = DBConnection.getConnection();

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, serialNumber);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            System.out.println("\n===== ASSET FOUND =====");
            System.out.println("ID: " + rs.getInt("asset_id"));
            System.out.println("Name: " + rs.getString("asset_name"));
            System.out.println("Type: " + rs.getString("asset_type"));
            System.out.println("Serial Number: " + rs.getString("serial_number"));
            System.out.println("Assigned To: " + rs.getString("assigned_to"));
            System.out.println("Purchase Date: " + rs.getDate("purchase_date"));
            System.out.println("Status: " + rs.getString("status"));
        } else {
            System.out.println("❌ No asset found with serial number: " + serialNumber);
        }

        rs.close();
        ps.close();
        con.close();

    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
