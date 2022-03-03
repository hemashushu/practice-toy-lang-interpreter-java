package org.sample.toy;

enum TokenType {
    // Single-character tokens.
    LEFT_PAREN, RIGHT_PAREN, // (, )
    LEFT_BRACE, RIGHT_BRACE, // {, }
    COMMA, DOT, SEMICOLON, // , . ;
    MINUS, PLUS, SLASH, STAR, // - + / *

    // One or two character tokens.
    BANG, BANG_EQUAL, // !, !=
    EQUAL, EQUAL_EQUAL, // =, ==
    GREATER, GREATER_EQUAL, // >, >=
    LESS, LESS_EQUAL, // <, <=

    // Literals.
    IDENTIFIER, STRING, NUMBER,

    // Keywords.
    AND, OR,
    IF, ELSE,
    TRUE, FALSE, NIL,
    VAR,
    FOR, WHILE,
    FUN, RETURN,
    CLASS, SUPER, THIS,
    PRINT,
    EOF
}
