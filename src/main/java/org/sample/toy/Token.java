package org.sample.toy;

class Token {
    final TokenType type;
    final String lexeme; // 符号的字面量，注意，这是原始字符串，比如字符串会包含双引号
    final Object literal; // 符号的值，对于数字、字符串有效
    final int line;

    Token(TokenType type, String lexeme, Object literal, int line) {
        this.type = type;
        this.lexeme = lexeme;
        this.literal = literal;
        this.line = line;
    }

    public String toString() {
        return type + " " + lexeme + " " + literal;
    }
}