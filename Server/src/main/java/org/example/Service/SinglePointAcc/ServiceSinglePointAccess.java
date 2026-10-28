package org.example.Service.SinglePointAcc;


import org.example.Service.UserService;
import org.example.Service.ZborService;
import org.example.Service.implementation.UtilizatorServiceImpl;
import org.example.Service.implementation.ZborServiceImpl;

public class ServiceSinglePointAccess {

    private static UserService utilizatorService;
    private static ZborService zborService;

    static {
        utilizatorService = new UtilizatorServiceImpl();
        zborService=new ZborServiceImpl();
    }

  public static UserService getUtilizatorService()
  {
    return utilizatorService;
  }

}
