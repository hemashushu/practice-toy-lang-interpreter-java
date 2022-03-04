package org.sample.toy;

class Token {
    final TokenType type;

    // token 的字面量
    // 注意，这是原始字符串，比如字符串会包含双引号
    final String lexeme;

    // token 的值
    // 对于数字、字符串有效，对于其他 token 类型，其值为 null（包括 TokenType.IDENTIFIER）
    // 对于 TokenType.NUMBER，其值为 Double 类型的数值。
    final Object literal;

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