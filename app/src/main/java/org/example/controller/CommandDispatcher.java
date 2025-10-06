package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CommandDispatcher {
    public final Map<String, Command> commands;

    public CommandDispatcher(List<Command> commandList) {
        commands = commandList.stream().collect(Collectors.toMap(Command::getCommandName, cmd -> cmd));
    }

    public void dispatch(String[] args) {
        if( args.length < 1 ) {
            System.out.println("No command provided.");
            return;
        }

        String commandName = args[0];
        Command command = commands.get(commandName);

        if( command != null ) {
            String[] commandArgs = Arrays.copyOfRange(args, 1, args.length);
            command.execute(commandArgs);
        }else {
            System.out.println("Unknown command: " + commandName);
        }
    }
}
