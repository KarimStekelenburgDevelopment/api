package com.serializer;

import com.entity.Area;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

public class AreaSerializerWithoutUser extends JsonSerializer<List<Area>> {
    @Override
    public void serialize(List<Area> areas, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException, JsonProcessingException {

        jsonGenerator.writeStartArray();
        for (Area area : areas){
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", area.getId());
            jsonGenerator.writeStringField("name", area.getName());
            jsonGenerator.writeObjectField("tables", area.getTables());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
    }
}