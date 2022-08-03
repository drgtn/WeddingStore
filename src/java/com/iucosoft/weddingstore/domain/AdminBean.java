package com.iucosoft.weddingstore.domain;

public class AdminBean {
    private int id;
    private String email="";
    private String password="";
    private String firstName;
    private String lastName;
    private String repeatPassword;

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
    public boolean valid;

    public AdminBean() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
     public void removePassword() {
        password = null;
   }
    public void removeEmail() {
        email = null;
         }
    public void removeLastName() {
        lastName = null;
         }
    public void removeFirstName() {
        firstName = null;
         }
    
      public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
