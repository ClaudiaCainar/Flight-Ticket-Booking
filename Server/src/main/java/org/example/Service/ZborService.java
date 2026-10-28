package org.example.Service;

import org.example.Domain.Zbor;
import java.util.ArrayList;

public interface ZborService {

    ArrayList<Zbor> cautaZboruri(String plecare, String destinatie);
    Zbor cautaZbor(String nrZbor);
    ArrayList<Zbor> toateZborurile();
    Boolean sergeZbor(String nrZbor);
    void updateZbor(Zbor zbor);
    void adaugaZbor(Zbor zbor);
}
