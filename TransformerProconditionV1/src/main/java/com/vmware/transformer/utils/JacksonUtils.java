package com.vmware.transformer.utils;

import java.io.IOException;
import java.util.Arrays;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.deser.std.StdDeserializer;

public class JacksonUtils {
	ObjectMapper mapper = null;
	
	public JacksonUtils() {
		mapper = this.getObejctMapper();
		mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_CONCRETE_AND_ARRAYS);
		mapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
	}
	
	public ObjectMapper getObejctMapper(){
		ObjectMapper objectMappger = new ObjectMapper();
		
		JacksonNonBlockingObjectMapperFactory factory = new JacksonNonBlockingObjectMapperFactory();
		factory.setJsonDeserializers(Arrays.asList(new StdDeserializer[]{
		    // StdDeserializer, here, comes from Jackson (org.codehaus.jackson.map.deser.StdDeserializer)
		    new StdDeserializer.ShortDeserializer(Short.class, null),
		    new StdDeserializer.IntegerDeserializer(Integer.class, null),
		    new StdDeserializer.CharacterDeserializer(Character.class, null),
		    new StdDeserializer.LongDeserializer(Long.class, null),
		    new StdDeserializer.FloatDeserializer(Float.class, null),
		    new StdDeserializer.DoubleDeserializer(Double.class, null),
		    new StdDeserializer.NumberDeserializer(),
		    new StdDeserializer.BigDecimalDeserializer(),
		    new StdDeserializer.BigIntegerDeserializer(),
		    new StdDeserializer.SqlDateDeserializer(),
		    new StdDeserializer.StackTraceElementDeserializer(),
		    new StdDeserializer.BooleanDeserializer(Boolean.class, null),
		    new StdDeserializer.ByteDeserializer(Byte.class, null)
		}));
		objectMappger = factory.createObjectMapper();
		return objectMappger;
	}

	public String toJson(Object obj){
//	    mapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
        String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(obj);

		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
System.out.println(jsonString);
		return jsonString;
	}
	
	public Object jsonToObject(String jsonString){
//		mapper.enable(SerializationConfig.Feature.AUTO_DETECT_FIELDS);
		Object obj =null;
		try {
			obj = (Object) mapper.readValue(jsonString, Object.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
System.out.println(obj);
		return obj;
	}

}
