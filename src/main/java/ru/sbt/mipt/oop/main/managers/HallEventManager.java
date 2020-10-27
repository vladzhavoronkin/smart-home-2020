package ru.sbt.mipt.oop.main.managers;

import ru.sbt.mipt.oop.main.command.CommandSender;
import ru.sbt.mipt.oop.main.command.CommandType;
import ru.sbt.mipt.oop.main.command.SensorCommand;
import ru.sbt.mipt.oop.main.events.SensorEvent;
import ru.sbt.mipt.oop.main.elements.Door;
import ru.sbt.mipt.oop.main.elements.Light;
import ru.sbt.mipt.oop.main.elements.Room;
import ru.sbt.mipt.oop.main.elements.SmartHome;

import static ru.sbt.mipt.oop.main.events.SensorEventType.DOOR_CLOSED;

public class HallEventManager implements EventManagable {

    private final CommandSender commandSender;

    public HallEventManager(CommandSender commandSender) {
        this.commandSender = commandSender;
    }

    public void turnOffAllLights(SmartHome smartHome) {
        smartHome.execute(object -> {
            if (object instanceof Light){
                Light light = (Light) object;
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                commandSender.sendCommand(command);
            }
        });
    }

    @Override
    public void manage(SensorEvent event, SmartHome smartHome) {
        if (event.getType() == DOOR_CLOSED) {
            smartHome.execute(object -> {
                if (object instanceof Room) {
                    Room room = (Room) object;
                    if (room.getName().equals("hall")) {
                        room.execute(object1 -> {
                            if (object1 instanceof Door) {
                                turnOffAllLights(smartHome);
                            }
                        });
                    }
                }
            });
        }
    }
}
