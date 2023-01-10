package org.example;

import dataPresentation.ReadMaxTemperature;
import dataPresentation.ReadMinTemperature;

import static spark.Spark.get;

public class APICalls {
    void gets() {
        try {
            get("/v1/places/with-max-temperature", ((request, response) -> {
                String from = request.queryParams("from");
                String to = request.queryParams("to");
                response.header("content-type", "application/json");
                return new ReadMaxTemperature().readAllData(from, to);
            }));
        } catch (Exception e) {
            System.out.println("Error al obtener el código HTML");
        }
        try {
            get("/v1/places/with-min-temperature", ((request, response) -> {
                String from = request.queryParams("from");
                String to = request.queryParams("to");
                response.header("content-type", "application/json");
                return new ReadMinTemperature().readAllData(from, to);
            }));
        } catch (Exception e) {
            System.out.println("Error al obtener el código HTML");
        }
    }
}