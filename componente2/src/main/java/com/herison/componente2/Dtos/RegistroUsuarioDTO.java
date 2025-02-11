package com.herison.componente2.Dtos;

import com.herison.componente2.enuns.UserRole;

public record RegistroUsuarioDTO(String login, String password, UserRole role) {
}
