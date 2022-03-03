package org.sample.toy;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

public class ScannerTest {

    private static List<Token> getTokens(String source) {
        Scanner scanner = new Scanner(source);
        return scanner.scanTokens();
    }

    private static boolean assertTokenEqual(Token expected, Token actual) {
        if (expected.type != actual.type) {
            return false;
        }

        switch (expected.type) {
            case NUMBER:
                return (expected.lexeme.equals(actual.lexeme) &&
                        ((Double) expected.literal).equals(actual.literal) &&
                        expected.line == actual.line);

            case IDENTIFIER:
                return (expected.lexeme.equals(actual.lexeme) &&
                        expected.literal == null && actual.literal == null &&
                        expected.line == actual.line);

            case STRING:
                return (expected.lexeme.equals(actual.lexeme) &&
                        ((String) expected.literal).equals(actual.literal) &&
                        expected.line == actual.line);

            default:
                return true;
        }
    }

    private static boolean assertTokensEquals(List<Token> expected, List<Token> actual) {
        if (expected.size() != actual.size()) {
            return false;
        }

        for (var idx = 0; idx < expected.size(); idx++) {
            if (!assertTokenEqual(expected.get(idx), actual.get(idx))) {
                return false;
            }
        }

        return true;
    }

    @Test
    public void testNumber() {
        List<String> sources = new ArrayList<>();
        List<List<Token>> expecteds = new ArrayList<>();

        sources.add("1");
        sources.add("123");
        sources.add("2.718");

        expecteds.add(
                Arrays.asList(
                        new Token(TokenType.NUMBER, "1", 1.0D, 1),
                        new Token(TokenType.EOF, "", null, 1)));
        expecteds.add(
                Arrays.asList(
                        new Token(TokenType.NUMBER, "123", 123.0D, 1),
                        new Token(TokenType.EOF, "", null, 1)));
        expecteds.add(
                Arrays.asList(
                        new Token(TokenType.NUMBER, "2.718", 2.718D, 1),
                        new Token(TokenType.EOF, "", null, 1)));

        for (var idx = 0; idx < sources.size(); idx++) {
            var source = sources.get(idx);

            var actual = getTokens(source);
            var expected = expecteds.get(idx);

            assertTrue(assertTokensEquals(expected, actual));
        }
    }

    @Test
    public void testIdentifier() {
        List<String> sources = new ArrayList<>();
        List<List<Token>> expecteds = new ArrayList<>();

        sources.add("a");
        sources.add("abc");
        sources.add("foo_bar");

        expecteds.add(
                Arrays.asList(
                        new Token(TokenType.IDENTIFIER, "a", null, 1),
                        new Token(TokenType.EOF, "", null, 1)));
        expecteds.add(
                Arrays.asList(
                        new Token(TokenType.IDENTIFIER, "abc", null, 1),
                        new Token(TokenType.EOF, "", null, 1)));
        expecteds.add(
                Arrays.asList(
                        new Token(TokenType.IDENTIFIER, "foo_bar", null, 1),
                        new Token(TokenType.EOF, "", null, 1)));

        for (var idx = 0; idx < sources.size(); idx++) {
            var source = sources.get(idx);

            var actual = getTokens(source);
            var expected = expecteds.get(idx);

            assertTrue(assertTokensEquals(expected, actual));
        }
    }

    @Test
    public void testKeywords() {
        List<String> sources = new ArrayList<>();
        List<List<Token>> expecteds = new ArrayList<>();

        sources.add("if");
        sources.add("true");
        sources.add("class");

        expecteds.add(
                Arrays.asList(
                        new Token(TokenType.IF, "if", null, 1),
                        new Token(TokenType.EOF, "", null, 1)));
        expecteds.add(
                Arrays.asList(
                        new Token(TokenType.TRUE, "true", null, 1),
                        new Token(TokenType.EOF, "", null, 1)));
        expecteds.add(
                Arrays.asList(
                        new Token(TokenType.CLASS, "class", null, 1),
                        new Token(TokenType.EOF, "", null, 1)));

        for (var idx = 0; idx < sources.size(); idx++) {
            var source = sources.get(idx);

            var actual = getTokens(source);
            var expected = expecteds.get(idx);

            assertTrue(assertTokensEquals(expected, actual));
        }
    }

    @Test
    public void testString() {
        List<String> sources = new ArrayList<>();
        List<List<Token>> expecteds = new ArrayList<>();

        sources.add("\"if\"");
        sources.add("\"foo bar\"");
        sources.add("\"hello\"//comment");

        expecteds.add(
                Arrays.asList(
                        new Token(TokenType.STRING, "\"if\"", "if", 1),
                        new Token(TokenType.EOF, "", null, 1)));
        expecteds.add(
                Arrays.asList(
                        new Token(TokenType.STRING, "\"foo bar\"", "foo bar", 1),
                        new Token(TokenType.EOF, "", null, 1)));
        expecteds.add(
                Arrays.asList(
                        new Token(TokenType.STRING, "\"hello\"", "hello", 1),
                        new Token(TokenType.EOF, "", null, 1)));

        for (var idx = 0; idx < sources.size(); idx++) {
            var source = sources.get(idx);

            var actual = getTokens(source);
            var expected = expecteds.get(idx);

            assertTrue(assertTokensEquals(expected, actual));
        }
    }

    @Test
    public void testExpressionStatement() {
        List<String> sources = new ArrayList<>();
        List<List<Token>> expecteds = new ArrayList<>();

        sources.add("1+2");
        sources.add("a>b;");

        expecteds.add(
                Arrays.asList(
                        new Token(TokenType.NUMBER, "1", 1D, 1),
                        new Token(TokenType.PLUS, "+", null, 1),
                        new Token(TokenType.NUMBER, "2", 2D, 1),
                        new Token(TokenType.EOF, "", null, 1)));
        expecteds.add(
                Arrays.asList(
                        new Token(TokenType.IDENTIFIER, "a", null, 1),
                        new Token(TokenType.GREATER, ">", null, 1),
                        new Token(TokenType.IDENTIFIER, "b", null, 1),
                        new Token(TokenType.SEMICOLON, ";", null, 1),
                        new Token(TokenType.EOF, "", null, 1)));

        for (var idx = 0; idx < sources.size(); idx++) {
            var source = sources.get(idx);

            var actual = getTokens(source);
            var expected = expecteds.get(idx);

            assertTrue(assertTokensEquals(expected, actual));
        }
    }
}
