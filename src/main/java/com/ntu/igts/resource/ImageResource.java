package com.ntu.igts.resource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.BodyPart;
import org.glassfish.jersey.media.multipart.BodyPartEntity;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.springframework.stereotype.Component;

import com.ntu.igts.utils.MD5Util;

@Component
@Path("image")
public class ImageResource {

    @POST
    @Path("upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public String upLoad(FormDataMultiPart form) {
        List<BodyPart> bodyPartList = form.getBodyParts();
        List<String> messages = new ArrayList<String>();
        for (BodyPart bodyPart : bodyPartList) {
            messages.add(saveFile(bodyPart));
        }
        String result = "";
        for (String message : messages) {
            result += message + ",";
        }
        return result;
    }

    @GET
    @Path("upload")
    @Produces(MediaType.TEXT_PLAIN)
    public String get() {
        return "test";
    }

    // save uploaded file to a defined location on the server
    private String saveFile(BodyPart bodyPart) {
        BodyPartEntity bodyPartEntity = (BodyPartEntity) bodyPart.getEntity();
        MediaType type = bodyPart.getMediaType();
        InputStream fileInputStream = bodyPartEntity.getInputStream();
        byte[] byteArray = MD5Util.toByteArray(fileInputStream);
        String fileName = MD5Util.getMd5(byteArray);
        String serverLocation = "D:/image/" + fileName + "." + type.getSubtype();
        try {
            File existingFile = new File(serverLocation);
            if (existingFile.exists()) {
                return "file already exits!";
            } else {
                OutputStream outpuStream = new FileOutputStream(new File(serverLocation));
                outpuStream.write(byteArray);
                outpuStream.flush();
                outpuStream.close();
                return "save file to " + serverLocation;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "exception happens";
        }

    }
}
