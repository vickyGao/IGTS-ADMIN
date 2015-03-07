package com.ntu.igts.jaxrs;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import com.ntu.igts.exception.ServiceErrorException;
import com.ntu.igts.exception.ServiceWarningException;
import com.ntu.igts.exception.UnAuthorizedException;
import com.ntu.igts.i18n.MessageBuilder;
import com.ntu.igts.i18n.MessageKeys;
import com.ntu.igts.utils.CommonUtil;
import com.ntu.igts.utils.SpringUtil;

@Provider
public class ExceptionMapperSupport implements ExceptionMapper<Exception> {

    private static final Logger LOGGER = Logger.getLogger(ExceptionMapperSupport.class);

    @Context
    private HttpServletRequest webRequest;

    @Override
    public Response toResponse(Exception exception) {
        MessageBuilder messageBuilder = SpringUtil.getBean(MessageBuilder.class);
        Locale locale = CommonUtil.getLocaleFromRequest(webRequest);

        if (exception instanceof ServiceErrorException) {
            ServiceErrorException serviceErrorException = (ServiceErrorException) exception;
            String code = serviceErrorException.getCode();
            String message = messageBuilder.buildMessage(code, serviceErrorException.getParam(),
                            serviceErrorException.getMessage(), locale);
            Status status = Status.INTERNAL_SERVER_ERROR;
            LOGGER.error(message, serviceErrorException);
            return Response.ok(message, MediaType.TEXT_PLAIN).status(status).build();
        } else if (exception instanceof ServiceWarningException) {
            ServiceWarningException serviceWarningException = (ServiceWarningException) exception;
            String code = serviceWarningException.getCode();
            String message = messageBuilder.buildMessage(code, serviceWarningException.getParam(),
                            serviceWarningException.getMessage(), locale);
            Status status = Status.BAD_REQUEST;
            LOGGER.error(message, serviceWarningException);
            return Response.ok(message, MediaType.TEXT_PLAIN).status(status).build();
        } else if (exception instanceof UnAuthorizedException) {
            String message = messageBuilder.buildMessage(MessageKeys.UNAUTHORIZED, "Error 401 Unauthorized", locale);
            Status status = Status.UNAUTHORIZED;
            LOGGER.warn(message, exception);
            return Response.ok(message, MediaType.TEXT_PLAIN).status(status).build();
        } else {
            Status status = Status.INTERNAL_SERVER_ERROR;
            String message = messageBuilder.buildMessage(MessageKeys.INTERNAL_SERVER_ERROR, "Internal server error",
                            locale);
            LOGGER.error(exception.getMessage(), exception);
            return Response.ok(message, MediaType.TEXT_PLAIN).status(status).build();
        }
    }
}
