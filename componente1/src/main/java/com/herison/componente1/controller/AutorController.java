package com.herison.componente1.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "API de Livros e Autores", description = "Endpoints para gerenciar livros e autores")
public class AutorController {


}
