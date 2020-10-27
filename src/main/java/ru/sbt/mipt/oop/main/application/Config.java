package ru.sbt.mipt.oop.main.application;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.main.adapter.EventAdapter;
import ru.sbt.mipt.oop.main.command.CommandSender;
import ru.sbt.mipt.oop.main.decorator.AlarmDecorator;
import ru.sbt.mipt.oop.main.elements.SmartHome;
import ru.sbt.mipt.oop.main.homeloader.SmartHomeFromJsonFile;
import ru.sbt.mipt.oop.main.homeloader.SmartHomeGetter;
import ru.sbt.mipt.oop.main.managers.DoorEventManager;
import ru.sbt.mipt.oop.main.managers.EventManagable;
import ru.sbt.mipt.oop.main.managers.HallEventManager;
import ru.sbt.mipt.oop.main.managers.LightEventManager;

import java.util.Collection;

@Configuration
@ComponentScan("ru.sbt.mipt.oop")
public class Config {

    @Bean
    public SmartHomeGetter smartHomeFromJsonFile(){
        return new SmartHomeFromJsonFile("smart-home-1.js");
    }

    @Bean
    public SmartHome smartHome(){
        return smartHomeFromJsonFile().loadSmartHome();
    }

    @Bean
    public EventManagable doorEventManager(){
        return new AlarmDecorator(new DoorEventManager());
    }

    @Bean
    public EventManagable lightEventManager(){
        return new AlarmDecorator(new LightEventManager());
    }

    @Bean
    public EventManagable hallDoorEventManager(){
        return new AlarmDecorator(new HallEventManager(new CommandSender()));
    }

    @Bean
    public SensorEventsManager sensorEventsManager(Collection<EventManagable> managers, SmartHome smartHome){
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        managers.stream().map(h -> new EventAdapter(h, smartHome))
                .forEach(sensorEventsManager::registerEventHandler);
        return sensorEventsManager;
    }
}
