package org.sample.toy;

import org.sample.toy.Expr.Assign;
import org.sample.toy.Expr.Variable;

// 注：
// 书中采用 Visitor 模式解藕各个 *Expr 的处理程序
// 处理程序需要实现 Expr.Visitor<T> 接口，其中 T 为期望
// 输出的数据。
// 实际上不采用设计模式，直接写也是可以的，追踪调试时更简单明了
class AstPrinter implements Expr.Visitor<String> {
    String print(Expr expr) {
        return expr.accept(this);
    }

    @Override
    public String visitBinaryExpr(Expr.Binary expr) {
        return parenthesize(expr.operator.lexeme,
                expr.left, expr.right);
    }

    @Override
    public String visitGroupingExpr(Expr.Grouping expr) {
        return parenthesize("group", expr.expression);
    }

    @Override
    public String visitLiteralExpr(Expr.Literal expr) {
        if (expr.value == null)
            return "nil";
        return expr.value.toString();
    }

    @Override
    public String visitUnaryExpr(Expr.Unary expr) {
        return parenthesize(expr.operator.lexeme, expr.right);
    }

    private String parenthesize(String name, Expr... exprs) {
        StringBuilder builder = new StringBuilder();

        builder.append("(").append(name);
        for (Expr expr : exprs) {
            builder.append(" ");
            builder.append(expr.accept(this));
        }
        builder.append(")");

        return builder.toString();
    }

    public static void main(String[] args) {
        Expr expression = new Expr.Binary(
                // left
                new Expr.Unary(
                        new Token(TokenType.MINUS, "-", null, 1),
                        new Expr.Literal(123)),

                // operator
                new Token(TokenType.STAR, "*", null, 1),

                // right
                new Expr.Grouping(
                        new Expr.Literal(45.67)));

        System.out.println(new AstPrinter().print(expression));
    }

    @Override
    public String visitVariableExpr(Variable expr) {
        // TODO
        return null;
    }

    @Override
    public String visitAssignExpr(Assign expr) {
        // TODO
        return null;
    }
}