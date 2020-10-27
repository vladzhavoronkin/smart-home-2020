package ru.sbt.mipt.oop.main.homeloader;

import com.google.gson.Gson;
import ru.sbt.mipt.oop.main.elements.SmartHome;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SmartHomeFromJsonFile implements SmartHomeGetter {

    private final String fileName;

    public SmartHomeFromJsonFile(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public SmartHome loadSmartHome() {
        Gson gson = new Gson();
        String json = null;
        try {
            json = new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gson.fromJson(json, SmartHome.class);
    }
}
