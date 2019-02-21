package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ResponseBuilder{
    private Map<String, Object> map;
    private ObjectMapper mapper;

    ResponseBuilder(){
        map = new HashMap<>();
        mapper = new ObjectMapper();
    }

    public ResponseBuilder append(String key, Object value) {
        map.put(key, value);
        return this;
    }
    public void build(HttpServletResponse response){
        try{
            String json = mapper.writeValueAsString(map);
            response.getWriter().write(json);
        } catch (IOException e){
            throw new IllegalArgumentException(e);
        }
    }
    public ResponseBuilder appendToObject(String objectKey, String key, Object value) {
        if(!map.containsKey(objectKey)){
            map.put(objectKey, new HashMap<String, Object>());
        }
        Map<String, Object> subMap = (HashMap<String, Object>) map.get(objectKey);
        subMap.put(key, value);
        return this;
    }
}
