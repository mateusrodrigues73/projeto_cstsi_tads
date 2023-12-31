package br.edu.ifsul.cstsi.projeto_cstsi_tads.api.leilao;

import br.edu.ifsul.cstsi.projeto_cstsi_tads.api.item.Item;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Entity
@Table(name = "leiloes", schema = "projeto_tads_leiloes", catalog = "")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Leilao {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    private LocalDate dataInicio;
    private LocalTime horaInicio;
    private LocalDate dataFinal;
    private LocalTime horaFinal;
    @OneToMany(mappedBy = "leilao")
    private Collection<Item> itens;

    public static Leilao create(LeilaoDTO l){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(l, Leilao.class);
    }
}
