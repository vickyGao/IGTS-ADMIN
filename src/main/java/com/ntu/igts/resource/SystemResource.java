package com.ntu.igts.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.ntu.igts.constants.Constants;
import com.ntu.igts.utils.ConfigManagmentUtil;

@Component
@Path("system")
public class SystemResource {

    @GET
    @Path("defaultpassword")
    @Produces(MediaType.TEXT_PLAIN)
    public String getDefaultPassword() {
        return ConfigManagmentUtil.getConfigProperties(Constants.DEFAULT_PASSWORD);
    }
}
