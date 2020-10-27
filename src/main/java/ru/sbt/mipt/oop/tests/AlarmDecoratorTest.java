package ru.sbt.mipt.oop.tests;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.main.command.CommandSender;
import ru.sbt.mipt.oop.main.decorator.AlarmDecorator;
import ru.sbt.mipt.oop.main.alarmstates.AlarmIsSignallingState;
import ru.sbt.mipt.oop.main.elements.*;
import ru.sbt.mipt.oop.main.events.SensorEvent;
import ru.sbt.mipt.oop.main.managers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.sbt.mipt.oop.main.events.SensorEventType.LIGHT_ON;

class AlarmDecoratorTest {

    @Test
    void manageWhileAlarmIsSignalling() {
        //given
        List<EventManagable> managers = new ArrayList<>();
        managers.add(new AlarmEventManager());
        managers.add(new AlarmDecorator(new LightEventManager()));
        managers.add(new DoorEventManager());
        managers.add (new HallEventManager(new CommandSender()));
        Door door = new Door(true, "1");
        Light light = new Light("1", false);
        Room room = new Room(Arrays.asList(light),
                Arrays.asList(door),
                "room");
        Alarm alarm = new Alarm();
        alarm.changeState(new AlarmIsSignallingState());
        SmartHome smartHome = new SmartHome(Arrays.asList(room), alarm);
        SensorEvent event = new SensorEvent(LIGHT_ON, "1");
        //when
        for (EventManagable manager : managers){
            manager.manage(event, smartHome);
        }
        boolean isSuccess = light.isOn();
        //then
        assertFalse(isSuccess);
    }
}