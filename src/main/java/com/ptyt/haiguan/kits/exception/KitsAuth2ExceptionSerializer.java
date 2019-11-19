package com.ptyt.haiguan.kits.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lombok.SneakyThrows;

/**
 * @author: yq
 * @date: 2019/11/15 11:21
 * @description:
 */
public class KitsAuth2ExceptionSerializer extends StdSerializer<KitsAuth2Exception> {
    public KitsAuth2ExceptionSerializer() {
        super(KitsAuth2Exception.class);
    }

    @Override
    @SneakyThrows
    public void serialize(KitsAuth2Exception value, JsonGenerator gen, SerializerProvider provider) {
        gen.writeStartObject();
        gen.writeObjectField("code", 1);
        gen.writeStringField("msg", value.getMessage());
        gen.writeStringField("data", value.getErrorCode());
        gen.writeEndObject();
    }
}
