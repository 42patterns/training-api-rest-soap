package ws.rest;

import ws.libs.profanity.ProfanityCheckClient;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class ProfanityClientProducer {

    @Produces
    public ProfanityCheckClient createClient() {
        return new ProfanityCheckClient();
    }

}
