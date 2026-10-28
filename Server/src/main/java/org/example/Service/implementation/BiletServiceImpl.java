package org.example.Service.implementation;


import org.example.Domain.Bilet;
import org.example.Repository.RepoBilete;
import org.example.Service.BiletService;

import java.util.ArrayList;

public class BiletServiceImpl implements BiletService {


    @Override
    public ArrayList<Bilet> cautaBiletPasager(String nume) {
        return RepoBilete.getBilet(nume);
    }

    @Override
    public ArrayList<Bilet> veziTot() {
        return RepoBilete.getBilete();
    }

    @Override
    public Boolean adaugaBilet(Bilet bilet) {
        return RepoBilete.insertBilet( bilet);
    }

    @Override
    public Boolean stergeBilet(Bilet bilet) {
        return RepoBilete.insertBilet(bilet);
    }


}
