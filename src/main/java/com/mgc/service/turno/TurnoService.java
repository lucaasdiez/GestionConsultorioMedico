package com.mgc.service.turno;



import com.mgc.dto.TurnoDTO;
import com.mgc.models.Turno;

import java.util.List;

public interface TurnoService {
    Turno registrarTurno(TurnoDTO turnoDTO);
    List<Turno> getTurnosPaciente(Integer paciente);
    List<Turno> getTurnosProfesionalYEspecialidad(String profesional, String especialidad);
    Turno modificarTurno(TurnoDTO turnoDTO);
    void cancelarTurno(Integer idTurno);
    List<TurnoDTO> convertirAturnoDTO(List<Turno> turno);
    TurnoDTO convertirAturnoDTO(Turno turno);
}
