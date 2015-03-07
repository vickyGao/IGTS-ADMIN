package com.ntu.igts.constants;

import java.util.Locale;

public class Constants {

    /** Configuration file */
    public static final String MGMT_PROPS_FILE = "igts_user.properties";
    public static final String HTTP_PROTOCOL = "http://";
    public static final String MGMT_HOST_PROPS_KEY = "igts.host";
    public static final String MGMT_PORT_PROPS_KEY = "igts.port";
    public static final String MGMT_BASE_URI_PROPS_KEY = "igts.api.path";

    /** Field name id used in BaseModel */
    public static String FIELD_ID = "id";
    /** Field name deletedYN used in BaseModel */
    public static String FIELD_DELETED_YN = "deletedYN";

    /** I18N */
    public static final String I18N_LOCALE_ATTRIBUTE = "request-locale";
    public static final String I18N_USER_LOCALE = "i18n.user.locale";
    public static final String I18N_DEFAULT_LANGUAGE = "zh-CN";
    public static final Locale I18N_DEFAULT_BUNDLE_LOCALE = Locale.ROOT;
    public static final String I18N_BUNDLE_BASE_NAME = "messages";

    /** HEADER */
    public static final String HEADER_X_AUTH_HEADER = "x-auth-token";

    /** Time format */
    public static final String DEFAULT_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /** json keys */
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

}
