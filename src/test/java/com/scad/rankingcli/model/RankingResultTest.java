package com.scad.rankingcli.model;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RankingResultTest {

    private static final String TEAM_NAME = "Liverpool";

    @Test
    public void captureWin() {
        final int numberOfWins = new Random().nextInt(50);
        final RankingResult rankingResult = new RankingResult(TEAM_NAME);
        for (int i = 0; i < numberOfWins; i++) {
            rankingResult.captureWin();
        }
        assertEquals(TEAM_NAME, rankingResult.getTeamName());
        assertEquals(0, rankingResult.getPoints() % 3);
    }

    @Test
    public void captureDraw() {
        final int numberOfDraws = new Random().nextInt(50);
        final RankingResult rankingResult = new RankingResult("Liverpool");
        for (int i = 0; i < numberOfDraws; i++) {
            rankingResult.captureDraw();
        }
        assertEquals(TEAM_NAME, rankingResult.getTeamName());
        assertEquals(numberOfDraws, rankingResult.getPoints());
    }

    @Test
    public void testEquals() {
        final RankingResult rankingResult1 = new RankingResult(TEAM_NAME);
        rankingResult1.captureWin();
        final RankingResult rankingResult2 = new RankingResult(TEAM_NAME);
        rankingResult2.captureDraw();
        assertEquals(rankingResult1, rankingResult2);
    }

    @Test
    public void testHashCode() {
        final RankingResult rankingResult1 = new RankingResult(TEAM_NAME);
        rankingResult1.captureWin();
        final RankingResult rankingResult2 = new RankingResult(TEAM_NAME);
        rankingResult2.captureDraw();
        assertEquals(rankingResult1.hashCode(), rankingResult2.hashCode());
    }

    @Test
    public void compareTo() {
        final RankingResult rankingResult1 = new RankingResult(TEAM_NAME);
        assertTrue(rankingResult1.compareTo(rankingResult1) == 0);

        final RankingResult rankingResult2 = new RankingResult("OTeam");
        rankingResult1.captureWin();
        rankingResult2.captureDraw();
        assertTrue(rankingResult1.compareTo(rankingResult2) > 0);

        rankingResult2.captureWin();
        assertTrue(rankingResult1.compareTo(rankingResult2) < 0);

        rankingResult1.captureDraw();
        assertTrue(rankingResult1.compareTo(rankingResult2) > 0);

        final RankingResult rankingResult3 = new RankingResult("Laverpool");
        rankingResult3.captureWin();
        rankingResult3.captureDraw();
        assertTrue(rankingResult1.compareTo(rankingResult3) < 0);
    }

    @Test
    public void testToString() {
        final RankingResult rankingResult = new RankingResult(TEAM_NAME);
        rankingResult.captureDraw();
        assertEquals("Liverpool, 1 pt", rankingResult.toString());
        rankingResult.captureWin();
        assertEquals("Liverpool, 4 pts", rankingResult.toString());
    }
}