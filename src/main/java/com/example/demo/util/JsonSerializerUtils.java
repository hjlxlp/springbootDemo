package com.example.demo.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Objects;

public class JsonSerializerUtils extends JsonSerializer<BigDecimal> {

    @Override
    public void serialize(BigDecimal value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (Objects.nonNull(value)) {
            BigDecimal result = StringUtil.getTwoZeroBigDecimal(value);
            jsonGenerator.writeNumber(result);
        } else {
            jsonGenerator.writeNumber(BigDecimal.ZERO);
        }
    }

}