package ru.sbt.mipt.oop.tests;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.main.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.sbt.mipt.oop.main.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.main.SensorEventType.DOOR_OPEN;

class HallEventManagerTest {

    @Test
    void testTurnOffAllLights() {
        //given
        Door door1 = new Door(false, "1");
        Door door2 = new Door(false, "2");
        Light light1 = new Light("1", false);
        Light light2 = new Light("2", true);
        Light light3 = new Light("3", false);
        Light light4 = new Light("4", true);

        Room room1 = new Room(Arrays.asList(light1, light2),
                Arrays.asList(door1),
                "room1");
        Room room2 = new Room(Arrays.asList(light3, light4),
                Arrays.asList(door2),
                "room2");
        SmartHome smartHome = new SmartHome(Arrays.asList(room1, room2));
        //when
        new HallEventManager(new CommandSender()).turnOffAllLights(smartHome);
        boolean isSuccess = true;
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                isSuccess = !(light.isOn());
                if (!(isSuccess)) break;
            }
            if (!(isSuccess)) break;
        }
        assertTrue(isSuccess);
    }

    @Test
    void testManageToTurnOffAllLightsWhenHallDoorIsClosed() {
        //given
        List<EventManagable> managers = new ArrayList<>();
        managers.add(new HallEventManager(new CommandSender()));
        Door door1 = new Door(false, "1");
        Door door2 = new Door(false, "2");
        Light light1 = new Light("1", false);
        Light light2 = new Light("2", true);
        Light light3 = new Light("3", false);
        Light light4 = new Light("4", true);

        Room room1 = new Room(Arrays.asList(light1, light2),
                Arrays.asList(door1),
                "room1");
        Room hall = new Room(Arrays.asList(light3, light4),
                Arrays.asList(door2),
                "hall");
        SmartHome smartHome = new SmartHome(Arrays.asList(room1, hall));
        SensorEvent event = new SensorEvent(DOOR_CLOSED, "2");
        //when
        for (EventManagable manager : managers){
            manager.manage(event, smartHome);
        }
        boolean isSuccess = true;
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                isSuccess = !(light.isOn());
                if (!(isSuccess)) break;
            }
            if (!(isSuccess)) break;
        }
        //then
        assertTrue(isSuccess);
    }

    @Test
    void testIsHallDoorIsClosed() {
        //given
        List<EventManagable> managers = new ArrayList<>();
        managers.add(new HallEventManager(new CommandSender()));
        Door door1 = new Door(false, "1");
        Door door2 = new Door(false, "2");
        Light light1 = new Light("1", false);
        Light light2 = new Light("2", true);
        Light light3 = new Light("3", false);
        Light light4 = new Light("4", true);

        Room room1 = new Room(Arrays.asList(light1, light2),
                Arrays.asList(door1),
                "room1");
        Room hall = new Room(Arrays.asList(light3, light4),
                Arrays.asList(door2),
                "hall");
        SmartHome smartHome = new SmartHome(Arrays.asList(room1, hall));
        SensorEvent event = new SensorEvent(DOOR_CLOSED, "2");
        //when
        for (EventManagable manager : managers) {
            manager.manage(event, smartHome);
        }
        boolean isSuccess = door2.isOpen();
        //then
        assertFalse(isSuccess);
    }
}