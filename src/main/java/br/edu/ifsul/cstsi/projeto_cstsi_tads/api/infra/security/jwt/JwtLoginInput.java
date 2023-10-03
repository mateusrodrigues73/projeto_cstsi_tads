package br.edu.ifsul.cstsi.projeto_cstsi_tads.api.infra.security.jwt;

import lombok.Data;

@Data
public class JwtLoginInput {
    private String username;
    private String password;
}