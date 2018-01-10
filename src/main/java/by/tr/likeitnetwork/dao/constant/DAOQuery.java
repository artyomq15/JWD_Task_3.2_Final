package by.tr.likeitnetwork.dao.constant;


public final class DAOQuery {
    public static final String SQL_CALL_GET_INFO_FOR_SIGN_IN_BY_LOGIN = "call getInfoForSignInByLogin(?, ?, ?, ?, ?)";
    public static final String SQL_CALL_UPDATE_TOKENS_BY_ID = "call updateTokensById(?, ?, ?)";

    public static final String SQL_SELECT_ACCESS_TOKEN_TRUE = "SELECT user_id FROM auth WHERE access_token=? AND access_token_expiration>=now();";
    public static final String SQL_SELECT_REFRESH_TOKEN_TRUE = "SELECT user_id FROM auth WHERE refresh_token=? AND refresh_token_expiration>=now();";

    public static final String SQL_SELECT_ALL_USER_BY_ID = "SELECT * FROM user WHERE user_id = ?";

    public static final String SELECT_PASSWORD_AND_SALT_BY_ID = "SELECT user_password, user_password_salt FROM user WHERE user_id = ?";
    public static final String SQL_UPDATE_NEW_PASSWORD_BY_ID = "UPDATE user SET user_password=? WHERE user_id=?;";

    public static final String SQL_INSERT_USER = "INSERT INTO user (user_name, user_email, user_login, user_password, user_password_salt) VALUES (?,?,?,?,?);";

    public static final String SQL_CALL_GET_USER_ID_BY_LOGIN = "call getUserByLogin(?, ?)";

    public static final String SQL_SELECT_LANGUAGE_ID_BY_NAME = "SELECT lang_id FROM language WHERE lang_short_name=?;";
    public static final String SQL_SELECT_ALL_THEMES_IN_DEFAULT_LANGUAGE = "SELECT theme_id, theme_default_name FROM theme;";
    public static final String SQL_SELECT_THEME_IN_DEFAULT_LANGUAGE = "SELECT theme_id, theme_default_name FROM theme WHERE theme_id=?;";

    public static final String SQL_SELECT_ALL_THEMES_IN_LOCALE_LANGUAGE = "SELECT theme_id, locale_theme_name FROM theme_has_language WHERE lang_id=?;";
    public static final String SQL_SELECT_THEME_IN_LOCALE_LANGUAGE = "SELECT theme_id, locale_theme_name FROM theme_has_language WHERE lang_id=? AND theme_id=?;";
    public static final String SQL_CALL_GET_ALL_TOPICS = "call getAllTopics(?, ?, ?, ?, ?, ?, ?)";
    public static final String SQL_CALL_ADD_TOPIC = "call addTopic(?, ?, ?, ?)";
    public static final String SQL_CALL_GET_TOPIC_BY_ID = "call getTopicById(?, ?, ?, ?, ?, ?, ?)";

    public static final String SQL_CALL_ADD_MESSAGE = "call addMessage(?, ?, ?)";
    public static final String SQL_CALL_LIKE_MESSAGE = "call likeMessage(?, ?)";
    public static final String SQL_CALL_UNLIKE_MESSAGE = "call unlikeMessage(?, ?)";
    public static final String SQL_CALL_GET_LIKED_USERS_ID = "call getLikedUsersId(?, ?)";
    public static final String SQL_CALL_GET_MESSAGES_BY_TOPIC_ID = "call getMessagesByTopicId(?, ?, ?, ?, ?, ?, ?)";



    private DAOQuery() {
    }
}
