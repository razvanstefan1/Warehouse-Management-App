package model;

/**
 * Aceasta clasa contine datele comenzilor.
 * idProd reprezinta id-ul produsului comandat
 * idClient reprezinta id-ul clientului care comanda.
 */
public class Comanda {
    private int id;
    private int idProd;
    private int  idClient;
    private int cantitate;
    private String data;

    public Comanda(int id, int produs, int client, int cantintate, String data) {
        this.id = id;
        this.idProd = produs;
        this.idClient = client;
        this.cantitate = cantintate;
        this.data = data;
    }

    public Comanda() {
        this.id = -1;
        this.idProd =-1;
        this.idClient =-1;
        this.cantitate = 0;
        this.data = "  ";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantintate) {
        this.cantitate = cantintate;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
