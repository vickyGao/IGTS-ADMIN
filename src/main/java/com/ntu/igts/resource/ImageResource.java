package com.ntu.igts.resource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.glassfish.jersey.media.multipart.BodyPart;
import org.glassfish.jersey.media.multipart.BodyPartEntity;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.springframework.stereotype.Component;

import com.ntu.igts.constants.Constants;
import com.ntu.igts.exception.ServiceErrorException;
import com.ntu.igts.exception.ServiceWarningException;
import com.ntu.igts.i18n.MessageBuilder;
import com.ntu.igts.i18n.MessageKeys;
import com.ntu.igts.model.Image;
import com.ntu.igts.model.container.ImageList;
import com.ntu.igts.service.ImageService;
import com.ntu.igts.utils.CommonUtil;
import com.ntu.igts.utils.JsonUtil;
import com.ntu.igts.utils.MD5Util;
import com.ntu.igts.utils.StringUtil;

@Component
@Path("image")
public class ImageResource {

    private static final Logger LOGGER = Logger.getLogger(ImageResource.class);

    @Resource
    private ImageService imageService;
    @Context
    private HttpServletRequest webRequest;
    @Resource
    private MessageBuilder messageBuilder;

    @POST
    @Path("upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public String upLoad(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token, FormDataMultiPart form) {
        List<BodyPart> bodyPartList = form.getBodyParts();
        for (BodyPart bodyPart : bodyPartList) {
            if (!Constants.MEDIA_TYPE_IMAGE.equals(bodyPart.getMediaType().getType())) {
                throw new ServiceWarningException("Only image type is allowed",
                                MessageKeys.UPLOAD_MEDIA_TYPE_NOT_ALLOWED);
            }
        }
        String basePath = imageService.getStoragePath(token);
        if (StringUtil.isEmpty(basePath)) {
            throw new ServiceErrorException("Cannot find location to save image",
                            MessageKeys.CANNOT_FIND_LOACATION_FOR_IMAGE);
        }
        List<Image> uploadedImages = new ArrayList<Image>();
        for (BodyPart bodyPart : bodyPartList) {
            Image uploadedImage = saveFile(token, bodyPart, basePath);
            if (uploadedImage != null) {
                uploadedImages.add(uploadedImage);
            } else {
                // If one uploaded Image is null, means the upload operation has failure, will reverse the whole upload
                for (Image image : uploadedImages) {
                    imageService.deleteImage(token, image.getId());
                }
                uploadedImages.clear();
                throw new ServiceErrorException("Image upload fail", MessageKeys.IMAGE_UPLOAD_FAIL);
            }
        }
        int successAmount = uploadedImages.size();
        String[] param = { String.valueOf(successAmount) };
        return messageBuilder.buildMessage(MessageKeys.NUMBER_IMAGE_UPLOADED, param, successAmount
                        + "Image(s) uploaded", CommonUtil.getLocaleFromRequest(webRequest));
    }

    // save uploaded file to a defined location on the server
    private Image saveFile(String token, BodyPart bodyPart, String basePath) {
        Image uploadedImage = null;
        if (bodyPart != null) {
            try {
                BodyPartEntity bodyPartEntity = (BodyPartEntity) bodyPart.getEntity();
                MediaType type = bodyPart.getMediaType();
                InputStream fileInputStream = bodyPartEntity.getInputStream();
                byte[] byteArray = MD5Util.toByteArray(fileInputStream);
                String fileName = MD5Util.getMd5(byteArray);
                String uri = basePath + "/" + fileName + "." + type.getSubtype();
                File existingFile = new File(uri);
                if (existingFile.exists()) {
                    // If the image already exists, will just use the record in DB
                    Image existingImage = imageService.getImageByFileName(token, fileName, type.getSubtype());
                    if (existingImage == null) {
                        // If there is image with the same file name, but we cannot get the record from DB, it means
                        // this file need to be deleted, then re-create the image
                        if (existingFile.delete()) {
                            return saveFile(token, bodyPart, basePath);
                        }
                    } else {
                        return existingImage;
                    }
                } else {
                    OutputStream outpuStream = new FileOutputStream(new File(uri));
                    outpuStream.write(byteArray);
                    outpuStream.flush();
                    outpuStream.close();
                    // If the image is a new image, will send request to api to create a record in DB
                    Image image = new Image();
                    image.setUri(uri);
                    return imageService.createImage(token, image);
                }
            } catch (IOException e) {
                LOGGER.error("Cannot write image into disk", e);
            }
        }
        return uploadedImage;
    }

    @PUT
    @Path("entity")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateImage(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token, String inString) {
        Image image = JsonUtil.getPojoFromJsonString(inString, Image.class);
        Image updatedImage = imageService.updateImage(token, image);
        return JsonUtil.getJsonStringFromPojo(updatedImage);
    }

    @DELETE
    @Path("entity/{imageid}")
    @Produces(MediaType.TEXT_PLAIN)
    public void delete(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token, @PathParam("imageid") String imageId) {
        imageService.deleteImage(token, imageId);
    }

    @GET
    @Path("entity/{imageid}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getImageById(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token,
                    @PathParam("imageid") String imageId) {
        Image returnImage = imageService.getImageById(token, imageId);
        return JsonUtil.getJsonStringFromPojo(returnImage);
    }

    @GET
    @Path("entity")
    @Produces(MediaType.APPLICATION_JSON)
    public String getImagesByToken(@HeaderParam(Constants.HEADER_X_AUTH_HEADER) String token) {
        ImageList returnImageList = imageService.getImagesByToken(token);
        return JsonUtil.getJsonStringFromPojo(returnImageList);
    }
}
