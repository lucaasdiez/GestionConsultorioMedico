package com.mgc.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HorarioAtencionDTO {
    private Integer id;
    private String diasSemana;
    private LocalTime horarioInicio;
    private LocalTime horarioFin;
    @JsonIgnore
    private ProfesionalDTO profesional;
}
