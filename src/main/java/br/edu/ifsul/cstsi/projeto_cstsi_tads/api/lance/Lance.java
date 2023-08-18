package br.edu.ifsul.cstsi.projeto_cstsi_tads.api.lance;

import br.edu.ifsul.cstsi.projeto_cstsi_tads.api.participante.Participante;
import br.edu.ifsul.cstsi.projeto_cstsi_tads.api.item.Item;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lances", schema = "projeto_tads_leiloes", catalog = "")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Lance {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    private BigDecimal valor;
    private Time hora;
    @ManyToOne
    @JoinColumn(name = "id_participante", referencedColumnName = "id", nullable = false)
    private Participante participante;
    @ManyToOne
    @JoinColumn(name = "id_item", referencedColumnName = "id", nullable = false)
    private Item item;
}
