package com.company.httpserver.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;

public class JsonAnalysis {
    private static ObjectMapper objectMapperAnalyse = defaultObjectMap();

    private static ObjectMapper defaultObjectMap() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    public static JsonNode parse(String src) throws IOException {
        return objectMapperAnalyse.readTree(src);
    }

    public static <T> T fromJson(JsonNode node, Class<T> tClass) throws JsonProcessingException {
        return objectMapperAnalyse.treeToValue(node, tClass);
    }

    public static JsonNode toJson(Object object) {
        return objectMapperAnalyse.valueToTree(object);
    }
    public static String generateJson(Object obj, boolean pretty) throws JsonProcessingException {
        ObjectWriter writer = objectMapperAnalyse.writer();
        if (pretty){
            writer = writer.with(SerializationFeature.INDENT_OUTPUT);
        }
        return writer.writeValueAsString(obj);
    }
    public static String stringify(JsonNode node) throws JsonProcessingException {
        return generateJson(node, false);
    }
    public static String stringifyPretty(JsonNode node) throws JsonProcessingException {
        return generateJson(node,true);
    }
}
