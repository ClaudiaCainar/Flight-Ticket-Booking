package org.example.Service.Factory;

import org.example.Service.IUserService;
import org.example.Service.Implementation.UserServiceImpl;

public class UserServiceFactory implements IUserServiceFactory {
    @Override
    public IUserService createUserService() {
        return new UserServiceImpl();
    }
}
