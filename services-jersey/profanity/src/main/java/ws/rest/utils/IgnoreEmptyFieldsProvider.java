package ws.rest.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import ws.libs.profanity.IsSwearWord;

import javax.ws.rs.ext.ContextResolver;

public class IgnoreEmptyFieldsProvider implements ContextResolver<ObjectMapper> {

    private final ObjectMapper mapper;

    public IgnoreEmptyFieldsProvider() {
        mapper = new ObjectMapper();
        mapper.addMixIn(IsSwearWord.class, IsSwearWordMixIn.class);
    }

    @Override
    public ObjectMapper getContext(Class<?> aClass) {
        return mapper;
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    abstract class IsSwearWordMixIn {

        public IsSwearWordMixIn(@JsonProperty("containsProfanity") boolean containsProfanity,
                                @JsonProperty("input") String input,
                                @JsonProperty("output") String output) {
        }

    }
}
