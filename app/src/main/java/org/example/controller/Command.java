package org.example.controller;

public interface Command {
    void execute(String[] args);
    String getCommandName();
}
