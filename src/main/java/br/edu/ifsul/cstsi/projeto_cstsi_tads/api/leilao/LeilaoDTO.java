package br.edu.ifsul.cstsi.projeto_cstsi_tads.api.leilao;

import lombok.Data;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class LeilaoDTO {
    private Long id;
    private LocalDate dataInicio;
    private LocalTime horaInicio;
    private LocalDate dataFinal;
    private LocalTime horaFinal;

    public static LeilaoDTO create (Leilao l) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(l, LeilaoDTO.class);
    }
}
