package by.tr.likeitnetwork.dao;

import by.tr.likeitnetwork.dao.auth.AuthDAO;
import by.tr.likeitnetwork.dao.auth.impl.AuthDAOImpl;
import by.tr.likeitnetwork.dao.datasource.DataSource;
import by.tr.likeitnetwork.dao.exception.DAOException;
import by.tr.likeitnetwork.dao.exception.DataSourceDAOException;
import by.tr.likeitnetwork.dao.theme.ThemeDAO;
import by.tr.likeitnetwork.dao.theme.impl.ThemeDAOImpl;
import by.tr.likeitnetwork.dao.topic.TopicDAO;
import by.tr.likeitnetwork.dao.topic.impl.TopicDAOImpl;
import by.tr.likeitnetwork.dao.user.UserDAO;
import by.tr.likeitnetwork.dao.user.impl.UserDAOImpl;

public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private UserDAO userDAO = new UserDAOImpl();
    private AuthDAO authDAO = new AuthDAOImpl();
    private ThemeDAO themeDAO = new ThemeDAOImpl();
    private TopicDAO topicDAO = new TopicDAOImpl();

    private DAOFactory() {
    }

    public static void initDatasource() throws DAOException {
        try {
            DataSource.init();
        } catch (DataSourceDAOException e) {
            throw new DAOException(e);
        }
    }

    public static void destroyDatasource() throws DAOException {
        try {
            DataSource.destroy();
        } catch (DataSourceDAOException e) {
            throw new DAOException(e);
        }
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public AuthDAO getAuthDAO() {
        return authDAO;
    }

    public ThemeDAO getThemeDAO() {
        return themeDAO;
    }

    public TopicDAO getTopicDAO() {
        return topicDAO;
    }
}
