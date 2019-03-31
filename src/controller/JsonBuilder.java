package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class JsonBuilder {
    private Map<String, Object> map;
    private ObjectMapper mapper;

    JsonBuilder(){
        map = new HashMap<>();
        mapper = new ObjectMapper();
    }

    public JsonBuilder append(String key, Object value) {
        map.put(key, value);
        return this;
    }
    public String build(){
        try{
            String json = mapper.writeValueAsString(map);
            return json;
        } catch (JsonProcessingException e){
            throw new IllegalArgumentException(e);
        }
    }
    public JsonBuilder appendToObject(String objectKey, String key, Object value) {
        if(!map.containsKey(objectKey)){
            map.put(objectKey, new HashMap<String, Object>());
        }
        Map<String, Object> subMap = (HashMap<String, Object>) map.get(objectKey);
        subMap.put(key, value);
        return this;
    }
}
