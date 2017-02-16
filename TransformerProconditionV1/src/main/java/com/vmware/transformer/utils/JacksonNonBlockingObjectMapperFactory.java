package com.vmware.transformer.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.deser.std.StdDeserializer;
import org.codehaus.jackson.map.module.SimpleModule;

public class JacksonNonBlockingObjectMapperFactory {

    /**
     * Deserializer that won't block if value parsing doesn't match with target type
     * @param <T> Handled type
     */
    private static class NonBlockingDeserializer<T> extends JsonDeserializer<T> {
        private StdDeserializer<T> delegate;

        public NonBlockingDeserializer(StdDeserializer<T> _delegate){
            this.delegate = _delegate;
        }

        @Override
        public T deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            try {
                return delegate.deserialize(jp, ctxt);
            }catch (JsonMappingException e){
                // If a JSON Mapping occurs, simply returning null instead of blocking things
                return null;
            }
        }
    }

    @SuppressWarnings("rawtypes")
	private List<StdDeserializer> jsonDeserializers = new ArrayList<StdDeserializer>();

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public ObjectMapper createObjectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();

        SimpleModule customJacksonModule = new SimpleModule("customJacksonModule", new Version(1, 0, 0, null));
        for(StdDeserializer jsonDeserializer : jsonDeserializers){
            // Wrapping given deserializers with NonBlockingDeserializer
            customJacksonModule.addDeserializer(jsonDeserializer.getValueClass(), new NonBlockingDeserializer(jsonDeserializer));
        }

        objectMapper.registerModule(customJacksonModule);
        return objectMapper;
    }

    @SuppressWarnings("rawtypes")
	public JacksonNonBlockingObjectMapperFactory setJsonDeserializers(List<StdDeserializer> _jsonDeserializers){
        this.jsonDeserializers = _jsonDeserializers;
        return this;
    }
}