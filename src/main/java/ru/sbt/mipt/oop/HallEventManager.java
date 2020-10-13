package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;

public class HallEventManager implements EventManagable {

    @Override
    public void manage(SensorEvent event, SmartHome smartHome) {
        if (event.getType() == SensorEventType.DOOR_CLOSED) {
            for (Room room : smartHome.getRooms()) {
                for (Door door : room.getDoors()) {
                    if (door.getId().equals(event.getObjectId()) && room.getName().equals("hall")) {
                        new AllLightsSwitcher(new CommandSender()).turnOffAllLights(smartHome);
                    }
                }
            }
        }
    }
}
