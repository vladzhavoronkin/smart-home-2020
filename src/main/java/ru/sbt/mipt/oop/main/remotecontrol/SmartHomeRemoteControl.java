package ru.sbt.mipt.oop.main.remotecontrol;

import rc.RemoteControl;

import java.util.Map;

public class SmartHomeRemoteControl implements RemoteControl {

    private final Map<String, RemoteControlCommand> commandsByButtons;
    private final String id;

    public SmartHomeRemoteControl(String id, Map<String, RemoteControlCommand> commandsByButtons) {
        this.id = id;
        this.commandsByButtons = commandsByButtons;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void onButtonPressed(String button, String rcId) {
        if(rcId.equals(id) && commandsByButtons.containsKey(button)){
            commandsByButtons.get(button).execute();
        }
    }
}
