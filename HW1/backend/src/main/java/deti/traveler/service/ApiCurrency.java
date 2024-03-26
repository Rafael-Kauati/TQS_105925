package deti.traveler.service;


import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class ApiCurrency
{
    final static String CMD = "curl ", BASEURL = "https://api.freecurrencyapi.com/v1/latest?apikey="
    ,KEY = "fca_live_KBICg2n01U0bCk763zVWrJBGQfahtwxNRpOYfanQ",
            OUTPUT = " > currency.txt";

    public static void Fetch_and_Store_Currency() throws IOException, InterruptedException {
        final String fullCommand = CMD + "\"" + BASEURL + KEY + "\"" + OUTPUT;
        ProcessBuilder processBuilder = new ProcessBuilder();

        processBuilder.command("bash", "-c", fullCommand);

        Process process = processBuilder.start();

        int exitCode = process.waitFor();
        System.out.println("\nExited with error code : " + exitCode);
    }

    public static void Fetch_Currency() throws IOException, InterruptedException {
        final String fullCommand = CMD + "\"" + BASEURL + KEY + "\"" ;
        ProcessBuilder processBuilder = new ProcessBuilder();

        processBuilder.command("bash", "-c", fullCommand);

        Process process = processBuilder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        int exitCode = process.waitFor();
        System.out.println("\nExited with error code : " + exitCode);
    }

    //For developing purposes
    public static void main(String[] args) {
        try {
            Fetch_Currency();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
