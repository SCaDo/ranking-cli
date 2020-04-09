package com.scad.rankingcli.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LeagueTable {

    private final Map<String, RankingResult> rankings = new HashMap<>();

    public void captureMatchResult(final MatchResult matchResult) {
        final RankingResult homeTeam = rankings.computeIfAbsent(matchResult.getHomeTeamName(),
                func -> new RankingResult(matchResult.getHomeTeamName()));
        final RankingResult awayTeam = rankings.computeIfAbsent(matchResult.getAwayTeamName(),
                func -> new RankingResult(matchResult.getAwayTeamName()));

        final int homeTeamScore = matchResult.getHomeTeamScore();
        final int awayTeamScore = matchResult.getAwayTeamScore();

        if (homeTeamScore == awayTeamScore) {
            homeTeam.captureDraw();
            awayTeam.captureDraw();
        } else if (homeTeamScore > awayTeamScore) {
            homeTeam.captureWin();
        } else {
            awayTeam.captureWin();
        }
    }

    public List<RankingResult> getRankingResults(){
        return rankings.values()
                .stream()
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());
    }

    Map<String, RankingResult> getRankings() {
        return rankings;
    }
}
