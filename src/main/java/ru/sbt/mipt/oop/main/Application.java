package ru.sbt.mipt.oop.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Application {

    private final SmartHomeGetter smartHome;
    private List<EventManagable> managers;

    public Application(SmartHomeGetter smartHome, List<EventManagable> managers) {
        this.smartHome = smartHome;
        this.managers = managers;
    }

    private void run() throws IOException {
        SmartHome smartHome = this.smartHome.loadSmartHome();
        // начинаем цикл обработки событий
        new EventManager(new TestEventGenerator(), this.managers).startManageEvents(smartHome);
    }

    public static void main(String... args) throws IOException {
        List<EventManagable> managers = new ArrayList<>();
        managers.add(new LightEventManager());
        managers.add(new DoorEventManager());
        managers.add(new HallEventManager(new CommandSender()));
        Application application = new Application(new SmartHomeFromJsonFile("smart-home-1.js"), managers);
        application.run();
    }
}
