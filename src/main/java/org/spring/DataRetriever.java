package org.spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataRetriever {

    public ElectionWinner findWinner() {
        ElectionWinner winner = null;

        String sql = """
        SELECT c.name, COUNT(v.id) AS total_votes
        FROM vote v
        JOIN candidate c ON v.candidate_id = c.id
        WHERE v.vote_type = 'VALID'
        GROUP BY c.id, c.name
        ORDER BY total_votes DESC
        LIMIT 1
    """;

        DBConnection dbConnection = new DBConnection();
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                winner = new ElectionWinner(
                        rs.getString("name"),
                        rs.getLong("total_votes")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur Q6 : " + e.getMessage());
        }
        return winner;
    }

    public double computeTurnoutRate() {
        double rate = 0.0;

        String sql = """
        SELECT (COUNT(v.id) * 100.0 / (SELECT COUNT(id) FROM voter)) AS rate
        FROM vote v
    """;

        DBConnection dbConnection = new DBConnection();
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                rate = rs.getDouble("rate");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur Q5 : " + e.getMessage());
        }
        return rate;
    }

    public VoteSummary computeVoteSummary() {
        VoteSummary summary = null;

        String sql = """
        SELECT 
            COUNT(id) FILTER (WHERE vote_type = 'VALID') AS valid_count,
            COUNT(id) FILTER (WHERE vote_type = 'BLANK') AS blank_count,
            COUNT(id) FILTER (WHERE vote_type = 'NULL') AS null_count
        FROM vote
    """;

        DBConnection dbConnection = new DBConnection();
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                summary = new VoteSummary(
                        rs.getLong("valid_count"),
                        rs.getLong("blank_count"),
                        rs.getLong("null_count")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur Q4 : " + e.getMessage());
        }
        return summary;
    }

    public List<CandidateVoteCount> countValidVotesByCandidate() {
        List<CandidateVoteCount> results = new ArrayList<>();
        String sql = """
    SELECT c.name AS candidate_name, 
           COUNT(v.id) FILTER (WHERE v.vote_type = 'VALID') AS valid_votes
    FROM candidate c
    LEFT JOIN vote v ON v.candidate_id = c.id
    GROUP BY c.id, c.name
    ORDER BY valid_votes DESC
    
""";

        DBConnection dbConnection = new DBConnection();

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                // Extraction des données calculées par la jointure
                String name = rs.getString("candidate_name");
                long count = rs.getLong("valid_votes");

                // Création de l'objet DTO CandidateVoteCount
                results.add(new CandidateVoteCount(name, count));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erreur Q3 : " + e.getMessage());
        }

        return results;
    }

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
