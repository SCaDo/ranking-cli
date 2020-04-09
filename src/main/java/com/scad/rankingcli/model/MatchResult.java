package com.scad.rankingcli.model;

import com.scad.rankingcli.exception.InvalidMatchResultException;

import org.apache.commons.lang3.StringUtils;

public class MatchResult {
    private final String homeTeamName;
    private final int homeTeamScore;
    private final String awayTeamName;
    private final int awayTeamScore;

    public MatchResult(
            final String homeTeamName,
            final int homeTeamScore,
            final String awayTeamName,
            final int awayTeamScore) {
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public static MatchResult toMatchResult(
            final String rawString) throws InvalidMatchResultException {

        if (StringUtils.isBlank(rawString)) {
            throw new InvalidMatchResultException(rawString);
        }

        final String[] rawResults = rawString.split(", ");

        if (rawResults.length != 2) {
            throw new InvalidMatchResultException(rawString);
        }

        try {
            final String homeResult = rawResults[0];
            final String awayResult = rawResults[1];
            final String homeTeamName = getTeamName(homeResult);
            final String awayTeamName = getTeamName(awayResult);
            final int homeTeamScore = getTeamScore(homeResult);
            final int awayTeamScore = getTeamScore(awayResult);
            return new MatchResult(homeTeamName, homeTeamScore, awayTeamName, awayTeamScore);
        } catch (final Exception e) {
            throw new InvalidMatchResultException(rawString);
        }
    }

    private static String getTeamName(
            final String rawString) {
        final int splitIndex = rawString.lastIndexOf(' ');
        return rawString.substring(0, splitIndex);
    }

    private static int getTeamScore(
            final String rawString) throws InvalidMatchResultException {
        final int splitIndex = rawString.lastIndexOf(' ');
        final int score = Integer.parseInt(rawString.substring(splitIndex + 1));
        if (score < 0) {
            throw new InvalidMatchResultException();
        }
        return score;
    }
}
