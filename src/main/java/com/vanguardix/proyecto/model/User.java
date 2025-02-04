package com.vanguardix.proyecto.model;
public class User {

    private int id;
    private String name;
    private  String email;

    public User(){

    }

    public User (int id, String name, String email){
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return name; }
    public void setNombre(String nombre) { this.name = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
