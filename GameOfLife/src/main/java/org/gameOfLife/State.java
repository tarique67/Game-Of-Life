package org.gameOfLife;

public enum State {
    DEAD("-"), ALIVE("*");

    private final String sateString;

    State(String s) {
        this.sateString = s;
    }

    public String getStateString() {
        return sateString;
    }

}
