package org.spring;

public class ElectionWinner {
    private String name;
    private long voteCount;

    public ElectionWinner(String name, long voteCount) {
        this.name = name;
        this.voteCount = voteCount;
    }

    @Override
    public String toString() {
        return "The winner is " + name + " with " + voteCount + " valid votes!";
    }
}
