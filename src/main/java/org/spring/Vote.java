package org.spring;

public class Vote {
    private int id;
    private Candidate candidate;
    private Voter voter;
    private VoteTypes voteTypes;

    public Vote(int id, Candidate candidate, Vote vote, VoteTypes voteTypes) {
        this.id = id;
        this.candidate = candidate;
        this.voter = voter;
        this.voteTypes = voteTypes;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public Candidate getCandidate() { return candidate; }
    public void setCandidate(Candidate candidate) { this.candidate = candidate; }

    public Voter getVoter() { return voter; }
    public void setVoter(Voter voter) { this.voter = voter; }

    public VoteTypes getVoteTypes() { return voteTypes; }
    public void setVoteTypes(VoteTypes voteTypes) { this.voteTypes = voteTypes; }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", candidate=" + candidate +
                ", voter=" + voter +
                ", voteTypes=" + voteTypes +
                '}';
    }
}