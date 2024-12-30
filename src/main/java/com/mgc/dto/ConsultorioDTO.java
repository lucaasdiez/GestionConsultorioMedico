package com.mgc.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConsultorioDTO {
    private Integer id;
    private int nombre;
    @JsonIgnore
    private List<TurnoDTO> turnos;
}
