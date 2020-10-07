package ru.sbt.mipt.oop.tests;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.main.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.sbt.mipt.oop.main.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.main.SensorEventType.DOOR_OPEN;

class DoorEventManagerTest {

    @Test
    void testManageToOpenDoor() {
        //given
        List<EventManagable> managers = new ArrayList<>();
        managers.add(new DoorEventManager());
        Door door = new Door(false, "1");
        Light light = new Light("1", false);
        Room room = new Room(Arrays.asList(light),
                Arrays.asList(door),
                "room");
        SmartHome smartHome = new SmartHome(Arrays.asList(room));
        SensorEvent event = new SensorEvent(DOOR_OPEN, "1");
        //when
        for (EventManagable manager : managers){
            manager.manage(event, smartHome);
        }
        boolean isSuccess = door.isOpen();
        //then
        assertTrue(isSuccess);
    }

    @Test
    void testManageToCloseDoor() {
        //given
        List<EventManagable> managers = new ArrayList<>();
        managers.add(new DoorEventManager());
        Door door = new Door(true, "1");
        Light light = new Light("1", false);
        Room room = new Room(Arrays.asList(light),
                Arrays.asList(door),
                "room");
        SmartHome smartHome = new SmartHome(Arrays.asList(room));
        SensorEvent event = new SensorEvent(DOOR_CLOSED, "1");
        //when
        for (EventManagable manager : managers){
            manager.manage(event, smartHome);
        }
        boolean isSuccess = door.isOpen();
        //then
        assertFalse(isSuccess);
    }
}