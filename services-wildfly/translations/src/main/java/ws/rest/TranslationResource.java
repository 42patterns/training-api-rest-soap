package ws.rest;

import ws.libs.dictionary.DictionaryClient;
import ws.libs.dictionary.DictionaryWord;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/translate/{word}")
public class TranslationResource {

    @Inject
    DictionaryClient dictionary;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<DictionaryWord> translations(@PathParam("word") String word) {
        List<DictionaryWord> dictionaryWords = dictionary.allTranslationsFor(word);
        return dictionaryWords;
    }


}
