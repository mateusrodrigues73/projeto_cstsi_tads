package br.edu.ifsul.cstsi.projeto_cstsi_tads.api.participante;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParticipanteDTO {
    private Long id;
    private String nome;
    private String login;
    private String senha;
    private String email;
    private String endereco;
    private String telefone;
    private String token;

    private List<String> roles;

    public static ParticipanteDTO create (Participante p) {
        ModelMapper modelMapper = new ModelMapper();
        ParticipanteDTO dto = modelMapper.map(p, ParticipanteDTO.class);
        dto.roles = p.getRoles().stream()
                .map(Role::getNome)
                .collect(Collectors.toList());
        return dto;
    }

    public static ParticipanteDTO create(Participante p, String token) {
        ParticipanteDTO dto = create(p);
        dto.token = token;
        return dto;
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper m = new ObjectMapper();
        return m.writeValueAsString(this);
    }
}
