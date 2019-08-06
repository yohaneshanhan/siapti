package com.siapti.service.jadwalkuliah.model;

import java.io.IOException;

import org.springframework.data.domain.PageImpl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

@SuppressWarnings("rawtypes")
public class PageSerializer extends StdSerializer<PageImpl> {
	
	private static final long serialVersionUID = 1L;

	public PageSerializer() {
		super(PageImpl.class);
	}

	@Override
	public void serialize(PageImpl value , JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		gen.writeNumberField("number", value.getNumber());
		gen.writeNumberField("size", value.getSize());
		gen.writeNumberField("totalElements", value.getTotalElements());
		gen.writeNumberField("totalPages", value.getTotalPages());
		gen.writeNumberField("numberOfElements", value.getNumberOfElements());
		gen.writeBooleanField("last", value.hasPrevious());
		gen.writeBooleanField("first", value.hasNext());
		gen.writeBooleanField("empty", value.isEmpty());
		gen.writeFieldName("sort");provider.defaultSerializeValue(value.getSort().isSorted(), gen);
		gen.writeFieldName("content");provider.defaultSerializeValue(value.getContent(), gen);
		gen.writeEndObject();
	}
}