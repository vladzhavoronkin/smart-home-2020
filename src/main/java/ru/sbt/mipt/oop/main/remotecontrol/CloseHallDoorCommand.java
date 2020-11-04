package ru.sbt.mipt.oop.main.remotecontrol;

import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.main.elements.Door;
import ru.sbt.mipt.oop.main.elements.Room;
import ru.sbt.mipt.oop.main.elements.SmartHome;

@Component
public class CloseHallDoorCommand implements RemoteControlCommand{

    private final SmartHome smartHome;

    public CloseHallDoorCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(object -> {
            if (object instanceof Room){
                Room room = (Room) object;
                if (room.getName().equals("hall")){
                    room.execute(object1 -> {
                        if(object1 instanceof Door){
                            Door door = (Door) object1;
                            door.setOpen(false);
                        }
                    });
                }
            }
        });

    }
}
