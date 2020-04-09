package com.scad.rankingcli;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class RankingCommandLineInterfaceTest {

    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;
    private File file;

    @Before
    public void setUp() throws IOException {
        outContent = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        file = File.createTempFile("InputFile", "txt");
    }

    @After
    public void tearDown() throws Exception {
        outContent.close();
        System.setOut(originalOut);
        FileUtils.deleteQuietly(file);
    }

    @Test
    public void givenInvalidArgs_whenRankingCli_thenExpectErrMsg() {
        final String[] args = {};
        RankingCommandLineInterface.main(args);
        assertEquals("Please provide a file to process\n", outContent.toString());
    }

    @Test
    public void givenInvalidFile_whenRankingCli_thenExpectErrMsg() {
        final String[] args = {"/idontexist.txt"};
        RankingCommandLineInterface.main(args);
        assertEquals("File not found: idontexist.txt\n", outContent.toString());
    }

    @Test
    public void givenValidFile_whenRankingCli_thenLeagueTableOutput() throws IOException {
        final String data = "" +
                "Lions 3, Snakes 3\n" +
                "Tarantulas 1, FC Awesome 0\n" +
                "Lions 1, FC Awesome 1\n" +
                "Tarantulas 3, Snakes 1\n" +
                "Lions 4, Grouches 0";

        final String expectedOutput = "" +
                "1. Tarantulas, 6 pts\n" +
                "2. Lions, 5 pts\n" +
                "3. FC Awesome, 1 pt\n" +
                "4. Snakes, 1 pt\n" +
                "5. Grouches, 0 pts\n";

        FileUtils.writeStringToFile(file, data, "UTF-8");

        final String[] args = {file.getCanonicalPath()};
        RankingCommandLineInterface.main(args);
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void givenInvalidMatchResult_whenRankingCli_thenLeagueTableOutputWithWarning() throws IOException {
        final String data = "" +
                "Lions 3, Snakes \n" +
                "Tarantulas 1, FC Awesome 0\n" +
                "Lions 1, FC Awesome 1\n" +
                "Tarantulas 3, Snakes 1\n" +
                "Lions 4, Grouches 0";

        final String expectedOutput = "" +
                "Warning: Could not process this match result: Lions 3, Snakes \n" +
                "1. Tarantulas, 6 pts\n" +
                "2. Lions, 4 pts\n" +
                "3. FC Awesome, 1 pt\n" +
                "4. Grouches, 0 pts\n" +
                "5. Snakes, 0 pts\n";

        FileUtils.writeStringToFile(file, data, "UTF-8");

        final String[] args = {file.getCanonicalPath()};
        RankingCommandLineInterface.main(args);
        assertEquals(expectedOutput, outContent.toString());
    }
}