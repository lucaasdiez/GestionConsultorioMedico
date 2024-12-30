package com.mgc.service.paciente;


import com.mgc.dto.PacienteDTO;
import com.mgc.models.Paciente;

public interface PacienteService {
    Paciente registrarPaciente(PacienteDTO pacienteDTO);
    Paciente getPacienteById(Integer id);
    Paciente getPacienteByNombre(String nombre);
    PacienteDTO convertirAPacienteDTO(Paciente paciente);
}
