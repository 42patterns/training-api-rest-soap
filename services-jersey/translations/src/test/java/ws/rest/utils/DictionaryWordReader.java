package ws.rest.utils;

import ws.libs.dictionary.DictionaryWord;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

public class DictionaryWordReader implements MessageBodyReader<DictionaryWord> {

    @Override
    public boolean isReadable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return type == DictionaryWord.class;
    }

    @Override
    public DictionaryWord readFrom(Class<DictionaryWord> aClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> multivaluedMap, InputStream inputStream) throws IOException, WebApplicationException {
        JsonObject jsonObject = Json.createReader(inputStream).readObject();
        return new DictionaryWord(jsonObject.getString("englishWord"), jsonObject.getString("polishWord"));
    }
}
