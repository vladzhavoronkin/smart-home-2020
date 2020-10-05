package ru.sbt.mipt.oop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Application {

    private final SmartHomeGettable smartHome;
    private List<Managable> managers;

    public Application(SmartHomeGettable smartHome, List<Managable> managers) {
        this.smartHome = smartHome;
        this.managers = managers;
    }

    private void run() throws IOException {
        SmartHome smartHome = this.smartHome.getSmartHome();
        // начинаем цикл обработки событий
        new EventManager(new TestEventGenerator(), this.managers).startManageEvents(smartHome);
    }

    public static void main(String... args) throws IOException {
        List<Managable> managers = new ArrayList<>();
        managers.add(new LightEventManager());
        managers.add(new DoorEventManager());
        Application application = new Application(new SmartHomeFromJsonFile(), managers);
        application.run();
    }
}
