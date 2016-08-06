/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.data.daos;

import java.util.List;

import org.hibernate.SessionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import diarsid.beam.server.web.services.UserLoginData;
import diarsid.beam.server.data.entities.jpa.PersistableUser;

@Repository
public class HibernateDaoUsers implements DaoUsers {
    
    private final static Logger logger;
    static {
        logger = LoggerFactory.getLogger(HibernateDaoUsers.class);
    }
    
    private final SessionFactory sessionFactory;
    
    public HibernateDaoUsers(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        logger.info("created.");
    }

    @Override
    @Transactional
    public PersistableUser getUserByNickAndPass(UserLoginData loginData) {
        return (PersistableUser) this.sessionFactory.getCurrentSession()
                .createQuery(
                        "SELECT user " +
                        "FROM PersistableUser AS user " +
                        "WHERE " +
                            "( user.nickname = :nickname ) " +
                            "AND " +
                            "( user.password = :password )")
                .setString("nickname", loginData.getNickName())
                .setString("password", loginData.getPassword())
                .uniqueResult();
    }

    @Override
    @Transactional
    public PersistableUser getUserById(int id) {
        return (PersistableUser) this.sessionFactory.getCurrentSession()
                .createQuery(
                        "SELECT user " +
                        "FROM PersistableUser AS user " +
                        "WHERE user.id = :id")
                .setInteger("id", id)
                .uniqueResult();
    }

    @Override
    @Transactional
    public boolean isNickNameFree(String nickName) {
        List<String> nicknames = (List<String>) this.sessionFactory.getCurrentSession()
                .createQuery(
                        "SELECT user.nickname " +
                        "FROM PersistableUser AS user " +
                        "WHERE user.nickname = :nickname")
                .setString("nickname", nickName)
                .list();
        logger.info("nickname free: " + nicknames.isEmpty());
        return nicknames.isEmpty();
    }

    @Override
    @Transactional
    public int addUser(PersistableUser user) {        
        return (Integer) this.sessionFactory.getCurrentSession()
                .save(user);
    }
}
