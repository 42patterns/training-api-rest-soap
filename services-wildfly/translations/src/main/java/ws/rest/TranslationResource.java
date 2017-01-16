package ws.rest;

import ws.libs.dictionary.DictionaryClient;
import ws.libs.dictionary.DictionaryWord;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("/translate")
@Produces(MediaType.APPLICATION_JSON)
public class TranslationResource {

    @Inject
    DictionaryClient dictionary;

    @GET
    @Path("/{word}")
    public List<DictionaryWord> translations(@PathParam("word") String word) {
        List<DictionaryWord> dictionaryWords = dictionary.allTranslationsFor(word);
        return dictionaryWords;
    }

    @GET
    @Path("/{word}/first")
    public Response firstTranslation(@PathParam("word") String word) {
        Optional<DictionaryWord> optional = dictionary.firstTranslationFor(word);

        return optional.map(d -> Response.ok(d).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<DictionaryWord> batchTranslations(@NotNull List<String> words) {
        List<DictionaryWord> translations = words.stream()
                .map(s -> dictionary.firstTranslationFor(s))
                .flatMap(o -> flatMap(o))
//                .filter(Optional::isPresent)
//                .map(Optional::get)
                .collect(Collectors.toList());

        return translations;
    }

    private Stream<DictionaryWord> flatMap(Optional<DictionaryWord> o) {
        return o.map(d -> Stream.of(d)).orElse(Stream.empty());
    }

}
