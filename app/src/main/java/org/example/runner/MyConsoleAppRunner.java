package org.example.runner;

import org.example.controller.CommandDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

@Component
public class MyConsoleAppRunner implements CommandLineRunner {
    @Autowired
    private CommandDispatcher commandDispatcher;

    @Override
    public void run(String... args) {
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.print("> ");
                String line = sc.nextLine();

                if (line == null || line.trim().isEmpty()) {
                    continue;
                }
                String[] inputArgs = line.trim().split(" ");

                if (Objects.equals(inputArgs[0], "exit")) {
                    System.out.println("Exiting application.");
                    break;
                }
                if (inputArgs.length < 2) {
                    System.out.println("Usage: MyConsoleAppRunner java Main <commandName> <commandDescription>");
                    continue;
                }

                if (!Objects.equals(inputArgs[0], "java") || !Objects.equals(inputArgs[1], "Main")) {
                    System.out.println("First two arguments must be 'java Main'");
                    continue;
                }

                String[] commandArgs = Arrays.copyOfRange(inputArgs, 2, inputArgs.length);
                commandDispatcher.dispatch(commandArgs);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
