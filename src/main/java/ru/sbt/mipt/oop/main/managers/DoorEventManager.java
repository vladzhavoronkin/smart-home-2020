package ru.sbt.mipt.oop.main.managers;

import ru.sbt.mipt.oop.main.events.SensorEvent;
import ru.sbt.mipt.oop.main.elements.Door;
import ru.sbt.mipt.oop.main.elements.SmartHome;

import static ru.sbt.mipt.oop.main.events.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.main.events.SensorEventType.DOOR_OPEN;

public class DoorEventManager implements EventManagable {

    @Override
    public void manage(SensorEvent event, SmartHome smartHome) {
        if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
            smartHome.execute(object -> {
                if (object instanceof Door) {
                    Door door = (Door) object;
                    if (door.getId().equals(event.getObjectId())) {
                        if (event.getType() == DOOR_OPEN) {
                            door.setOpen(true);
                            System.out.println("Door " + door.getId() + " was opened.");
                        } else {
                            door.setOpen(false);
                            System.out.println("Door " + door.getId() + " was closed.");
                        }
                    }
                }
            });
        }
    }
}