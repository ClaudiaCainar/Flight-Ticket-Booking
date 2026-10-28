package org.example.Service;


import org.example.Domain.User;

import java.util.ArrayList;

public interface UserService {

    Integer login(String name, String password);

    ArrayList<User> useriAut();

    boolean newUser(User aux);
    boolean update(User aux);
    User cautadupaID(String id);
    boolean stergeUser(String id);

}
