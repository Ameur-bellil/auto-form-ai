package org.example;

import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {

    public static void main(String[] args) {
        String model = "deepseek-r1:7b";
        String csvFilePath = "/home/amer/Desktop/protos/auto-form-ai/CSVFile.csv";
        String csvContent = readCSVFile(csvFilePath);

        String prompt = "Here is a CSV file:\n" + csvContent + "\n\n" +
                "Please fill the empty fields in this CSV file as follows: FirsName, LastName, E-mail, Age \n" +
                "and saved in this CSV file content only.\n" +
                "- Row 1: John, Doe, john.doe@example.com, 30\n\n" +
                "- Row 1: Amer, bellil, amer.bellil@example.com, 50\n\n"+
                "Return only the completed CSV content. Do not add any explanations, 'think' steps, or any other text."
        ;

        String fullResponse = sendPromptToLLM(model, prompt).replaceAll("(?s)<think>.*?</think>", "").trim();
        System.out.println("LLM Response:\n" + fullResponse);
        String csvContentToSaved = extractCSV(fullResponse);
        saveToFile(csvContentToSaved, "/home/amer/Desktop/protos/auto-form-ai/CSVFile.csv");
    }

    private static String readCSVFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    private static String sendPromptToLLM(String model, String prompt) {
        String fullResponse = "";
        try {
            URL url = new URL("http://localhost:11434/api/generate");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            JSONObject requestJson = new JSONObject();
            requestJson.put("model", model);
            requestJson.put("prompt", prompt);
            requestJson.put("stream", false);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(requestJson.toString().getBytes());
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String responseLine = br.readLine();
                JSONObject responseJson = new JSONObject(responseLine);
                fullResponse = responseJson.getString("response");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return fullResponse;
    }

    private static void saveToFile(String content, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
            System.out.println("Filled CSV file saved to: " + filePath);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static String extractCSV(String response) {
        return response.replaceAll("(?s).*?```csv\\s*(.*?)\\s*```.*", "$1").trim();
    }
}
