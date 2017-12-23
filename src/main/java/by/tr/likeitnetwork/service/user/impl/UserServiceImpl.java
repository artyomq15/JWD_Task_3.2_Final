package by.tr.likeitnetwork.service.user.impl;


import by.tr.likeitnetwork.dao.DAOFactory;
import by.tr.likeitnetwork.dao.exception.UserDAOException;
import by.tr.likeitnetwork.dao.user.UserDAO;
import by.tr.likeitnetwork.entity.User;
import by.tr.likeitnetwork.service.exception.UserServiceException;
import by.tr.likeitnetwork.service.user.UserService;
import by.tr.likeitnetwork.service.validation.AuthValidator;

public class UserServiceImpl implements UserService {
    @Override
    public User findUserById(int id) throws UserServiceException {
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        try {
            return userDAO.findUserById(id);
        } catch (UserDAOException ex) {
            throw new UserServiceException("",ex);
        }
    }

    @Override
    public boolean changePassword(int id, String oldPassword, String newPassword, String newPasswordConfirmation) throws UserServiceException {
        if (!AuthValidator.isValidPasswordsForChanging(oldPassword, newPassword, newPasswordConfirmation)) {
            return false;
        }
        UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
        try {
            return userDAO.changePassword(id, oldPassword, newPassword);
        } catch (UserDAOException ex) {
            throw new UserServiceException(ex);
        }
    }
}
