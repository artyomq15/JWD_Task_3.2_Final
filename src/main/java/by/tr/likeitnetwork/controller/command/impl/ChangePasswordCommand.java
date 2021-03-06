package by.tr.likeitnetwork.controller.command.impl;

import by.tr.likeitnetwork.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


import by.tr.likeitnetwork.controller.constant.AttributeKey;
import by.tr.likeitnetwork.controller.constant.RedirectQuery;
import by.tr.likeitnetwork.controller.util.CookieHandler;
import by.tr.likeitnetwork.controller.util.QueryConstructor;
import by.tr.likeitnetwork.entity.input.UserInput;
import by.tr.likeitnetwork.service.ServiceFactory;
import by.tr.likeitnetwork.service.exception.UserServiceException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class ChangePasswordCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ChangePasswordCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserInput input = new UserInput();
        input.setOldPassword(request.getParameter(AttributeKey.PASSWORD));
        input.setPassword(request.getParameter(AttributeKey.NEW_PASSWORD));
        input.setConfirmation(request.getParameter(AttributeKey.CONFIRMATION));

        Integer id = (Integer) request.getSession().getAttribute(AttributeKey.ID);

        try {
            boolean success = ServiceFactory.getInstance().getUserService().changePassword(id, input);
            String lastRequest = CookieHandler.getLastRequest(request.getCookies());
            if (success) {
                lastRequest = QueryConstructor.addParameter(lastRequest, AttributeKey.PASSWORD_CHANGED);
            } else {
                lastRequest = QueryConstructor.addParameter(lastRequest, AttributeKey.PASSWORD_NOT_CHANGED);
            }
            response.sendRedirect(lastRequest);
        } catch (UserServiceException ex) {
            logger.error(ex);
            response.sendRedirect(RedirectQuery.ERROR);
        }
    }
}
