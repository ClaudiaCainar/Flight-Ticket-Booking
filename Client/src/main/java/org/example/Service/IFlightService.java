package org.example.Service;


import java.util.ArrayList;

public interface IFlightService {

    String cautaZboruri(String plecare, String destinatie);
    ArrayList<String> toateZborurile();
    void updateZbor(String plecare ,String destinatie, Integer nrZbor, Integer nr);
    void adaugaZbor(String plecare,String destinatie ,Integer numar );
    void stergeZbor(String numar );
    String cautaZbor(String text);
}
