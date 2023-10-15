package com.jeffersonvilla.apifestivos.interfaces;

import java.util.Calendar;

public interface IFestivoServicio {
    
    public boolean esFestivo(Calendar fecha);

    public boolean esFechaValida(String fecha);
}
