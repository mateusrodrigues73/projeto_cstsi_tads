package br.edu.ifsul.cstsi.projeto_cstsi_tads.api.participante;

import br.edu.ifsul.cstsi.projeto_cstsi_tads.api.leilao.Leilao;
import br.edu.ifsul.cstsi.projeto_cstsi_tads.api.leilao.LeilaoDTO;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class ParticipanteDTO {
    private Long id;
    private String nome;
    private String login;
    private String senha;
    private String email;
    private String endereco;
    private String telefone;

    public static ParticipanteDTO create (Participante p) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(p, ParticipanteDTO.class);
    }
}
