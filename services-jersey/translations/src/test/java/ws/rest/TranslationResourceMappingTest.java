package ws.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import ws.libs.dictionary.DictionaryWord;
import ws.rest.utils.CustomObjectMapper;
import ws.rest.utils.DictionaryWordReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class TranslationResourceMappingTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new TranslationApplication();
    }


    @Test
    public void should_return_single_translation() throws URISyntaxException {
        final String word = "computer";

        DictionaryWord dictionaryWord = target().path("translate").path(word).path("first")
                .register(DictionaryWordReader.class)
                .request().header("X-Dictionary", "dict").get(DictionaryWord.class);

        assertThat(dictionaryWord.englishWord, equalTo("computer"));
        assertThat(dictionaryWord.polishWord, equalTo("komputer"));
    }

    @Test
    public void should_return_not_found_for_missing_word() throws URISyntaxException {
        final String word = "yadayada";

        Response response = target().path("translate").path(word).path("first")
                .request().header("X-Dictionary", "dict").get();

        assertThat(response.getStatus(), equalTo(404));
    }

    @Test
    public void should_return_objects_not_json_string() throws URISyntaxException {
        final String word = "home";

        List<DictionaryWord> dictionaryWords = target().path("translate").path(word)
                .register(CustomObjectMapper.class)
                .request().header("X-Dictionary", "dict").get(new GenericType<List<DictionaryWord>>() {
                });

        assertThat(dictionaryWords.size(), equalTo(24));
    }

}