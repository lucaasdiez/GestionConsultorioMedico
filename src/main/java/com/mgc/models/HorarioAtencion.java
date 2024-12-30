package com.mgc.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class HorarioAtencion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String diasSemana;
    private LocalTime horarioInicio;
    private LocalTime horarioFin;
    @ManyToOne
    @JoinColumn(name = "profesional_id")
    private Profesional profesional;
}
