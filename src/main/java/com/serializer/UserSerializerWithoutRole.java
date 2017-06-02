package com.serializer;

import com.entity.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

public class UserSerializerWithoutRole extends JsonSerializer<List<User>> {
    @Override
    public void serialize(List<User> users, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException, JsonProcessingException {
        for (User user : users){
            jsonGenerator.writeObjectFieldStart(Integer.toString(user.getId()));
            jsonGenerator.writeFieldName("id");
            jsonGenerator.writeNumber(user.getId());
            jsonGenerator.writeFieldName("username");
            jsonGenerator.writeNumber(user.getUsername());
            jsonGenerator.writeFieldName("areas");
            jsonGenerator.writeObject(user.getAreas());
            jsonGenerator.writeEndObject();
        }
    }
}
