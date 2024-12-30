package com.mgc.dto;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mgc.enums.EstadoTurno;
import com.mgc.models.Consultorio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TurnoDTO {
    private Integer id;
    private LocalDate fecha;
    private LocalTime hora;
    private EstadoTurno estadoTurno;
/*
    @JsonManagedReference
*/
    private ProfesionalDTO profesional;
    /*@JsonManagedReference*/
    private PacienteDTO paciente;
   /* @JsonManagedReference*/
    private ConsultorioDTO consultorio;

}
