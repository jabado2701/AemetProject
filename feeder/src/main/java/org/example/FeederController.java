package org.example;

import fileTreatment.AemetFileManager;
import sensor.AemetWeatherSensor;

import java.io.IOException;
import java.util.Timer;

public class FeederController {
    public void startFunction() {
        new AemetFileManager().filecreator();
        Clock();
    }

    public void Clock() {
        java.util.TimerTask task = new ExecuteFeeder();
        Timer timer = new Timer();
        timer.schedule(task, 0, 3600000);
    }

    class ExecuteFeeder extends java.util.TimerTask {
        public void run() {
            AemetFileManager fileManager = new AemetFileManager();
            try {
                fileManager.txtCreator(new AemetWeatherSensor().read());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Ha pasado una hora");
        }
    }
}