package com.scad.rankingcli;

import com.scad.rankingcli.exception.InvalidMatchResultException;
import com.scad.rankingcli.model.LeagueTable;
import com.scad.rankingcli.model.MatchResult;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class RankingCommandLineInterface {

    private final LeagueTable leagueTable;

    private RankingCommandLineInterface() {
        this.leagueTable = new LeagueTable();
    }

    public static void main(
            final String... args) {
        if (args.length == 0) {
            println("Please provide a file to process");
            return;
        }

        final RankingCommandLineInterface cli = new RankingCommandLineInterface();
        for (final String filePath : args) {
            cli.processFile(filePath);
        }

        cli.printRankingResults();
    }

    private void processFile(final String filePath) {
        final File file = new File(filePath);
        try {
            String line;
            try (final FileReader fileReader = new FileReader(file)) {
                try (final BufferedReader bufferedFileReader = new BufferedReader(fileReader)) {
                    while ((line = bufferedFileReader.readLine()) != null) {
                        try {
                            final MatchResult matchResult = MatchResult.toMatchResult(line);
                            leagueTable.captureMatchResult(matchResult);
                        } catch (final InvalidMatchResultException e) {
                            println(e.getMessage());
                        }
                    }
                }
            }
        } catch (final FileNotFoundException e) {
            println("File not found: " + file.getName());
        } catch (final IOException e) {
            println("Unable to read from file:" + file.getName());
        } catch (final Exception e) {
            println("Unknown error has occured" + file.getName());
        }
    }

    private void printRankingResults() {
        final AtomicInteger counter = new AtomicInteger();
        leagueTable.getRankingResults()
                .forEach(rankingResult ->
                        println(counter.incrementAndGet() + ". " + rankingResult));
    }

    private static void println(
            final String message) {
        System.out.println(message);
    }
}