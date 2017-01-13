package ws.rest;

import ws.libs.profanity.IsSwearWord;
import ws.libs.profanity.ProfanityCheckClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

@Path("/profanity/{input}")
public class ProfanityResource {

    @Inject
    ProfanityCheckClient client;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response check(@PathParam("input") String input) {
        return Response.ok(client.profanityCheck(input)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public JAXBElement<IsSwearWord> checkXml(@PathParam("input") String input) {
        return new JAXBElement<>(new QName("profanity"), IsSwearWord.class, client.profanityCheck(input));
    }

}
