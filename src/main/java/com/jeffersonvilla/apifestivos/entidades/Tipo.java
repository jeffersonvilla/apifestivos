package com.jeffersonvilla.apifestivos.entidades;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Tipo")
public class Tipo {
    
    @Id
    @Column(name = "Id", columnDefinition = "")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "secuencia tipo")
    @GenericGenerator(name = "secuencia tipo", strategy = "increment")
    private int id;

    @Column(name = "Tipo", length = 100, unique = true)
    private String nombre;

    public Tipo() {
    }

    public Tipo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    

}
