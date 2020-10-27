package ru.sbt.mipt.oop.main.command;

public class CommandSender {

    public void sendCommand(SensorCommand command) {
        System.out.println("Pretend we're sending command " + command);
    }
}
