package org.example;

import org.example.Domain.User;
import org.example.Domain.Zbor;
import org.example.Repository.RepoUser;
import org.example.Repository.RepoZboruri;
import org.example.Service.ServerService;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        int port = 12345;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Serverul este pornit și ascultă pe portul " + port + "...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Conexiune stabilită cu clientul: " + clientSocket);

                ClientHandler clientHandler = new ClientHandler(clientSocket);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + port);
            e.printStackTrace();
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
        try {
            this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            System.err.println("Error initializing client handler: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        String request;
        try {
            while ((request = in.readLine()) != null) {
                System.out.println("Request de la client: " + request);
                handleRequest(request, out);
            }
        } catch (IOException e) {
            System.err.println("I/O exception: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Error closing client socket: " + e.getMessage());
            }
        }
    }

    private void handleRequest(String request, PrintWriter out) {
        if (request.startsWith("#login")) {
            String response = processLogin(request);
            out.println(response);
        } else if (request.startsWith("#cautaZborPD")) {
            ArrayList<String> rez = processCautaZbor(request);
            String aux = "";
            for (String s : rez) {
                aux += s + " ";
            }
            out.println(aux);
        } else if (request.startsWith("#adaugaZbor")) {
            processAdaugaZbor(request);
        } else if (request.startsWith("#toateZborurile")) {
            ArrayList<String> rez = processToateZborurile(request);
            String aux = "";
            for (String s : rez) {
                aux += s + " ";
            }
            out.println(aux);
        } else if (request.startsWith("#updateZbor")) {
            processUpdateZbor(request);
        } else if (request.startsWith("#stergeZbor")) {
            processStergeZbor(request);
        } else if (request.startsWith("#zborCuNr")) {
            out.println(processGasesteZbor(request));
        } else if (request.startsWith("#newUser")) {
            processNewUser(request);
        }
        else if (request.startsWith("#stergeUser")) {
            processDelUser(request);
        }
        else if (request.startsWith("#updateUser")) {
            processUpdateUser(request);
        }
        else if (request.startsWith("#findUser")) {
            User aux=processFindUser(request);
          out.println(aux.getId()+" "+aux.getUsername()+" "+ aux.getParola()+" "+ aux.getTip());
        }
        else if (request.startsWith("#tipUseri")) {
            ArrayList<User> vec=processFindUsersType(request);
            String rez="";
            for(User aux:vec) {
                rez+=aux.getId() + " " + aux.getUsername() + " " + aux.getParola() + " " + aux.getTip()+" ";
            }
            out.println(rez);
        }
         else {
            out.println("Comandă necunoscută");
        }
    }

    private ArrayList<User> processFindUsersType(String request) {
       return RepoUser.getUsersAutTip(request.split(" ")[1]);
    }

    private User processFindUser(String request) {
    return RepoUser.getUser(request.split(" ")[1]);
    }

    private void processUpdateUser(String request) {
        String s[]=request.split(" ");
        User user=new User(Integer.valueOf(s[1]),s[2],s[3],Integer.valueOf(s[4]));
        RepoUser.updateUser(user);
    }

    private void processDelUser(String request) {
        String[] s=request.split(" ");
        RepoUser.stergeUser(s[1]);
    }

    private String processGasesteZbor(String request) {
        Zbor z = RepoZboruri.getZborCuNr(request.split(" ")[1]);
        return z.getStart() + " " + z.getDestinatie() + " " + z.getNrZbor() + " " + z.getNrLocuri();
    }

    private void processStergeZbor(String request) {
        RepoZboruri.stergeZbor(request.split(" ")[1]);
    }

    private ArrayList<String> processToateZborurile(String request) {
        ServerService service = new ServerService();
        ArrayList<String> rez = new ArrayList<>();
        ArrayList<Zbor> zboruri = service.toateZborurile();
        for (Zbor z : zboruri) {
            String s = z.getNrZbor() + " " + z.getStart() + " " + z.getDestinatie() + " " + z.getNrLocuri();
            rez.add(s);
        }
        return rez;
    }

    private String processLogin(String request) {
        ServerService service = new ServerService();
        String[] aux = request.split(" ");
        return service.authenticateUser(aux[1], aux[2]).toString();
    }

    private void processNewUser(String request) {
        ServerService service = new ServerService();
        String[] aux = request.split(" ");
         service.newUser(aux[1], aux[2],aux[3]);
    }


    private ArrayList<String> processCautaZbor(String request) {
        ServerService service = new ServerService();
        String[] aux = request.split(" ");
        ArrayList<Zbor> zbors = service.cautaZbor(aux[1], aux[2]);
        ArrayList<String> rez = new ArrayList<>();
        for (Zbor z : zbors) {
            String s = z.getStart() + " " + z.getDestinatie() + " " + z.getNrZbor() + " " + z.getNrLocuri();
            rez.add(s);
        }
        return rez;
    }

    private void processAdaugaZbor(String request) {
        ServerService service = new ServerService();
        String[] aux = request.split(" ");
        service.adaugaZbor(aux[1], aux[2], aux[3]);
    }

    private void processUpdateZbor(String request) {
        ServerService service = new ServerService();
        String[] aux = request.split(" ");
        service.updateZbor(aux[1], aux[2], aux[3], aux[4]);
    }
}
