package com.mgc.service.paciente;


import com.mgc.dto.PacienteDTO;
import com.mgc.models.Paciente;

public interface PacienteService {
    Paciente registrarPaciente(PacienteDTO pacienteDTO);
    Paciente getPacienteById(Integer id);
    PacienteDTO convertirAPacienteDTO(Paciente paciente);
}
