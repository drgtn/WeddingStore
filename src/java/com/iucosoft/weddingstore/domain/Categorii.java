
package com.iucosoft.weddingstore.domain;

public class Categorii {
    private int id;
    private String denumire;

    public Categorii() {
    }

    public Categorii(int id, String denumire) {
        this.id = id;
        this.denumire = denumire;
    }

    public Categorii(String denumire) {
        this.denumire = denumire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    @Override
    public String toString() {
        return "Categorii{" + "id=" + id + ", denumire=" + denumire + '}';
    }
    
    
}
