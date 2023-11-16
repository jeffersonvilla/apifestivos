package com.jeffersonvilla.apifestivos.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeffersonvilla.apifestivos.entidades.Tipo;

@Repository
public interface TipoRepositorio extends JpaRepository<Tipo, Integer>{
    
}
