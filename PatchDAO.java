package dao;


import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

import model.Patch;
import java.sql.ResultSet;

public class PatchDAO {

    public void addPatch(Patch patch) {

        String sql =
                "INSERT INTO patches " +
                "(patch_name, version, release_date) " +
                "VALUES (?, ?, ?)";

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, patch.getPatchName());
            ps.setString(2, patch.getVersion());
            ps.setString(3, patch.getReleaseDate());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Patch added successfully!");
            } else {
                System.out.println("❌ Failed to add patch.");
            }

            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void installPatch(int assetId,
                         int patchId,
                         String status) {

    String sql =
            "INSERT INTO asset_patches " +
            "(asset_id, patch_id, installation_status) " +
            "VALUES (?, ?, ?)";

    try {
        Connection con =
                DBConnection.getConnection();

        PreparedStatement ps =
                con.prepareStatement(sql);

        ps.setInt(1, assetId);
        ps.setInt(2, patchId);
        ps.setString(3, status);

        int rows = ps.executeUpdate();

        if (rows > 0) {
            System.out.println("✅ Patch installed successfully!");
        } else {
            System.out.println("❌ Installation failed.");
        }

        ps.close();
        con.close();

    } catch (Exception e) {
        e.printStackTrace();
    }
}

public void viewInstalledPatches() {

    String sql =
            "SELECT a.asset_name, " +
            "p.patch_name, " +
            "p.version, " +
            "ap.installation_status " +
            "FROM asset_patches ap " +
            "JOIN assets a ON ap.asset_id = a.asset_id " +
            "JOIN patches p ON ap.patch_id = p.patch_id";

    try {
        Connection con = DBConnection.getConnection();

        PreparedStatement ps =
                con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        System.out.println("\n===== INSTALLED PATCHES =====");

        while (rs.next()) {

            System.out.println(
                    "Asset: " +
                    rs.getString("asset_name"));

            System.out.println(
                    "Patch: " +
                    rs.getString("patch_name"));

            System.out.println(
                    "Version: " +
                    rs.getString("version"));

            System.out.println(
                    "Status: " +
                    rs.getString("installation_status"));

            System.out.println(
                    "----------------------------");
        }

        rs.close();
        ps.close();
        con.close();

    } catch (Exception e) {
        e.printStackTrace();
    }
}

public void assetsNeedingUpdates() {

    String sql =
        "SELECT a.asset_name, " +
        "p.patch_name, " +
        "p.version AS installed_version, " +
        "lp.latest_version " +
        "FROM asset_patches ap " +
        "JOIN assets a ON ap.asset_id = a.asset_id " +
        "JOIN patches p ON ap.patch_id = p.patch_id " +
        "JOIN latest_patches lp ON p.patch_name = lp.patch_name " +
        "WHERE p.version <> lp.latest_version";

    try {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        System.out.println("\n===== ASSETS NEEDING UPDATES =====");

        boolean found = false;

        while (rs.next()) {
            found = true;

            System.out.println(
                "Asset: " + rs.getString("asset_name"));

            System.out.println(
                "Patch: " + rs.getString("patch_name"));

            System.out.println(
                "Installed Version: " +
                rs.getString("installed_version"));

            System.out.println(
                "Latest Version: " +
                rs.getString("latest_version"));

            System.out.println(
                "--------------------------------");
        }

        if (!found) {
            System.out.println("All assets are up to date.");
        }

        rs.close();
        ps.close();
        con.close();

    } catch (Exception e) {
        e.printStackTrace();
    }
}
}