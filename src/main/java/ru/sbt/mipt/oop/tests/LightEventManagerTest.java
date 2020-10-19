package ru.sbt.mipt.oop.tests;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.main.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.sbt.mipt.oop.main.SensorEventType.*;

class LightEventManagerTest {

    @Test
    void testManageToTurnOnLight() {
        //given
        List<EventManagable> managers = new ArrayList<>();
        managers.add(new LightEventManager());
        Door door = new Door(false, "1");
        Light light = new Light("1", false);
        Room room = new Room(Arrays.asList(light),
                Arrays.asList(door),
                "room");
        SmartHome smartHome = new SmartHome(Arrays.asList(room), new Alarm());
        SensorEvent event = new SensorEvent(LIGHT_ON, "1");
        //when
        for (EventManagable manager : managers){
            manager.manage(event, smartHome);
        }
        boolean isSuccess = light.isOn();
        //then
        assertTrue(isSuccess);
    }

    @Test
    void testManageToTurnOffLight() {
        //given
        List<EventManagable> managers = new ArrayList<>();
        managers.add(new LightEventManager());
        Door door = new Door(false, "1");
        Light light = new Light("1", true);
        Room room = new Room(Arrays.asList(light),
                Arrays.asList(door),
                "room");
        SmartHome smartHome = new SmartHome(Arrays.asList(room), new Alarm());
        SensorEvent event = new SensorEvent(LIGHT_OFF, "1");
        //when
        for (EventManagable manager : managers){
            manager.manage(event, smartHome);
        }
        boolean isSuccess = light.isOn();
        //then
        assertFalse(isSuccess);
    }
}