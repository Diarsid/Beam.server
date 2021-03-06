/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.domain.services.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import diarsid.beam.server.data.daos.DaoUsers;
import diarsid.beam.server.domain.entities.jpa.PersistableUser;
import diarsid.beam.server.domain.services.exceptions.BadDataRequestArgumentsException;
import diarsid.beam.server.domain.services.exceptions.NickNameIsNotFreeException;
import diarsid.beam.server.domain.services.exceptions.UserLoginInvalidException;
import diarsid.beam.server.domain.services.exceptions.UserRegistrationInvalidException;
import diarsid.beam.server.domain.services.exceptions.UsersServiceUnknownLogicException;
import diarsid.beam.server.domain.services.validation.UsersValidationService;
import diarsid.beam.server.domain.services.validation.ValidationResult;
import diarsid.beam.server.presentation.web.json.dto.JsonUserLogin;
import diarsid.beam.server.presentation.web.json.dto.JsonUserRegistration;
import diarsid.beam.server.presentation.web.services.auth.UserRole;

import static java.util.Objects.isNull;

@Component
public class UsersServiceWorker implements UsersService {
    
    private static final Logger logger;
    static {
        logger = LoggerFactory.getLogger(UsersServiceWorker.class);
    }
    
    private final DaoUsers users;
    private final UsersValidationService validation;
    
    public UsersServiceWorker(DaoUsers dao, UsersValidationService validator) {
        this.users = dao;
        this.validation = validator;
    }

    @Override
    public void checkUser(int userId) {
        if ( ! this.users.existsBy(userId) ) {
            logger.debug("User with id " + userId + " does not exist.");
            throw new BadDataRequestArgumentsException("User with id " + userId + " does not exist.");
        }
    }

    @Override
    public UserRole getNonNullUserRoleOf(int userId) {
        return UserRole.valueOfIgnoreCase(this.obtainUserNonNull(userId).getRole());
    }

    @Override
    public PersistableUser findBy(int userId) {
        return this.obtainUserNonNull(userId);
    }

    private PersistableUser obtainUserNonNull(int userId) 
            throws BadDataRequestArgumentsException {
        PersistableUser user = this.users.getUserById(userId);
        if ( isNull(user) ) {
            logger.debug("User with id " + userId + " does not exist.");
            throw new BadDataRequestArgumentsException("User with id " + userId + " does not exist.");
        } else {
            return user;
        }
    }

    @Override
    public PersistableUser ifExistsFindBy(JsonUserLogin login) {        
        ValidationResult result = this.validation.validateLoginInfo(login);
        if ( result.isOk() ) {
            PersistableUser user = this.users.getUserByNicknameAndPassword(
                    login.getNickName(), login.getPassword());
            if ( isNull(user) ) {
                logger.debug("Any user has not been found by login " + 
                        login.getNickName() + ":" + login.getPassword());
                return null;
            } else {
                return user;
            }
        } else {
            throw new UserLoginInvalidException(result);
        }        
    }

    @Override
    public PersistableUser createUserBy(JsonUserRegistration registration) {
        ValidationResult result = this.validation.validateRegistrationInfo(registration);
        if ( result.isOk() ) {
            if ( ! this.users.isNickNameFree(registration.getNickName()) ) {
                throw new NickNameIsNotFreeException(
                        "Nickname " + registration.getNickName() + " is not free.");
            }
            PersistableUser savedUser = this.users.addUser(registration.composeNewUnpersistedUser());
            if ( isNull(savedUser) ) {
                logger.debug("Unknown error during new user saving.");
                throw new UsersServiceUnknownLogicException("Unknown error during new user saving.");
            } else {
                return savedUser;
            }
        } else {
            throw new UserRegistrationInvalidException(result);
        }        
    }
    
    @Override
    public boolean isNicknameFree(String nick) {
        return this.users.isNickNameFree(nick);
    }
}
