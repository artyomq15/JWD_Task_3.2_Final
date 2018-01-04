package by.tr.likeitnetwork.controller.filter;

import by.tr.likeitnetwork.controller.constant.CookieConstant;
import by.tr.likeitnetwork.controller.constant.AttributeKey;
import by.tr.likeitnetwork.controller.constant.RedirectQuery;
import by.tr.likeitnetwork.entity.AuthToken;
import by.tr.likeitnetwork.entity.User;
import by.tr.likeitnetwork.service.ServiceFactory;
import by.tr.likeitnetwork.service.auth.AuthService;
import by.tr.likeitnetwork.service.exception.AuthServiceException;
import by.tr.likeitnetwork.util.UserHelper;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.tr.likeitnetwork.controller.constant.AttributeKey.ACCESS_TOKEN;
import static by.tr.likeitnetwork.controller.constant.AttributeKey.REFRESH_TOKEN;
import static by.tr.likeitnetwork.controller.constant.AttributeKey.ROLE;
import static by.tr.likeitnetwork.controller.constant.AttributeKey.ID;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        try{
            checkTokens(request,response);
            filterChain.doFilter(request,response);
        } catch (AuthServiceException ex){
            response.sendRedirect(RedirectQuery.ERROR_WITH_MESSAGE);
        }
    }

    private void checkTokens(HttpServletRequest request, HttpServletResponse response) throws AuthServiceException {
        HttpSession session = request.getSession(false);

        Integer roleValue = (Integer) session.getAttribute(AttributeKey.ROLE);
        if (roleValue == null) {
            System.out.println("ROLE NULL");
            session.setAttribute(ROLE, User.Role.GUEST.getRole());
            return;
        }
        if (roleValue == User.Role.GUEST.getRole()) {
            System.out.println("ROLE " + roleValue);
            return;
        }
        AuthService authService = ServiceFactory.getInstance().getAuthService();
        Cookie[] cookies = request.getCookies();

        String accessToken = UserHelper.getTokenFromCookies(cookies, AttributeKey.ACCESS_TOKEN);

        if (authService.checkAccessTokenIsRight(accessToken)) {

            System.out.println("ACCESS TRUE");
            return;
        }
        String refreshToken = UserHelper.getTokenFromCookies(cookies, AttributeKey.REFRESH_TOKEN);
        if (authService.checkRefreshTokenIsRight(refreshToken)) {

            System.out.println("REFRESH TRUE");
            AuthToken authToken = authService.getNewTokensByOld(new AuthToken(accessToken, refreshToken));
            if (authToken != null) {

                Cookie accessCookie = new Cookie(ACCESS_TOKEN, authToken.getAccessToken());
                accessCookie.setMaxAge(CookieConstant.ACCESS_COOKIE_LIFETIME);
                response.addCookie(accessCookie);

                Cookie refreshCookie = new Cookie(REFRESH_TOKEN, authToken.getRefreshToken());
                refreshCookie.setMaxAge(CookieConstant.REFRESH_COOKIE_LIFETIME);
                response.addCookie(refreshCookie);

                session.setAttribute(ROLE, User.Role.valueOf(UserHelper.parseRoleFromToken(authToken.getAccessToken())).getRole());
                session.setAttribute(ID, UserHelper.parseIdFromToken(authToken.getAccessToken()));

                return;
            }
        }
        session.setAttribute(ROLE, User.Role.GUEST.getRole());
        session.removeAttribute(ID);
        UserHelper.removeTokensFromCookies(cookies);
    }

    @Override
    public void destroy() {

    }
}
