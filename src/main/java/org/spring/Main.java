package org.spring;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DataRetriever retriever = new DataRetriever();
//        long totalVotes = retriever.countAllVotes();
//        System.out.println("Total Votes : " + totalVotes);
//        List<CandidateVoteCount> candidateScores = retriever.countValidVotesByCandidate();
//        for (CandidateVoteCount cvc : candidateScores) {
//            System.out.println(cvc);
//        }

//        VoteSummary summary = retriever.computeVoteSummary();
//        System.out.println(summary);

//
//        double turnoutRate = retriever.computeTurnoutRate();
//        System.out.println("Taux de participation : " + turnoutRate + "%");

//        List<VoteTypeCount> counts = retriever.countVotesByType();
//        System.out.println(counts);


        ElectionWinner winner = retriever.findWinner();
        if (winner != null) {
            System.out.println(winner);
        } else {
            System.out.println("No valid winner found.");
        }
        }
}
