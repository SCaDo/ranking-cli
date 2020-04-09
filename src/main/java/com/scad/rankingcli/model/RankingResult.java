package com.scad.rankingcli.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class RankingResult implements Comparable<RankingResult> {

    private final String teamName;
    private int points;

    public RankingResult(final String teamName) {
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getPoints() {
        return points;
    }

    public void captureWin() {
        points += 3;
    }

    public void captureDraw() {
        points++;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final RankingResult that = (RankingResult) o;
        return new EqualsBuilder()
                .append(teamName, that.teamName)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(teamName)
                .toHashCode();
    }

    @Override
    public int compareTo(final RankingResult o) {
        final int pointComparison = this.points - o.points;
        if (pointComparison != 0) {
            return pointComparison;
        }

        final int nameComparison = this.teamName.compareToIgnoreCase(o.teamName);
        if (nameComparison == 0) {
            return 0;
        } else if (nameComparison > 0) {
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return teamName + ", " + points + " " + (points == 1 ? "pt" : "pts");
    }
}
