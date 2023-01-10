package org.example;

import dbManager.DropTable;
import temperatureFunctions.TemperatureFilter;

import java.io.IOException;
import java.util.Timer;

public class DatamartController {
    private final TemperatureFilter call = new TemperatureFilter();

    public DatamartController() {
    }

    public void startcallCenter() {

        java.util.TimerTask task = new ExecuteDatamart();
        Timer timer = new Timer();
        timer.schedule(task, 10000, 3600000);

    }

    class ExecuteDatamart extends java.util.TimerTask {
        public void run() {

            new DropTable();
            try {
                call.MaxTemperature();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                call.MinTemperature();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}