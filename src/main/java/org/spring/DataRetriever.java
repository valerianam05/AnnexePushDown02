package org.spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataRetriever {



    public List<VoteTypeCount> countVotesByType() {
        List<VoteTypeCount> results = new ArrayList<>();
        String sql = """
        SELECT vote_type, COUNT(id) AS total_count
        FROM vote
        GROUP BY vote_type
    """;
        DBConnection dbConnection = new DBConnection();

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String typeFromDb = rs.getString("vote_type");
                VoteTypes type = VoteTypes.valueOf(typeFromDb);

                long count = rs.getLong("total_count");
                results.add(new VoteTypeCount(type, count));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erreur Q2 : " + e.getMessage());
        }

        return results;
    }

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
