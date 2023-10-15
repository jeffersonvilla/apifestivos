package com.jeffersonvilla.apifestivos.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeffersonvilla.apifestivos.entidades.Festivo;

@Repository
public interface FestivoRepositorio extends JpaRepository<Festivo, Long>{
    
}
