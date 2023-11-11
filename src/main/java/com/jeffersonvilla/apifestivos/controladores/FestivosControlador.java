package com.jeffersonvilla.apifestivos.controladores;

import java.util.Calendar;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeffersonvilla.apifestivos.DTO.FestivoDTO;
import com.jeffersonvilla.apifestivos.interfaces.IFestivoServicio;

@RestController
@RequestMapping("/festivos")
public class FestivosControlador {
    
    private IFestivoServicio servicio;

    public FestivosControlador(IFestivoServicio servicio){
        this.servicio = servicio;
    }

    @GetMapping("/verificar/{anio}/{mes}/{dia}")
    public String verficarFestivo(@PathVariable int anio, @PathVariable int mes, @PathVariable int dia){
        String fechaStr = Integer.valueOf(anio) + "-" + Integer.valueOf(mes) + "-" +Integer.valueOf(dia);
        if(!servicio.esFechaValida(fechaStr)){
            return "Fecha No valida";
        }else {
            Calendar fecha = Calendar.getInstance();
            fecha.set(anio, mes - 1, dia);
            if(servicio.esFestivo(fecha)) return "Es Festivo";
            else return "No es festivo";
        }
    }

    @GetMapping("/listar/{año}")
    public List<FestivoDTO> listarFestivos(@PathVariable int año){
        return servicio.obtenerFestivos(año);
    }
}
