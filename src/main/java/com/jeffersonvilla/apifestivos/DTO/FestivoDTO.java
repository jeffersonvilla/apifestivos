package com.jeffersonvilla.apifestivos.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class FestivoDTO {

    private String nombre;

    private LocalDateTime fecha;

    public FestivoDTO(String nombre, LocalDate fecha) {
        this.nombre = nombre;
        this.fecha = fecha.atStartOfDay();
    }

    public FestivoDTO(String nombre, LocalDateTime fecha) {
        this.nombre = nombre;
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    
}
