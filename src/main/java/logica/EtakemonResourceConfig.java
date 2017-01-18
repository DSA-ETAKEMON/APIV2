package logica;


import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.linking.DeclarativeLinkingFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

/**
 * Created by hixam on 13/01/17.
 */
public class EtakemonResourceConfig extends ResourceConfig {
    public EtakemonResourceConfig() {
    packages("logica");
        packages("CORS");
      //  packages("Entity");
    register(RolesAllowedDynamicFeature.class);
    register(DeclarativeLinkingFeature.class);
    register(JacksonFeature.class);
    register(MultiPartFeature.class);
}
}
