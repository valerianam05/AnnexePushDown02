package org.spring;

public class VoteTypeCount {
    private VoteTypes voteType;
    private long count;

    // Constructeur (obligatoire sans Lombok)
    public VoteTypeCount(VoteTypes voteType, long count) {
        this.voteType = voteType;
        this.count = count;
    }

    // Getters
    public VoteTypes getVoteType() { return voteType; }
    public long getCount() { return count; }

    @Override
    public String toString() {
        return "Type: " + voteType + " | Count: " + count;
    }
}
