package org.example.Domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

public class Zbor {
     private String plecare;
     private String destinatie;
     private Integer nrZbor;
     private Integer nrLocuri;


    public Zbor(String plecare, String destinatie, Integer nrZbor) {
        this.plecare = plecare;
        this.destinatie = destinatie;
        this.nrZbor = nrZbor;
    }

        public Zbor(String plecare, String destinatie, Integer nrZbor,Integer nrLocuri) {
            this.plecare = plecare;
            this.destinatie = destinatie;
            this.nrZbor = nrZbor;
            this.nrLocuri=nrLocuri;
        }

        public Zbor()
        {

        }

    @Override
    public String toString() {
         String rez=this.nrZbor+" "+this.plecare+" "+this.destinatie;
        return rez;
    }

    public static void sorteza(ArrayList<Zbor> flights) {
        Collections.sort(flights, Comparator
                .comparing(Zbor::getStart)
                .thenComparing(Zbor::getDestinatie));
    }

    public String getStart() {
        return plecare;
    }

    public String getDestinatie() {
        return destinatie;
    }

    public Integer getNrZbor() {
        return nrZbor;
    }

    public void setStart(String start) {
        this.plecare = start;
    }

    public void setDestinatie(String destinatie) {
        this.destinatie = destinatie;
    }

    public void setNrZbor(Integer nrZbor) {
        this.nrZbor = nrZbor;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zbor zbor = (Zbor) o;
        return Objects.equals(plecare, zbor.plecare) &&
                Objects.equals(destinatie, zbor.destinatie) &&
                nrZbor == zbor.nrZbor;
    }

    public void setNrLocuri(Integer nrLocuri) {
        this.nrLocuri = nrLocuri;
    }

    public Integer getNrLocuri() {
        return nrLocuri;
    }
}
