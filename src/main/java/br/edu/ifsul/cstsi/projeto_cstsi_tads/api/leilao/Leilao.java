package br.edu.ifsul.cstsi.projeto_cstsi_tads.api.leilao;

import br.edu.ifsul.cstsi.projeto_cstsi_tads.api.item.Item;
import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Date dataInicio;
    private Time horaInicio;
    private Date dataFinal;
    private Time horaFinal;
    @OneToMany(mappedBy = "leilao")
    private Collection<Item> itens;
}
