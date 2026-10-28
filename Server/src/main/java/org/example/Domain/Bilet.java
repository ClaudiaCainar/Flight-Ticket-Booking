package org.example.Domain;

import java.util.Objects;

public class Bilet {

        String numePasager;
        Integer nrZbor;
        Integer nrBilet;

        public Bilet(Integer nrZbor,String numePasager,Integer nrBilet) {
            this.numePasager = numePasager;
            this.nrZbor=nrZbor;
            this.nrBilet=nrBilet;
        }

    public Bilet() {
    }

    public String getNrZbor() {
        return nrZbor.toString();
    }

    public String getNume() {
        return numePasager;
    }

    public void setNrZbor(Integer nrZbor) {
        this.nrZbor = nrZbor;
    }

    public void setNumePasager(String numePasager) {
        this.numePasager = numePasager;
    }

    public Integer getNrBilet() {
        return nrBilet;
    }

    public void setNrBilet(Integer nrBilet) {
        this.nrBilet = nrBilet;
    }

    @Override
    public String toString() {
        return "Bilet{nrBilet=" + nrBilet +
                ", persoana='" + numePasager + '\'' +
                ", nrZbor=" +  nrZbor+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bilet bilet = (Bilet) o;
        return Objects.equals(numePasager, bilet.numePasager) &&
                Objects.equals(nrZbor, bilet.nrZbor) &&
                Objects.equals(nrBilet, bilet.nrBilet);
    }


}


