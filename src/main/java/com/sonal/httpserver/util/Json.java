package com.sonal.httpserver.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;

/*
* used to parse json using java using jackson
* */
public class Json {

private static ObjectMapper myObjectMapper= defaulObjectMapper();

private static ObjectMapper defaulObjectMapper()
{
    ObjectMapper om= new ObjectMapper();
    om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    return om;
}

public static JsonNode parse(String jsonSrc) throws IOException {
    return myObjectMapper.readTree(jsonSrc);
}

/*
* used to move the jsonnode to configuration pojo
* */
public static <A> A fromJson(JsonNode node, Class<A>clazz) throws JsonProcessingException{
    return myObjectMapper.treeToValue(node, clazz);
}

/*
* this is used to get the configuration file ito the json node
* */
public static JsonNode toJson(Object obj)
{
    return myObjectMapper.valueToTree(obj);
}

private static String generateJson(Object o, boolean pretty) throws JsonProcessingException {
    ObjectWriter objectWriter= myObjectMapper.writer();

    if(pretty)
    {
        objectWriter=objectWriter.with(SerializationFeature.INDENT_OUTPUT);
    }
    return objectWriter.writeValueAsString(o);
}

public String stringify(JsonNode node) throws JsonProcessingException {
 return generateJson(node, false);
}

public String stringifyPretty(JsonNode node) throws JsonProcessingException {
        return generateJson(node, true);
    }


}
