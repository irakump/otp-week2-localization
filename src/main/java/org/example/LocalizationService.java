package org.example;

import java.sql.*;
import java.util.*;

public class LocalizationService {

    public static Map<String, String> loadStrings(Locale locale) {
        Map<String, String> strings = new HashMap<>();
        String lang = locale.getLanguage();

        try (Connection conn = DatabaseConnection.getConnection()) {

            String query = "SELECT `key`, value FROM localization_strings WHERE language = ?";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, lang);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    strings.put(rs.getString("key"), rs.getString("value"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return strings;
    }

    public static String getString(String key, Locale locale) {
        String value = "";
        String lang = locale.getLanguage();

        try (Connection conn = DatabaseConnection.getConnection()) {

            String query = "SELECT value FROM localization_strings WHERE `key` = ? AND language = ?";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, key);
                stmt.setString(2, lang);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        value = rs.getString("value");
                    }
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

     return value;

    }

    public static ArrayList<String> getAllKeys(Locale locale) {
        ArrayList<String> keys = new ArrayList<>();
        String lang = locale.getLanguage();

        String query = "SELECT `key` FROM localization_strings WHERE language = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, lang);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    keys.add(rs.getString("key"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return keys;
    }

}
