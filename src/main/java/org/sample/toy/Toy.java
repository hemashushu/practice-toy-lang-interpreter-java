package org.sample.toy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Toy {
    public static void main(String[] args) throws IOException {
        if (args.length > 1) {
            System.out.println("Usage: jlox [script]");
            System.exit(4);
        }else if (args.length == 1) {
            runFile(args[0]);
        }else {
            runPrompt();
        }
    }

    private static void runFile(String arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void runPrompt() throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        System.out.println("Lox REPL");

        for(;;) {
            System.out.println("> ");
            String line = reader.readLine();
            if (line == null) {
                break;
            }
            run(line);
        }
    }

    private static void run(String line) {
        //
        System.out.println("todo");
    }
}
