package com.gateway.containertests.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class JsonUtils {

    private final ObjectMapper objectMapper;

    public <T> T readValue(Object value, Class<T> clazz) {
        try {
            return objectMapper.readValue((String) value, clazz);
        } catch (Exception e) {
            log.error("Could not read value", e);
        }
        return null;
    }

    public String writeToString(Object filterResponse) {
        try {
            return objectMapper.writeValueAsString(filterResponse);
        } catch (Exception e) {
            log.error("Could not write value", e);
        }
        return "";
    }
}
