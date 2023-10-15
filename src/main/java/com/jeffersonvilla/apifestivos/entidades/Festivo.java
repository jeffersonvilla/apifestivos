package com.jeffersonvilla.apifestivos.entidades;

import java.util.Calendar;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "Festivo")
public class Festivo {
    
    @Id
    @Column(name ="Id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "secuencia festivo")
    @GenericGenerator(name = "secuencia festivo", strategy = "increment")
    private long id;

    @Column(name = "Nombre", length = 100, unique = true)
    private String nombre;

    @Column(name = "Dia")
    private int dia;

    @Column(name = "Mes")
    private int mes;

    @Transient
    private int anio;

    @Column(name = "diaspascua")
    private int diasPascua;

    @ManyToOne
    @JoinColumn(name = "idtipo", referencedColumnName = "Id")
    private Tipo tipoFestivo;

    public Festivo() {
    }

    public Festivo(long id, String nombre, int dia, int mes, int diasPascua, Tipo tipoFestivo) {
        this.id = id;
        this.nombre = nombre;
        this.dia = dia;
        this.mes = mes;
        this.diasPascua = diasPascua;
        this.tipoFestivo = tipoFestivo;
    }

    public Festivo(long id, String nombre, int dia, int mes, int anio, int diasPascua, Tipo tipoFestivo) {
        this.id = id;
        this.nombre = nombre;
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
        this.diasPascua = diasPascua;
        this.tipoFestivo = tipoFestivo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getDiasPascua() {
        return diasPascua;
    }

    public void setDiasPascua(int diasPascua) {
        this.diasPascua = diasPascua;
    }

    public Tipo getTipoFestivo() {
        return tipoFestivo;
    }

    public void setTipoFestivo(Tipo tipoFestivo) {
        this.tipoFestivo = tipoFestivo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public void setFecha(Calendar fecha){
        setAnio(fecha.get(Calendar.YEAR));
        setMes(fecha.get(Calendar.MONTH) + 1);
        setDia(fecha.get(Calendar.DAY_OF_MONTH));
    }

    public Calendar getFecha(){
        Calendar fecha = Calendar.getInstance();
        fecha.set(getAnio(), getMes() - 1, getDia());
        return fecha;
    }

    public String toString(){
        return this.nombre + " -- " + getFecha().getTime().toString();
    }
}
