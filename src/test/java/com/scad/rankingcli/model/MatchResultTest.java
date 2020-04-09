package com.scad.rankingcli.model;

import com.scad.rankingcli.exception.InvalidMatchResultException;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MatchResultTest {

    @Test
    public void givenValidInput_whenToMatchResult_thenExpectMatchResult()
            throws InvalidMatchResultException {
        final MatchResult actual = MatchResult.toMatchResult("Liverpool 3, Manchester United 0");
        assertEquals("Liverpool", actual.getHomeTeamName());
        assertEquals("Manchester United", actual.getAwayTeamName());
        assertEquals(3, actual.getHomeTeamScore());
        assertEquals(0, actual.getAwayTeamScore());
    }

    @Test(expected = InvalidMatchResultException.class)
    public void givenInValidHomeResult_whenToMatchResult_thenExpectInvalidMatchResultException()
            throws InvalidMatchResultException {
        MatchResult.toMatchResult("  , Manchester United 0");
    }

    @Test(expected = InvalidMatchResultException.class)
    public void givenInValidAwayResult_whenToMatchResult_thenExpectInvalidMatchResultException()
            throws InvalidMatchResultException {
        MatchResult.toMatchResult("Liverpool -1, 0");
    }

    @Test(expected = InvalidMatchResultException.class)
    public void givenInValidHomeTeamName_whenToMatchResult_thenExpectInvalidMatchResultException()
            throws InvalidMatchResultException {
        MatchResult.toMatchResult("3, Manchester United 0");
    }

    @Test(expected = InvalidMatchResultException.class)
    public void givenInValidHomeTeamScore_whenToMatchResult_thenExpectInvalidMatchResultException()
            throws InvalidMatchResultException {
        MatchResult.toMatchResult("Liverpool -1, Manchester United 0");
    }

    @Test(expected = InvalidMatchResultException.class)
    public void givenInValidAwayTeamName_whenToMatchResult_thenExpectInvalidMatchResultException()
            throws InvalidMatchResultException {
        MatchResult.toMatchResult("Liverpool 3, 1");
    }

    @Test(expected = InvalidMatchResultException.class)
    public void givenInValidAwayTeamScore_whenToMatchResult_thenExpectInvalidMatchResultException()
            throws InvalidMatchResultException {
        MatchResult.toMatchResult("Liverpool 3, Manchester United 1.5");
    }

    @Test(expected = InvalidMatchResultException.class)
    public void givenMissingComma_whenToMatchResult_thenExpectInvalidMatchResultException()
            throws InvalidMatchResultException {
        MatchResult.toMatchResult("Liverpool 3 Manchester United 1");
    }

    @Test(expected = InvalidMatchResultException.class)
    public void givenBlankInput_whenToMatchResult_thenExpectInvalidMatchResultException()
            throws InvalidMatchResultException {
        MatchResult.toMatchResult("");
    }

    @Test(expected = InvalidMatchResultException.class)
    public void givenNullInput_whenToMatchResult_thenExpectInvalidMatchResultException()
            throws InvalidMatchResultException {
        MatchResult.toMatchResult(null);
    }
}