package org.example.Service;


import org.example.Domain.Bilet;

import java.util.ArrayList;

public interface BiletService {

    ArrayList<Bilet> cautaBiletPasager(String nume);
    ArrayList<Bilet> veziTot();
    Boolean adaugaBilet(Bilet bilet);
    Boolean stergeBilet(Bilet bilet);
}
