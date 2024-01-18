package com.ellot1992.gateway.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;

public class JacksonLocalDate2NumberSerializer extends JsonSerializer<LocalDate> {

    @Override
    public void serialize(LocalDate localDate, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        long timestamp = Timestamp.valueOf(localDate.atStartOfDay()).getTime();
        jsonGenerator.writeNumber(timestamp);
    }
}
