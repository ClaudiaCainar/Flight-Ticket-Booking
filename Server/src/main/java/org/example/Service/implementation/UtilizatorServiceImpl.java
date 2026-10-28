package org.example.Service.implementation;


import org.example.Domain.User;
import org.example.Repository.RepoUser;
import org.example.Service.UserService;

import java.util.ArrayList;

public class UtilizatorServiceImpl implements UserService {

    @Override
    public Integer login(String email, String password) {

        return RepoUser.authenticateUser(email, password);
    }

    @Override
    public ArrayList<User> useriAut() {
        return RepoUser.getUsersAut();
    }

    @Override
    public boolean newUser(User aux) {
        return RepoUser.nou(aux);
    }

    @Override
    public boolean update(User aux) {
        return RepoUser.updateUser(aux);
    }

    @Override
    public User cautadupaID(String id) {
        return RepoUser.getUser(id);
    }

    @Override
    public boolean stergeUser(String id) {
        return RepoUser.stergeUser(id);
    }
}
