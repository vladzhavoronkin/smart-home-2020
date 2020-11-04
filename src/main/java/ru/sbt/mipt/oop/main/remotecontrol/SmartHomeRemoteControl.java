package ru.sbt.mipt.oop.main.remotecontrol;

import rc.RemoteControl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SmartHomeRemoteControl implements RemoteControl {

    private final Map<String, RemoteControlCommand> commandsByButtons = new HashMap<>();
    private final Set<String> buttons = Set.of("A", "B", "C", "D", "1", "2", "3", "4");;
    private final String id;

    public SmartHomeRemoteControl(String id) {
        this.id = id;
    }

    public void setButtonCommand(RemoteControlCommand command, String button){
        if (buttons.contains(button)){
            commandsByButtons.put(button, command);
        }
    }

    public Map<String, RemoteControlCommand> getCommandsByButtons() {
        return commandsByButtons;
    }

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
