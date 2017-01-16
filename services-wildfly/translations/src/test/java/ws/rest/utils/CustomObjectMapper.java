package ws.rest.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import ws.libs.dictionary.DictionaryWord;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
public class CustomObjectMapper implements ContextResolver<ObjectMapper> {

    private final ObjectMapper mapper;

    public CustomObjectMapper() {
        mapper = new ObjectMapper();
        mapper.addMixIn(DictionaryWord.class, DictionaryWordMixIn.class);
//        mapper.registerModule(new DictionaryWordModule());
    }

    @Override
    public ObjectMapper getContext(Class<?> aClass) {
        return mapper;
    }

}
