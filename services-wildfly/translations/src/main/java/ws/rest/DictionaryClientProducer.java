package ws.rest;

import ws.libs.dictionary.BablaDictionaryClient;
import ws.libs.dictionary.DictDictionaryClient;
import ws.libs.dictionary.DictionaryClient;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.ext.Provider;
import java.util.logging.Logger;

@Provider
@RequestScoped
public class DictionaryClientProducer {

    private Logger log = Logger.getLogger(DictionaryClientProducer.class.getName());

    @HeaderParam("X-Dictionary")
    String type;

    @Produces
    public DictionaryClient dictionaryClient() {
        log.info("DictionaryClient for type=" + type);
        switch(type) {
            case "dict": return new DictDictionaryClient();
            case "babla": return new BablaDictionaryClient();
            default: throw new IllegalStateException("No dictionary available: " + type);
        }
    }

}
