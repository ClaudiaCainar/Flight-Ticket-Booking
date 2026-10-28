package org.example.Presentation.Controller.Language;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Language {
    private List<org.example.Presentation.controller.Language.Observer> observers = new ArrayList<>();
    private String currentLanguage = "ro";
    private Map<String, String> languageTextMap = new HashMap<>();

    public Language() {
        loadLanguage(currentLanguage);
    }

    public void setLanguage(String language) {
        if (!language.equals(currentLanguage)) {
            loadLanguage(language);
            notifyObservers();
        }
    }

    private void loadLanguage(String language) {
        String filePath = getLanguageFilePath(language);
        try {
            loadLanguageTextMap(filePath);
            currentLanguage = language;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getLanguageFilePath(String language) {
        return "C:\\Users\\Claudia Mateescu\\Documents\\an3 sem2\\PS\\Proiect\\Proiect_Mateescu_Claudia-Paula\\Client\\src\\main\\java\\org\\example\\Presentation\\Controller\\Language\\" + language.toLowerCase() + ".txt";
    }

    private void loadLanguageTextMap(String filePath) throws IOException {
        languageTextMap.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    languageTextMap.put(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error reading from file: " + filePath);
            e.printStackTrace();
        }
    }

    private void notifyObservers() {
        for (org.example.Presentation.controller.Language.Observer observer : observers) {
            observer.updateLanguage();
        }
    }

    public String getLanguageText(String key) {
        //System.out.println(languageTextMap.getOrDefault(key, "404 " + key));
        return languageTextMap.getOrDefault(key, "404 " + key);
    }

    public String getCurrentLanguage() {
        return currentLanguage;
    }
}
