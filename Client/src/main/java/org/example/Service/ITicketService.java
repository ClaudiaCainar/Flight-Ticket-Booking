package org.example.Service;

import java.util.ArrayList;

public interface ITicketService {
    void adaugaBilet(int nrZbor, String nume, int nrBilet);

    String cautaBilet(String nrBilet);

    ArrayList<String> toateBiletele();

    void updateBilet(int nrZbor, String nume, int nrBilet);

    void stergeBilet(String nrBilet);
}
