package com.example.demo.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonaTest {
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
}
