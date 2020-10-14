package ru.sbt.mipt.oop.main;

import java.util.Scanner;

import static ru.sbt.mipt.oop.main.AlarmState.*;
import static ru.sbt.mipt.oop.main.SensorEventType.ALARM_ACTIVATE;
import static ru.sbt.mipt.oop.main.SensorEventType.ALARM_DEACTIVATE;

public class AlarmEventManager implements EventManagable {

    public AlarmEventManager() {
        this.password = "0000";
    }

    private String password;

    private void activateAlarm(Alarm alarm, String word) {
        password = word;
        alarm.setActivated(ALARM_ACTIVATED);
        System.out.println("Alarm is activated");
    }

    private void deactivateAlarm(Alarm alarm, String word) {
       if(password.equals(word)) {
           alarm.setActivated(ALARM_DEACTIVATED);
           System.out.println("Alarm is deactivated");
       } else {
           alarm.setActivated(ALARM_IS_SCREAMING);
           System.out.println("Sending sms");
       }
    }

    @Override
    public void manage(SensorEvent event, SmartHome smartHome) {
        if (event.getType() == ALARM_ACTIVATE || event.getType() == ALARM_DEACTIVATE){
            smartHome.execute(object -> {
                if (object instanceof Alarm){
                    Alarm alarm = (Alarm) object;
                    if(event.getType() == ALARM_ACTIVATE){
                        Scanner in = new Scanner(System.in);
                        System.out.println("Create password");
                        String word = in.nextLine();
                        activateAlarm(alarm, word);
                    }
                    if(event.getType() == ALARM_DEACTIVATE){
                        Scanner in = new Scanner(System.in);
                        System.out.println("Enter password");
                        String word = in.nextLine();
                        deactivateAlarm(alarm, word);
                    }
                }

            });
        }
    }
}
