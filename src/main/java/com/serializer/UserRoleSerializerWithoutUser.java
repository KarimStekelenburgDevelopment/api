package com.serializer;

import com.entity.UserRole;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

public class UserRoleSerializerWithoutUser extends JsonSerializer<UserRole> {
    @Override
    public void serialize(UserRole role, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException, JsonProcessingException {




            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", role.getId());
            jsonGenerator.writeStringField("name", role.getName());
            jsonGenerator.writeEndObject();


    }
}
