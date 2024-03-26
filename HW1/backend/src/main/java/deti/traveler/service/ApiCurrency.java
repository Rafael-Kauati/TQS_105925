package deti.traveler.service;


import lombok.Getter;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Service
public class ApiCurrency
{
    final  String
     CMD = "curl "
    ,BASEURL = "https://api.freecurrencyapi.com/v1/latest?apikey="
    ,KEY = "fca_live_KBICg2n01U0bCk763zVWrJBGQfahtwxNRpOYfanQ"
    ,OUTPUT = " > currency.txt";

    @Getter
    private Map<String, Double> currencyMap = new HashMap<>();

    public  void Fetch_and_Store_Currency() throws IOException, InterruptedException {
        final String fullCommand = CMD + "\"" + BASEURL + KEY + "\"" + OUTPUT;
        ProcessBuilder processBuilder = new ProcessBuilder();

        processBuilder.command("bash", "-c", fullCommand);

        Process process = processBuilder.start();

        int exitCode = process.waitFor();
        System.out.println("\nExited with error code : " + exitCode);
    }

    public  void Fetch_Currency() throws IOException, InterruptedException {
        final String fullCommand = CMD + "\"" + BASEURL + KEY + "\"";
        ProcessBuilder processBuilder = new ProcessBuilder();

        processBuilder.command("bash", "-c", fullCommand);

        Process process = processBuilder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        int exitCode = process.waitFor();
        System.out.println("\nExited with error code : " + exitCode);

        JSONObject jsonResponse = new JSONObject(response.toString());
        JSONObject data = jsonResponse.getJSONObject("data");
        for (String currency : data.keySet()) {
            double value = data.getDouble(currency);
            currencyMap.put(currency, value);
        }
    }

}
