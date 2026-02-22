package org.spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataRetriever {

    public long countAllVotes() {
        long total = 0;

        String sql = """
        SELECT COUNT(id) AS total 
        FROM vote
    """;

        DBConnection dbConnection = new DBConnection();
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                total = rs.getLong("total");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur Q1 : " + e.getMessage());
        }
        return total;
    }
}
