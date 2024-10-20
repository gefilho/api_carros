package carro.com.api.DTO;

import carro.com.api.Enums.UsuarioPermissao;

public record RegisterDTO(String nome, String email, String senha, UsuarioPermissao permissao) {}
