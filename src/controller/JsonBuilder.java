package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class JsonBuilder {
    private final Map<String, Object> map;
    private final ObjectMapper mapper;

    public JsonBuilder(){
        map = new HashMap<>();
        mapper = new ObjectMapper();
    }

    public JsonBuilder append(String key, Object value) {
        map.put(key, value);
        return this;
    }
    public String build(){
        try{
            return mapper.writeValueAsString(map);
        } catch (JsonProcessingException e){
            throw new IllegalArgumentException(e);
        }
    }
    public <T> JsonBuilder appendToObject(String objectKey, String key, T value) {
        if(!map.containsKey(objectKey)){
            map.put(objectKey, new HashMap<String, T>());
        }
        Map<String, T> subMap = (HashMap<String, T>) map.get(objectKey);
        subMap.put(key, value);
        return this;
    }
}
