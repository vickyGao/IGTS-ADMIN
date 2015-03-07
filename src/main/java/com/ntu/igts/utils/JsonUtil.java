package com.ntu.igts.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ntu.igts.constants.Constants;
import com.ntu.igts.exception.ServiceWarningException;

public class JsonUtil {

    private static final Logger LOGGER = Logger.getLogger(JsonUtil.class);

    private static ObjectMapper objectMapperWithoutRoot;
    private static ObjectMapper objectMapperWithRoot;

    static {
        objectMapperWithoutRoot = new ObjectMapper();
        objectMapperWithoutRoot.setDateFormat(new SimpleDateFormat(Constants.DEFAULT_TIME_FORMAT));

        objectMapperWithRoot = new ObjectMapper();
        objectMapperWithRoot.enable(SerializationFeature.WRAP_ROOT_VALUE);
        objectMapperWithRoot.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
        objectMapperWithRoot.setDateFormat(new SimpleDateFormat(Constants.DEFAULT_TIME_FORMAT));
    }

    private JsonUtil() {
    }

    public static String getJsonStringWithRootFromPojo(Object object) {
        try {
            return objectMapperWithRoot.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            LOGGER.error("transfer fail", e);
            throw new ServiceWarningException("transfer fail");
        }
    }

    public static <T> T getPojoFromJsonStringWithRoot(String jsonString, Class<T> targetClass) {
        try {
            T object = objectMapperWithRoot.readValue(jsonString, targetClass);
            return object;
        } catch (IOException e) {
            LOGGER.error("transfer fail", e);
            throw new ServiceWarningException("transfer fail");
        }
    }

    public static String getJsonStringFromPojo(Object object) {
        try {
            return objectMapperWithoutRoot.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            LOGGER.error("transfer fail", e);
            throw new ServiceWarningException("transfer fail");
        }
    }

    public static <T> T getPojoFromJsonString(String jsonString, Class<T> targetClass) {
        try {
            T object = objectMapperWithoutRoot.readValue(jsonString, targetClass);
            return object;
        } catch (IOException e) {
            LOGGER.error("transfer fail", e);
            throw new ServiceWarningException("transfer fail");
        }
    }
}
