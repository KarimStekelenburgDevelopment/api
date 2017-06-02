package com.serializer;


import com.entity.Area;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

public class AreaSerializerWithoutTable extends JsonSerializer<List<Area>> {
    @Override
    public void serialize(List<Area> areas, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException, JsonProcessingException {

        jsonGenerator.writeStartArray();
        for (Area area : areas) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", area.getId());
            jsonGenerator.writeStringField("name", area.getName());
            jsonGenerator.writeObjectField("users", area.getUsers());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();

    }
}