package by.tr.likeitnetwork.constant;


public final class CookieConstant {
    private static final int YEAR_IN_SECONDS = 60 * 60 * 24 * 365;
    private static final int HOUR_IN_SECONDS = 60 * 60;

    public static final int ACCESS_COOKIE_LIFETIME = YEAR_IN_SECONDS;
    public static final int REFRESH_COOKIE_LIFETIME = HOUR_IN_SECONDS * 2;

    private CookieConstant(){}
}