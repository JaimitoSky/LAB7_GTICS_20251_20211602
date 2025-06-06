package com.example.lab7_gtics_20251_20211602.Repository;


import com.example.lab7_gtics_20251_20211602.Entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {
    Optional<Estudiante> findByDni(String dni);
    boolean existsByDni(String dni);
    boolean existsByCodigoPucp(String codigoPucp);
}
