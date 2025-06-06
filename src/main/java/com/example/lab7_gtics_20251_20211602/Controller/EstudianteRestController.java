package com.example.lab7_gtics_20251_20211602.Controller;


import com.example.lab7_gtics_20251_20211602.Entity.Estudiante;
import com.example.lab7_gtics_20251_20211602.Repository.EstudianteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteRestController {

    @Autowired
    private EstudianteRepository estudianteRepo;

    @GetMapping
    public List<Map<String, Object>> listarTodos() {
        List<Map<String, Object>> respuesta = new ArrayList<>();
        for (Estudiante e : estudianteRepo.findAll()) {
            Map<String, Object> m = new LinkedHashMap<>();
            m.put("id", e.getId());
            m.put("nombres", e.getNombres());
            m.put("apellidos", e.getApellidos());
            m.put("dni", e.getDni());
            m.put("codigoPucp", e.getCodigoPucp());
            m.put("correoInstitucional", e.getCorreoInstitucional());
            m.put("correoPersonal", e.getCorreoPersonal());
            m.put("telefono", e.getTelefono());
            m.put("direccion", e.getDireccion());
            m.put("departamento", e.getDepartamento());
            m.put("provincia", e.getProvincia());
            m.put("carrera", e.getCarrera());
            m.put("estado", Boolean.TRUE.equals(e.getEstado()) ? "Activo" : "Inactivo");
            respuesta.add(m);
        }
        return respuesta;
    }

    @GetMapping("/{dni}")
    public ResponseEntity<?> buscarPorDni(@PathVariable String dni) {
        return estudianteRepo.findByDni(dni)
                .map(e -> {
                    Map<String, Object> m = new LinkedHashMap<>();
                    m.put("id", e.getId());
                    m.put("nombres", e.getNombres());
                    m.put("apellidos", e.getApellidos());
                    m.put("dni", e.getDni());
                    m.put("codigoPucp", e.getCodigoPucp());
                    m.put("correoInstitucional", e.getCorreoInstitucional());
                    m.put("correoPersonal", e.getCorreoPersonal());
                    m.put("telefono", e.getTelefono());
                    m.put("direccion", e.getDireccion());
                    m.put("departamento", e.getDepartamento());
                    m.put("provincia", e.getProvincia());
                    m.put("carrera", e.getCarrera());
                    m.put("estado", Boolean.TRUE.equals(e.getEstado()) ? "Activo" : "Inactivo");
                    return ResponseEntity.ok(m);
                })
                .orElseGet(() -> {
                    Map<String, Object> error = new HashMap<>();
                    error.put("mensaje", "Estudiante no encontrado");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
                });
    }

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Estudiante estudiante) {
        if (estudianteRepo.existsByDni(estudiante.getDni())) {
            return ResponseEntity.badRequest().body("DNI ya registrado");
        }
        if (estudianteRepo.existsByCodigoPucp(estudiante.getCodigoPucp())) {
            return ResponseEntity.badRequest().body("Código PUCP ya registrado");
        }
        if (Period.between(estudiante.getFechaNacimiento(), LocalDate.now()).getYears() < 17) {
            return ResponseEntity.badRequest().body("El estudiante debe tener más de 16 años");
        }
        if (estudiante.getCorreoInstitucional().equals(estudiante.getCorreoPersonal())) {
            return ResponseEntity.badRequest().body("El correo personal no debe ser igual al institucional");
        }

        estudiante.setFechaRegistro(LocalDateTime.now());
        estudiante.setUltimaActualizacion(null);
        estudiante.setEstado(true);
        estudianteRepo.save(estudiante);
        return ResponseEntity.status(HttpStatus.CREATED).body("Estudiante registrado correctamente");
    }
}
