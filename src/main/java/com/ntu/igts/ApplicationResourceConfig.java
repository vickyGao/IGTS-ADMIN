package com.ntu.igts;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

@ApplicationPath("/api/*")
public class ApplicationResourceConfig extends ResourceConfig {

    public ApplicationResourceConfig() {
        packages("com.ntu.igts");
        register(MultiPartFeature.class);
        register(JacksonJsonProvider.class);
    }
}
