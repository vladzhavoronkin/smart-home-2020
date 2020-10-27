package ru.sbt.mipt.oop.tests;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.main.elements.Alarm;
import ru.sbt.mipt.oop.main.alarmstates.AlarmIsOffState;
import ru.sbt.mipt.oop.main.alarmstates.AlarmIsOnState;
import ru.sbt.mipt.oop.main.alarmstates.AlarmIsSignallingState;

import static org.junit.jupiter.api.Assertions.*;

class AlarmTest {

    @Test
    void activateAlarm() {
        // given
        Alarm alarm = new Alarm();
        // when
        alarm.activateAlarm("0000");
        boolean isSuccess = alarm.isActivated() instanceof AlarmIsOnState;
        // then
        assertTrue(isSuccess);
    }

    @Test
    void deactivateAlarm() {
        // given
        Alarm alarm = new Alarm();
        // when
        alarm.activateAlarm("0000");
        alarm.deactivateAlarm("0000");
        boolean isSuccess = alarm.isActivated() instanceof AlarmIsOffState;
        // then
        assertTrue(isSuccess);
    }

    @Test
    void deactivateAlarmWithIncorrectPassword() {
        // given
        Alarm alarm = new Alarm();
        // when
        alarm.activateAlarm("0000");
        alarm.deactivateAlarm("1111");
        boolean isSuccess = alarm.isActivated() instanceof AlarmIsSignallingState;
        // then
        assertTrue(isSuccess);
    }


    @Test
    void enableAlarm() {
        // given
        Alarm alarm = new Alarm();
        // when
        alarm.enableAlarm();
        boolean isSuccess = alarm.isActivated() instanceof AlarmIsSignallingState;
        // then
        assertTrue(isSuccess);
    }
}