package org.example.Service.Implementation;
import org.example.Service.IUserService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class UserServiceImpl implements IUserService {
   public Socket socket;
    public BufferedReader in =null;
    public PrintWriter out=null;
    public UserServiceImpl()
    {
    }


    void apel()
    {
        try {
            socket = new Socket("localhost",12345);

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Integer login(String usermane, String password) {
        apel();
        out.println("#login "+usermane+" "+password);
    //    return RepoUser.authenticateUser(email, password);
        try {
            return Integer.valueOf(in.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<String> useriAut(String tip) throws IOException {
        apel();
        out.println("#tipUseri "+tip);
        String aux=in.readLine();
        ArrayList<String> s = splitUsers(aux);

        try {
            long startTime = System.currentTimeMillis();
            long timeout = 5000; // 5 seconds timeout

            // Continue to read until a non-empty response is received or timeout is exceeded
            while (true) {
                if (in.ready()) {
                    aux = in.readLine();
                    if (aux != null && !aux.trim().isEmpty()) {
                        break;
                    }
                }
                if (System.currentTimeMillis() - startTime > timeout) {
                    throw new RuntimeException("Timeout while waiting for server response.");
                }
                Thread.sleep(100); // Wait 100 ms before trying to read again
            }

            System.out.println(aux);
            s = splitUsers(aux);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return s;
    }

    @Override
    public void newUser(String username, String pass, Integer TIP){
        apel();
        out.println("#newUser "+username+" "+pass+" "+TIP);
    }

    @Override
    public void update(String aux) {
        apel();
    out.println("#updateUser "+aux);
    }

    @Override
    public String cautadupaID(String id) throws IOException {
        apel();
       out.println("#findUser "+id);
        String s=in.readLine();
        return s;
    }

    @Override
    public void stergeUser(String id) {
        apel();
    out.println("#stergeUser "+id);
    }


    public static ArrayList<String> splitUsers(String users) {
        ArrayList<String> usersList = new ArrayList<>();

        if (users == null) {
            throw new IllegalArgumentException("Users data cannot be null");
        }

        String[] tokens = users.split(" ");
        StringBuilder user = new StringBuilder();

        for (int i = 0; i < tokens.length; i++) {
            user.append(tokens[i]).append(" ");
            // Check if this is the end of a flight info (i.e., the number of seats is the 4th token)
            if ((i + 1) % 4 == 0) {
                usersList.add(user.toString().trim());
                user.setLength(0);
            }
        }
        return usersList;
    }


}
