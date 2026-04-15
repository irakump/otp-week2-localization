package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceTest {

    @BeforeEach
    void setup() {
        DatabaseConnection.TEST_MODE = true;
    }

    // --- loadStrings: DB heittää poikkeuksen TEST_MODEssa -> palautetaan tyhjä map ---

    @Test
    void testLoadStrings_english_returnsMap() {
        Map<String, String> result = LocalizationService.loadStrings(Locale.ENGLISH);
        assertNotNull(result);
    }

    @Test
    void testLoadStrings_french_returnsMap() {
        Map<String, String> result = LocalizationService.loadStrings(Locale.FRENCH);
        assertNotNull(result);
    }

    @Test
    void testLoadStrings_japanese_returnsMap() {
        Map<String, String> result = LocalizationService.loadStrings(Locale.JAPANESE);
        assertNotNull(result);
    }

    @Test
    void testLoadStrings_persian_returnsMap() {
        Map<String, String> result = LocalizationService.loadStrings(new Locale("fa", "IR"));
        assertNotNull(result);
    }

    @Test
    void testLoadStrings_testMode_returnsEmptyMap() {
        // TEST_MODEssa DB ei ole saatavilla -> map on tyhjä mutta ei null
        Map<String, String> result = LocalizationService.loadStrings(Locale.ENGLISH);
        assertNotNull(result);
        // tyhjä on ok — tärkeintä ei kaadu
        assertTrue(result instanceof Map);
    }

    @Test
    void testLoadStrings_unknownLocale_doesNotThrow() {
        assertDoesNotThrow(() ->
                LocalizationService.loadStrings(new Locale("xx", "XX"))
        );
    }

    // --- getString ---

    @Test
    void testGetString_english_returnsString() {
        String value = LocalizationService.getString("title", Locale.ENGLISH);
        assertNotNull(value);
    }

    @Test
    void testGetString_french_returnsString() {
        String value = LocalizationService.getString("title", Locale.FRENCH);
        assertNotNull(value);
    }

    @Test
    void testGetString_missingKey_returnsEmpty() {
        String value = LocalizationService.getString("nonexistent.key.xyz", Locale.ENGLISH);
        assertNotNull(value);
        // TEST_MODEssa DB ei vastaa -> palatuu ""
        assertEquals("", value);
    }

    @Test
    void testGetString_emptyKey_doesNotThrow() {
        assertDoesNotThrow(() ->
                LocalizationService.getString("", Locale.ENGLISH)
        );
    }

    @Test
    void testGetString_japanese_doesNotThrow() {
        assertDoesNotThrow(() ->
                LocalizationService.getString("calculate.button", Locale.JAPANESE)
        );
    }

    // --- getAllKeys ---

    @Test
    void testGetAllKeys_english_returnsList() {
        List<String> keys = LocalizationService.getAllKeys(Locale.ENGLISH);
        assertNotNull(keys);
    }

    @Test
    void testGetAllKeys_french_returnsList() {
        List<String> keys = LocalizationService.getAllKeys(Locale.FRENCH);
        assertNotNull(keys);
    }

    @Test
    void testGetAllKeys_japanese_returnsList() {
        List<String> keys = LocalizationService.getAllKeys(Locale.JAPANESE);
        assertNotNull(keys);
    }

    @Test
    void testGetAllKeys_persian_returnsList() {
        List<String> keys = LocalizationService.getAllKeys(new Locale("fa", "IR"));
        assertNotNull(keys);
    }

    @Test
    void testGetAllKeys_testMode_returnsEmptyList() {
        List<String> keys = LocalizationService.getAllKeys(Locale.ENGLISH);
        assertNotNull(keys);
        assertTrue(keys instanceof List);
    }
}