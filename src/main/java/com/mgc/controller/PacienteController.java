package com.mgc.controller;

import com.mgc.dto.PacienteDTO;
import com.mgc.exceptions.AlreadyExistExeption;
import com.mgc.exceptions.ResourceNotFoundException;
import com.mgc.models.Paciente;
import com.mgc.response.ApiResponse;
import com.mgc.service.paciente.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api/pacientes")
@RequiredArgsConstructor
public class PacienteController {
    private final PacienteService pacienteService;

    @GetMapping("/paciente/{idPaciente}")
    public ResponseEntity<ApiResponse> getPacienteById(@PathVariable Integer idPaciente){
        try {
            Paciente paciente = pacienteService.getPacienteById(idPaciente);
            PacienteDTO pacienteDTO = pacienteService.convertirAPacienteDTO(paciente);
            return ResponseEntity.ok(new ApiResponse("Paciente", pacienteDTO));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/paciente/registrar")
    public ResponseEntity<ApiResponse> registrarPaciente(@RequestBody PacienteDTO pacienteDTO){
        try {
            Paciente paciente = pacienteService.registrarPaciente(pacienteDTO);
            PacienteDTO pacienteDTO1 = pacienteService.convertirAPacienteDTO(paciente);
            return ResponseEntity.ok(new ApiResponse("Paciente Registrado", pacienteDTO1));
        }catch (AlreadyExistExeption e){
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));

        }
    }
}
