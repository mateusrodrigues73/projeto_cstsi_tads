package br.edu.ifsul.cstsi.projeto_cstsi_tads.api.item;

import br.edu.ifsul.cstsi.projeto_cstsi_tads.api.lance.Lance;
import br.edu.ifsul.cstsi.projeto_cstsi_tads.api.leilao.Leilao;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "itens", schema = "projeto_tads_leiloes", catalog = "")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Item {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal valorMinimo;
    private String foto;
    private int arrematado;
    @ManyToOne
    @JoinColumn(name = "id_leilao", referencedColumnName = "id", nullable = false)
    private Leilao leilao;
    @OneToMany(mappedBy = "item")
    private Collection<Lance> lances;
}
