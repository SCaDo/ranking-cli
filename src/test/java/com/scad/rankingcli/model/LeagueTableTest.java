package com.scad.rankingcli.model;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LeagueTableTest {

    private LeagueTable leagueTable;

    @Before
    public void setUp() {
        leagueTable = new LeagueTable();
    }

    @Test
    public void captureMatchResult_draw() {
        final MatchResult matchResult = new MatchResult("H", 1, "A", 1);
        leagueTable.captureMatchResult(matchResult);
        final Map<String, RankingResult> rankings = leagueTable.getRankings();
        assertTrue(rankings.containsKey("H"));
        assertTrue(rankings.containsKey("A"));
        assertEquals(1, rankings.get("H").getPoints());
        assertEquals(1, rankings.get("A").getPoints());
    }

    @Test
    public void captureMatchResult_home_win() {
        final MatchResult matchResult = new MatchResult("H", 1, "A", 0);
        leagueTable.captureMatchResult(matchResult);
        final Map<String, RankingResult> rankings = leagueTable.getRankings();
        assertTrue(rankings.containsKey("H"));
        assertTrue(rankings.containsKey("A"));
        assertEquals(3, rankings.get("H").getPoints());
        assertEquals(0, rankings.get("A").getPoints());
    }

    @Test
    public void captureMatchResult_away_win() {
        final MatchResult matchResult = new MatchResult("H", 0, "A", 1);
        leagueTable.captureMatchResult(matchResult);
        final Map<String, RankingResult> rankings = leagueTable.getRankings();
        assertTrue(rankings.containsKey("H"));
        assertTrue(rankings.containsKey("A"));
        assertEquals(0, rankings.get("H").getPoints());
        assertEquals(3, rankings.get("A").getPoints());
    }

    @Test
    public void getRankingResults() {
        final MatchResult matchResult1 = new MatchResult("H", 3, "A", 1);
        final MatchResult matchResult2 = new MatchResult("A", 3, "H", 1);
        leagueTable.captureMatchResult(matchResult1);
        final List<RankingResult> snapshot1 = leagueTable.getRankingResults();
        assertEquals("[H, 3 pts, A, 0 pts]", snapshot1.toString());
        leagueTable.captureMatchResult(matchResult2);
        final List<RankingResult> snapshot2 = leagueTable.getRankingResults();
        assertEquals("[A, 3 pts, H, 3 pts]", snapshot2.toString());
    }
}