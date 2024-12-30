package com.mgc.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteDTO {
    private Integer id;
    private String nombre;
    private String apellido;
    private long documento;
    private LocalDate fecha_nacimiento;
    private String email;
    private long  telefono;
    @JsonIgnore
    private List<TurnoDTO> turnos;
}
