package com.iucosoft.weddingstore.domain;

/**
 *
 * @author Andrei
 */
public class Produse {

    private int id;
    private String denumire;
    private String descriere;
    private int pret;
    private int telefon;
    private String regiunea;
    private int idCategorie;
    private int userId;
    private byte[] imagineProdus;
   private String numeFile;
    private String numeDir;

    public String getNumeFile() {
        return numeFile;
    }

    public void setNumeFile(String numeFile) {
        this.numeFile = numeFile;
    }

    public String getNumeDir() {
        return numeDir;
    }

    public void setNumeDir(String numeDir) {
        this.numeDir = numeDir;
    }

    public byte[] getImagineProdus() {
        return imagineProdus;
    }

    public void setImagineProdus(byte[] imagineProdus) {
        this.imagineProdus = imagineProdus;
    }
   

   
    public Produse() {
    }

    public Produse(int id) {
        this.id = id;
    }

    public Produse(int id, String denumire, byte[] imagineProdus, String numeFile, String numeDir) {
        this.id = id;
        this.denumire = denumire;
        this.imagineProdus = imagineProdus;
        this.numeFile = numeFile;
        this.numeDir = numeDir;
    }

    

 
    
    

   

    public Produse(String denumire, String descriere, int pret, int telefon, String regiunea) {
        this.denumire = denumire;
        this.descriere = descriere;
        this.pret = pret;
        this.telefon = telefon;
        this.regiunea = regiunea;
    }

    public Produse(int id, String denumire, String descriere, int pret, int telefon, String regiunea, int idCategorie, int userId) {
        this.id = id;
        this.denumire = denumire;
        this.descriere = descriere;
        this.pret = pret;
        this.telefon = telefon;
        this.regiunea = regiunea;
        this.idCategorie = idCategorie;
        this.userId = userId;
    }

    public Produse(String denumire, String descriere, int pret, int telefon, String regiunea, int idCategorie) {
        this.denumire = denumire;
        this.descriere = descriere;
        this.pret = pret;
        this.telefon = telefon;
        this.regiunea = regiunea;
        this.idCategorie = idCategorie;
    }

    public Produse(String denumire, String descriere, int pret, int telefon, String regiunea, int idCategorie, byte[] imagineProdus, String numeFile, String numeDir) {
        this.denumire = denumire;
        this.descriere = descriere;
        this.pret = pret;
        this.telefon = telefon;
        this.regiunea = regiunea;
        this.idCategorie = idCategorie;
        this.imagineProdus = imagineProdus;
        this.numeFile = numeFile;
        this.numeDir = numeDir;
    }

  
    
    

    public Produse(int id, String denumire, String descriere, int pret, int telefon, String regiunea) {
        this.id = id;
        this.denumire = denumire;
        this.descriere = descriere;
        this.pret = pret;
        this.telefon = telefon;
        this.regiunea = regiunea;
    }
    

    public Produse(int id, String denumire) {
        this.id = id;
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

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public int getPret() {
        return pret;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    public int getTelefon() {
        return telefon;
    }

    public void setTelefon(int telefon) {
        this.telefon = telefon;
    }

    public String getRegiunea() {
        return regiunea;
    }

    public void setRegiunea(String regiunea) {
        this.regiunea = regiunea;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Produse{" + "id=" + id + ", denumire=" + denumire + ", descriere=" + descriere + ", pret=" + pret + ", telefon=" + telefon + ", regiunea=" + regiunea + ", idCategorie=" + idCategorie + ", userId=" + userId + '}';
    }

}
