package ws.rest;

import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScope;
import ws.libs.dictionary.BablaDictionaryClient;
import ws.libs.dictionary.DictDictionaryClient;
import ws.libs.dictionary.DictionaryClient;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.logging.Logger;

import static jdk.nashorn.internal.objects.NativeFunction.bind;

@Provider
public class DictionaryClientProducer implements Factory<DictionaryClient> {

    private Logger log = Logger.getLogger(DictionaryClientProducer.class.getName());

    @HeaderParam("X-Dictionary")
    String type;

    @Override
    public DictionaryClient provide() {
        return dictionaryClient();
    }

    @Override
    public void dispose(DictionaryClient dictionaryClient) {

    }

    public DictionaryClient dictionaryClient() {
        log.info("DictionaryClient for type=" + type);
        switch(type) {
            case "dict": return new DictDictionaryClient();
            case "babla": return new BablaDictionaryClient();
            default: throw new IllegalStateException("No dictionary available: " + type);
        }
    }


}
