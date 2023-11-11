package com.jeffersonvilla.apifestivos.interfaces;

import java.util.Calendar;
import java.util.List;

import com.jeffersonvilla.apifestivos.DTO.FestivoDTO;

public interface IFestivoServicio {
    
    public boolean esFestivo(Calendar fecha);

    public boolean esFechaValida(String fecha);

    public List<FestivoDTO> obtenerFestivos(int año);
}
