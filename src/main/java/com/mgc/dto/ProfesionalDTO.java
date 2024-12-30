package com.mgc.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProfesionalDTO {

    private Integer id;
    private String nombre;
    private String apellido;
    private Integer matricula;
    @JsonIgnore
    private List<TurnoDTO> turnos;
    private EspecialidadDTO especialidad;
    private List<HorarioAtencionDTO> horariosAtencion;


}
