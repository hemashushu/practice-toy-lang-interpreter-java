# (Practice) Toy Language Interpreter - Java

<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->

<!-- code_chunk_output -->

- [(Practice) Toy Language Interpreter - Java](#practice-toy-language-interpreter-java)
  - [使用方法](#使用方法)
    - [测试](#测试)
    - [打包](#打包)
    - [进入 REPL 模式（交互模式）](#进入-repl-模式交互模式)

<!-- /code_chunk_output -->

练习单纯使用 Java 编写简单的 _玩具语言_ 解析器。

> 注：本项目是阅读和学习《Crafting Interpreters》时的随手练习，并无实际用途。程序的原理、讲解和代码的原始出处请移步 https://craftinginterpreters.com/

## 使用方法

### 测试

`$ mvn test`

### 打包

`$ mvn package`

### 进入 REPL 模式（交互模式）

`$ mvn clean package exec:java -Dexec.mainClass="org.sample.toy.Toy" -Dexec.args=""`

或者

`$ ./repl`
