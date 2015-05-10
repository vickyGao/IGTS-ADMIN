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
    public static final String LOGIN = "login";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    /** URL Authorization */
    public static final String URL_LOGIN = "authorization/admin";
    public static final String URL_LOGOUT = "authorization/logout";

    /** URL Admin */
    public static final String URL_ADMIN_ENTITY = "admin/entity";
    public static final String URL_ADMIN_DETAIL = "admin/detail";
    public static final String URL_ADMIN_SEARCH_TERM = "admin/search_term";
    public static final String URL_ADMIN_GET_BY_TOKEN = "admin/detail/token";

    /** URL Commodity */
    public static final String URL_COMMODITY_SEARCH_TERM = "commodity/search_term";

    /** URL Custom Module */
    public static final String URL_CUSTOM_MODULE_ENTITY = "custommodule/entity";
    public static final String URL_CUSTOM_MODULE_DETAIL = "custommodule/detail";

    /** URL Hot Commodities */
    public static final String URL_HOT_ENTITY = "hot/entity";

    /** URL Message */
    public static final String URL_MESSAGE_ENTITY = "message/entity";

    /** URL Sensitive Word */
    public static final String URL_SENSITIVE_WORD_ENTITY = "sensitiveword/entity";
    public static final String URL_SENSITIVE_WORD_SEARCH_TERM = "sensitiveword/search_term";

    /** URL Slice */
    public static final String URL_SLICE_ENTITY = "slice/entity";

    /** URL Tag */
    public static final String URL_TAG_ENTITY = "tag/entity";

    /** URL User */
    public static final String URL_USER_ENTITY = "user/entity";
    public static final String URL_USER_DETAIL = "user/detail";
    public static final String URL_USER_SEARCH_TERM = "user/search_term";
    public static final String URL_USER_GET_BY_TOKEN = "user/detail/token";

}
