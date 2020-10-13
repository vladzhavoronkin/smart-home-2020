package ru.sbt.mipt.oop;

public class AllLightsSwitcher {

    private final CommandSender commandSender;

    public AllLightsSwitcher(CommandSender commandSender) {
        this.commandSender = commandSender;
    }

    public void turnOffAllLights(SmartHome smartHome) {
        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                commandSender.sendCommand(command);
            }
        }
    }
}
