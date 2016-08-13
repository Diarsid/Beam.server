/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.services.domain.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import diarsid.beam.server.data.daos.DaoUsers;
import diarsid.beam.server.data.entities.jpa.PersistableUser;
import diarsid.beam.server.services.domain.exceptions.BadDataRequestArgumentsException;
import diarsid.beam.server.services.domain.exceptions.UnknownUsersServiceLogicException;
import diarsid.beam.server.services.web.auth.UserLoginRequestData;
import diarsid.beam.server.services.web.auth.UserRegistrationRequestData;
import diarsid.beam.server.services.web.auth.UserRole;

@Component
public class UsersServiceWorker implements UsersService {
    
    private static final Logger logger;
    static {
        logger = LoggerFactory.getLogger(UsersServiceWorker.class);
    }
    
    private final DaoUsers users;
    private final UsersInfoValidator validator;
    
    public UsersServiceWorker(DaoUsers dao, UsersInfoValidator validator) {
        this.users = dao;
        this.validator = validator;
    }

    @Override
    public void checkUser(int userId) {
        if ( ! this.users.existsBy(userId) ) {
            logger.debug("User with id " + userId + " does not exist.");
            throw new BadDataRequestArgumentsException("User with id " + userId + " does not exist.");
        }
    }

    @Override
    public UserRole getRoleOf(int userId) {
        return UserRole.valueOfIgnoreCase(this.obtainUser(userId).getRole());
    }

    @Override
    public PersistableUser findBy(int userId) {
        return this.obtainUser(userId);
    }

    private PersistableUser obtainUser(int userId) throws BadDataRequestArgumentsException {
        PersistableUser user = this.users.getUserById(userId);
        if ( user == null ) {
            logger.debug("User with id " + userId + " does not exist.");
            throw new BadDataRequestArgumentsException("User with id " + userId + " does not exist.");
        } else {
            return user;
        }
    }

    @Override
    public PersistableUser findBy(UserLoginRequestData login) {        
        this.validator.validateLoginInfo(login);
        PersistableUser user = this.users.getUserByNicknameAndPassword(
                login.getNickName(), login.getPassword());
        if ( user == null ) {
            logger.debug("Any user has not been found by login " + 
                    login.getNickName() + ":" + login.getPassword());
            throw new BadDataRequestArgumentsException(
                    "User with nickname " + login.getNickName() + 
                            " and given password does not exist.");
        } else {
            return user;
        }
    }

    @Override
    public PersistableUser createUserBy(UserRegistrationRequestData registration) {
        this.validator.validateRegistrationInfo(registration);
        PersistableUser savedUser = this.users.addUser(registration.composeNewUnpersistedUser());
        if ( savedUser == null ) {
            logger.debug("Unknown error during new user saving.");
            throw new UnknownUsersServiceLogicException("Unknown error during new user saving.");
        } else {
            return savedUser;
        }
    }

    @Override
    public boolean isPassValid(String pass) {
        return this.validator.isPassValid(pass);
    }

    @Override
    public boolean isNameValid(String name) {
        return this.validator.isNameValid(name);
    }
    
    @Override
    public boolean isNickValidAndFree(String nick) {
        return 
                this.validator.isNickValid(nick) && 
                this.users.isNickNameFree(nick);
    }

    @Override
    public boolean isEmailValid(String email) {
        return this.validator.isEmailValid(email);
    }
}
