package com.serializer;

import com.entity.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

public class UserSerializerWithoutArea extends JsonSerializer<List<User>> {
    @Override
    public void serialize(List<User> users, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException, JsonProcessingException {

        jsonGenerator.writeStartArray();
        for (User user : users){
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("id");
            jsonGenerator.writeNumber(user.getId());
            jsonGenerator.writeFieldName("username");
            jsonGenerator.writeNumber(user.getUsername());
            jsonGenerator.writeFieldName("role");
            jsonGenerator.writeObject(user.getRole());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();

    }
}
