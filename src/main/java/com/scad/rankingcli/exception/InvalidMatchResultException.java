package com.scad.rankingcli.exception;

public class InvalidMatchResultException extends Exception {

    public InvalidMatchResultException() {
        super();
    }

    public InvalidMatchResultException(final String invalidMatchResult) {
        super("Warning: Could not process this match result: " + invalidMatchResult);
    }
}
