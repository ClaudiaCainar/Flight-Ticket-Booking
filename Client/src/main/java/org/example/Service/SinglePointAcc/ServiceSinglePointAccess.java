package org.example.Service.SinglePointAcc;


import org.example.Service.IUserService;
import org.example.Service.Implementation.UserServiceImpl;

public class ServiceSinglePointAccess {

    private static IUserService utilizatorService;
   // private static ZborService zborService;

    static {
        utilizatorService = new UserServiceImpl();
      //  zborService=new ZborServiceImpl();
    }

  public static IUserService getUtilizatorService()
  {
    return utilizatorService;
  }

}
