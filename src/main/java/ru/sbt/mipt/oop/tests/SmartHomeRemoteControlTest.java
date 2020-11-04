package ru.sbt.mipt.oop.tests;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.main.alarmstates.AlarmIsSignallingState;
import ru.sbt.mipt.oop.main.command.CommandSender;
import ru.sbt.mipt.oop.main.decorator.AlarmDecorator;
import ru.sbt.mipt.oop.main.elements.*;
import ru.sbt.mipt.oop.main.managers.*;
import ru.sbt.mipt.oop.main.remotecontrol.MakeAlarmSignallingCommand;
import ru.sbt.mipt.oop.main.remotecontrol.SmartHomeRemoteControl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

class SmartHomeRemoteControlTest {

    @Test
    void onButtonPressedWithSuccess() {
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
        SmartHome smartHome = new SmartHome(Arrays.asList(room), alarm);
        SmartHomeRemoteControl remoteControl = new SmartHomeRemoteControl("1");
        remoteControl.setButtonCommand(new MakeAlarmSignallingCommand(smartHome), "A");
        //when
        remoteControl.onButtonPressed("A", "1");
        boolean isSuccess = alarm.isActivated() instanceof AlarmIsSignallingState;
        //then
        assertTrue(isSuccess);
    }

    @Test
    void onButtonPressedWithIncorrectId() {
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
        SmartHome smartHome = new SmartHome(Arrays.asList(room), alarm);
        SmartHomeRemoteControl remoteControl = new SmartHomeRemoteControl("1");
        remoteControl.setButtonCommand(new MakeAlarmSignallingCommand(smartHome), "A");
        //when
        remoteControl.onButtonPressed("A", "2");
        boolean isSuccess = alarm.isActivated() instanceof AlarmIsSignallingState;
        //then
        assertFalse(isSuccess);
    }

    @Test
    void onButtonPressedWithIncorrectButton() {
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
        SmartHome smartHome = new SmartHome(Arrays.asList(room), alarm);
        SmartHomeRemoteControl remoteControl = new SmartHomeRemoteControl("1");
        remoteControl.setButtonCommand(new MakeAlarmSignallingCommand(smartHome), "A");
        //when
        remoteControl.onButtonPressed("1", "1");
        boolean isSuccess = alarm.isActivated() instanceof AlarmIsSignallingState;
        //then
        assertFalse(isSuccess);
    }
}