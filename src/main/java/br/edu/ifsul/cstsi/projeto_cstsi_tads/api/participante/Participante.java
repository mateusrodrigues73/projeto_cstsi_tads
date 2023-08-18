package br.edu.ifsul.cstsi.projeto_cstsi_tads.api.participante;

import br.edu.ifsul.cstsi.projeto_cstsi_tads.api.lance.Lance;
import javax.persistence.*;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "participantes", schema = "projeto_tads_leiloes", catalog = "")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Participante {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    private String nome;
    private String login;
    private String senha;
    private String email;
    private String endereco;
    private String telefone;
    @OneToMany(mappedBy = "participante")
    private Collection<Lance> lances;
}
