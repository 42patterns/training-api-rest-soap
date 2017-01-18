package ws.rest;

import javax.ws.rs.core.Application;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScope;
import org.glassfish.jersey.process.internal.RequestScoped;
import ws.libs.dictionary.DictionaryClient;
import ws.utils.jersey.EndpointLoggingListener;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class TranslationApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<>();
        s.add(EndpointLoggingListener.class);
        s.add(TranslationResource.class);
        s.add(InjectionFeature.class);
        return s;
    }


    public static class InjectionFeature implements Feature {
        @Override
        public boolean configure(FeatureContext ctx) {
            ctx.register(new Binder());
            return true;
        }
    }

    public static class Binder extends AbstractBinder {

        @Override
        protected void configure() {
            bindFactory(DictionaryClientProducer.class).to(DictionaryClient.class)
                    .in(RequestScoped.class);
        }
    }

}
