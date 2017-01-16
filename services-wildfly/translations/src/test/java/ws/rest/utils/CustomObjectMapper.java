package ws.rest.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
public class CustomObjectMapper implements ContextResolver<ObjectMapper> {

    private final ObjectMapper mapper;

    public CustomObjectMapper() {
        mapper = new ObjectMapper();
        mapper.registerModule(new DictionaryWordModule());
    }

    @Override
    public ObjectMapper getContext(Class<?> aClass) {
        return mapper;
    }
}
