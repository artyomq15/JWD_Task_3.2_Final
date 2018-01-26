package by.tr.likeitnetwork.controller.command.impl;

import by.tr.likeitnetwork.controller.command.Command;
import by.tr.likeitnetwork.controller.constant.AttributeKey;
import by.tr.likeitnetwork.controller.constant.RedirectQuery;
import by.tr.likeitnetwork.controller.util.CookieHandler;
import by.tr.likeitnetwork.controller.util.QueryConstructor;
import by.tr.likeitnetwork.entity.Theme;
import by.tr.likeitnetwork.entity.Topic;
import by.tr.likeitnetwork.entity.User;
import by.tr.likeitnetwork.service.ServiceFactory;
import by.tr.likeitnetwork.service.exception.TopicServiceException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddTopicCommand implements Command {
    private final Logger logger = LogManager.getLogger(AddTopicCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = (Integer) request.getSession().getAttribute(AttributeKey.ID);
        if (id == null){
            response.sendRedirect(RedirectQuery.SIGN_IN);
            return;
        }

        Topic topic = new Topic();
        User user = new User();
        Theme theme = new Theme();

        topic.setHeader(request.getParameter(AttributeKey.TOPIC_HEADER));
        topic.setContext(request.getParameter(AttributeKey.TOPIC_CONTEXT));
        theme.setId(Integer.parseInt(request.getParameter(AttributeKey.TOPIC_THEME_ID)));
        topic.setTheme(theme);
        user.setId(id);
        topic.setUser(user);

        try{
            String lastRequest = CookieHandler.getLastRequest(request.getCookies());
            if (ServiceFactory.getInstance().getTopicService().addTopic(topic)){
                lastRequest = QueryConstructor.addParameter(lastRequest, AttributeKey.TOPIC_ADDED);
            } else{
                lastRequest = QueryConstructor.addParameter(lastRequest, AttributeKey.TOPIC_NOT_ADDED);
            }
            response.sendRedirect(lastRequest);
        } catch (TopicServiceException ex) {
            logger.error(ex);
            response.sendRedirect(RedirectQuery.ERROR_WITH_MESSAGE);
        }
    }
}
