package com.jeffersonvilla.apifestivos.servicios;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeffersonvilla.apifestivos.DTO.FestivoDTO;
import com.jeffersonvilla.apifestivos.entidades.Festivo;
import com.jeffersonvilla.apifestivos.interfaces.IFestivoServicio;
import com.jeffersonvilla.apifestivos.repositorios.FestivoRepositorio;

@Service
public class FestivoServicio implements IFestivoServicio {

    @Autowired
    private FestivoRepositorio repositorio;

    @Override
    public boolean esFechaValida(String strFecha) {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            df.setLenient(false);
            df.parse(strFecha);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    @Override
    public boolean esFestivo(Calendar fecha) {
        List<Festivo> festivos = repositorio.findAll();
        return esFestivo(festivos, fecha);
    }

    private boolean esFestivo(List<Festivo> festivos, Calendar fecha) {
        if (festivos != null) {
            
            for (final Festivo festivo : calcularFestivos(festivos, fecha.get(Calendar.YEAR))) {
                if (fechasIguales(festivo.getFecha(), fecha))
                    return true;
            }
        }
        return false;
    }

    private List<Festivo> calcularFestivos(List<Festivo> festivos, int año) {

        if (festivos != null) {
            Calendar pascua = obtenerDomingoPascua(año);

            for (Festivo festivo : festivos) {
                festivo.setAnio(año);

                Calendar fechaBasadaEnPascua = Calendar.getInstance();
                fechaBasadaEnPascua.setTime(pascua.getTime());
                fechaBasadaEnPascua.add(Calendar.DATE, festivo.getDiasPascua());

                switch (festivo.getTipoFestivo().getId()) {
                    case 2:
                        festivo.setFecha(siguienteLunes(festivo.getFecha()));
                        break;
                    case 3:
                        festivo.setFecha(fechaBasadaEnPascua);
                        break;
                    case 4:
                        festivo.setFecha(siguienteLunes(fechaBasadaEnPascua));
                        break;
                }
            }
            return festivos;
        }
        return null;
    }

    private boolean fechasIguales(Calendar fecha1, Calendar fecha2) {
        return fecha1.get(Calendar.YEAR) == fecha2.get(Calendar.YEAR) 
                && fecha1.get(Calendar.MONTH) == fecha2.get(Calendar.MONTH)
                && fecha1.get(Calendar.DAY_OF_MONTH) == fecha2.get(Calendar.DAY_OF_MONTH);
    }

    private Calendar obtenerDomingoPascua(int año) {
        int mes, dia, A, B, C, D, E, M, N;
        M = 0;
        N = 0;
        if (año >= 1583 && año <= 1699) {
            M = 22;
            N = 2;
        } else if (año >= 1700 && año <= 1799) {
            M = 23;
            N = 3;
        } else if (año >= 1800 && año <= 1899) {
            M = 23;
            N = 4;
        } else if (año >= 1900 && año <= 2099) {
            M = 24;
            N = 5;
        } else if (año >= 2100 && año <= 2199) {
            M = 24;
            N = 6;
        } else if (año >= 2200 && año <= 2299) {
            M = 25;
            N = 0;
        }

        A = año % 19;
        B = año % 4;
        C = año % 7;
        D = ((19 * A) + M) % 30;
        E = ((2 * B) + (4 * C) + (6 * D) + N) % 7;

        // Decidir entre los 2 casos
        if (D + E < 10) {
            dia = D + E + 22;
            mes = 3; // Marzo
        } else {
            dia = D + E - 9;
            mes = 4; // Abril
        }

        // Excepciones especiales
        if (dia == 26 && mes == 4)
            dia = 19;
        if (dia == 25 && mes == 4 && D == 28 && E == 6 && A > 10)
            dia = 18;

        Calendar fecha = Calendar.getInstance();
        fecha.set(año, mes - 1, dia);
        return fecha;
    }

    private Calendar siguienteLunes(Calendar fecha) {
        if (fecha.get(Calendar.DAY_OF_WEEK) > Calendar.MONDAY)
            fecha.add(Calendar.DATE, 9 - fecha.get(Calendar.DAY_OF_WEEK));
        else if (fecha.get(Calendar.DAY_OF_WEEK) < Calendar.MONDAY)
            fecha.add(Calendar.DATE, 1);
        return fecha;
    }
    
    @Override
    public List<FestivoDTO> obtenerFestivos(int año) {
        List<Festivo> festivos = repositorio.findAll();
        festivos = calcularFestivos(festivos, año);
        List<FestivoDTO> fechas = new ArrayList<FestivoDTO>();
        for (final Festivo festivo : festivos) {
            fechas.add(new FestivoDTO(festivo.getNombre(), LocalDate.of(festivo.getAnio(), festivo.getMes(), festivo.getDia())));
        }
        return fechas;
    }

}
