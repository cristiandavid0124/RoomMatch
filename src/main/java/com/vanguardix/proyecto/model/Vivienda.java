package com.vanguardix.proyecto.model;

public class Vivienda {

    private int id;
    private String name;
    private String direccion;
    private User user;
    private boolean disponible;

    public Vivienda(int id, String name, String direccion, User user, boolean disponible) {
        this.id = id;
        this.name = name;
        this.direccion = direccion;
        this.user = user;
        this.disponible = disponible;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "Vivienda{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", direccion='" + direccion + '\'' +
                ", user=" + user +
                ", disponible=" + disponible +
                '}';
    }
}
