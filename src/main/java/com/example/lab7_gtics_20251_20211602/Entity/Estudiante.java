package com.example.lab7_gtics_20251_20211602.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$")
    private String nombres;

    @NotBlank @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$")
    private String apellidos;

    @NotBlank @Size(min = 8, max = 8) @Pattern(regexp = "\\d{8}")
    @Column(unique = true)
    private String dni;

    @NotBlank @Size(min = 8, max = 8) @Pattern(regexp = "\\d{8}")
    @Column(name = "codigo_pucp", unique = true)
    private String codigoPucp;

    @NotNull @Past
    private LocalDate fechaNacimiento;

    @NotBlank @Pattern(regexp = "^[MF]$")
    private String sexo;

    @Email @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@pucp\\.edu\\.pe$")
    @Column(name = "correo_institucional")
    private String correoInstitucional;

    @Email
    @Column(name = "correo_personal")
    private String correoPersonal;

    @Pattern(regexp = "^9\\d{8}$")
    private String telefono;

    @Size(max = 100)
    private String direccion;

    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$")
    private String departamento;

    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$")
    private String provincia;

    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$")
    private String carrera;

    private LocalDateTime fechaRegistro;
    private LocalDateTime ultimaActualizacion;
    private Boolean estado;
}
