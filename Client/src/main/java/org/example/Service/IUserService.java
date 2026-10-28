package org.example.Service;



import java.io.IOException;
import java.util.ArrayList;

public interface IUserService {

    Integer login(String name, String password);

    ArrayList<String> useriAut(String tip) throws IOException;

    void newUser(String username,String pass,Integer TIP);
    void update(String aux);
    String cautadupaID(String id) throws IOException;
    void stergeUser(String id);

}
