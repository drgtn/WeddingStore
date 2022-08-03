/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iucosoft.weddingstore.domain;

/**
 *
 * @author Andrei
 */
public class ImaginiProduse {
    private int id;
    private String numeFile;
    private String numeDir;
    private byte[] imageData;
    private int idProdus;

    public ImaginiProduse() {
    }
    

    public ImaginiProduse(int id, String numeFile, String numeDir, byte[] imageData, int idProdus) {
        this.id = id;
        this.numeFile = numeFile;
        this.numeDir = numeDir;
        this.imageData = imageData;
        this.idProdus = idProdus;
    }

    public ImaginiProduse(String numeFile, String numeDir, byte[] imageData, int idProdus) {
        this.numeFile = numeFile;
        this.numeDir = numeDir;
        this.imageData = imageData;
        this.idProdus = idProdus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public int getIdProdus() {
        return idProdus;
    }

    public void setIdProdus(int idProdus) {
        this.idProdus = idProdus;
    }

    @Override
    public String toString() {
        return "ImaginiProduse{" + "id=" + id + ", numeFile=" + numeFile + ", numeDir=" + numeDir + ", imageData=" + imageData + ", idProdus=" + idProdus + '}';
    }
    
    
    
}
