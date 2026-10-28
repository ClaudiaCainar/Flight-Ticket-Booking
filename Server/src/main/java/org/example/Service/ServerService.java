package org.example.Service;

import org.example.Domain.User;
import org.example.Domain.Zbor;
import org.example.Repository.RepoUser;
import org.example.Repository.RepoZboruri;

import java.util.ArrayList;

public class ServerService {

    public ServerService() {
    }
        public Integer authenticateUser(String username, String password) {
        int user = RepoUser.authenticateUser(username,password);

            return  user;
        }

    public ArrayList<Zbor> cautaZbor(String plecare, String desitnatie) {
        ArrayList<Zbor> user = RepoZboruri.getZboruriStartDest(plecare,desitnatie);

            return  user;
    }

    public void adaugaZbor(String plecare, String destinatie,String nrZbor) {
        RepoZboruri.insertZbor(new Zbor(plecare,destinatie,Integer.parseInt(nrZbor)));
    }

    public ArrayList<Zbor> toateZborurile() {
        return   RepoZboruri.getZboruri();
    }

    public void updateZbor(String plecare, String destinatie,String nrZbor,String nrLocuri) {
        RepoZboruri.updateZbor(new Zbor(plecare,destinatie,Integer.parseInt(nrZbor),Integer.parseInt(nrLocuri)));
    }

    public void newUser(String aux, String aux1, String aux2) {
        RepoUser.nou(new User(aux,aux1,Integer.parseInt(aux2)));
    }
}
