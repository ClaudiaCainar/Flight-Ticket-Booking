package org.example.Service.implementation;



import org.example.Domain.Zbor;
import org.example.Repository.RepoZboruri;
import org.example.Service.ZborService;

import java.util.ArrayList;

public class ZborServiceImpl implements ZborService {

    @Override
    public ArrayList<Zbor> cautaZboruri(String plecare, String destinatie) {
      return RepoZboruri.getZboruriStartDest(plecare,destinatie);
    }

    @Override
    public Zbor cautaZbor(String nrZbor) {
      return   RepoZboruri.getZborCuNr(nrZbor);
    }

    @Override
    public ArrayList<Zbor> toateZborurile() {
        return RepoZboruri.getZboruri();
    }

    @Override
    public Boolean sergeZbor(String nrZbor) {
        return RepoZboruri.stergeZbor(nrZbor);
    }

    @Override
    public void updateZbor(Zbor zbor) {
        RepoZboruri.updateZbor(zbor);
    }

    @Override
    public void adaugaZbor(Zbor zbor) {
        RepoZboruri.insertZbor(zbor);
    }


}
