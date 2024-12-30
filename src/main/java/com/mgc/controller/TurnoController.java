package com.mgc.controller;

import com.mgc.dto.TurnoDTO;
import com.mgc.exceptions.ResourceNotFoundException;
import com.mgc.exceptions.TurnoNoValidoException;
import com.mgc.models.Turno;
import com.mgc.response.ApiResponse;
import com.mgc.service.turno.TurnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api/turnos")
@RequiredArgsConstructor
public class TurnoController {
    private final TurnoService turnoService;

    @GetMapping("/paciente")
    public ResponseEntity<ApiResponse> getTurnosPaciente(@RequestParam String nombrePaciente){
        try{
            List<Turno> turnos = turnoService.getTurnosPaciente(nombrePaciente);
            List<TurnoDTO> turnoDTOS= turnoService.convertirAturnoDTO(turnos);
            return ResponseEntity.ok().body(new ApiResponse("Turnos", turnoDTOS));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/especialidadYprofesional")
    public ResponseEntity<ApiResponse> getTurnosProfesionalYEspecialidad(
            @RequestParam(required = false) String profesional,
            @RequestParam(required = false) String especialidad){
        try{
            List<Turno> turnos = turnoService.getTurnosProfesionalYEspecialidad(profesional, especialidad);
            List<TurnoDTO> turnoDTOS= turnoService.convertirAturnoDTO(turnos);
            return ResponseEntity.ok().body(new ApiResponse("Turnos", turnoDTOS));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }

    }

    @PostMapping("/turno/registrar")
    public ResponseEntity<ApiResponse> registrarTurno(@RequestBody TurnoDTO turnoDTO){
        try{
            Turno turno = turnoService.registrarTurno(turnoDTO);
            TurnoDTO turnoDTO1 = turnoService.convertirAturnoDTO(turno);
            return ResponseEntity.ok().body(new ApiResponse("Turno Registrado", turnoDTO1));
        }catch (TurnoNoValidoException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }


    @PutMapping("/turno/modificar")
    public ResponseEntity<ApiResponse> modificarTurno(@RequestBody TurnoDTO turnoDTO){
        try{
            Turno turno = turnoService.modificarTurno(turnoDTO);
            TurnoDTO turnoDTO1 = turnoService.convertirAturnoDTO(turno);
            return ResponseEntity.ok().body(new ApiResponse("Turno Modificado", turnoDTO1));
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/turno/{idTurno}/cancelar")
    public ResponseEntity<ApiResponse> cancelarTurno(@PathVariable Integer idTurno){
        try{
            turnoService.cancelarTurno(idTurno);
            return ResponseEntity.ok().body(new ApiResponse("Turno Cancelado", null));
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
