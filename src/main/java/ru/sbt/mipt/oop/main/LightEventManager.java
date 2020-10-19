package ru.sbt.mipt.oop.main;

import static ru.sbt.mipt.oop.main.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.main.SensorEventType.LIGHT_ON;

public class LightEventManager implements EventManagable {

    @Override
    public void manage(SensorEvent event, SmartHome smartHome) {

            if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
                smartHome.execute(object -> {
                    if (object instanceof Light) {
                        Light light = (Light) object;
                        if (light.getId().equals(event.getObjectId())) {
                            if (event.getType() == LIGHT_ON) {
                                light.setOn(true);
                                System.out.println("Light " + light.getId() + " was turned on.");
                            } else {
                                light.setOn(false);
                                System.out.println("Light " + light.getId() + " was turned off.");
                            }
                        }
                    }
                });
            }
        }
    }
