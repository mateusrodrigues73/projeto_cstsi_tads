package br.edu.ifsul.cstsi.projeto_cstsi_tads.api.leilao;

import lombok.Data;
import org.modelmapper.ModelMapper;

import java.sql.Date;
import java.sql.Time;

@Data
public class LeilaoDTO {
    private Long id;
    private Date dataInicio;
    private Time horaInicio;
    private Date dataFinal;
    private Time horaFinal;

    public static LeilaoDTO create (Leilao l) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(l, LeilaoDTO.class);
    }
}
