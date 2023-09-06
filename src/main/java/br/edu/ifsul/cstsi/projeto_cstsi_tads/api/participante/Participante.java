package br.edu.ifsul.cstsi.projeto_cstsi_tads.api.participante;

import br.edu.ifsul.cstsi.projeto_cstsi_tads.api.lance.Lance;
import javax.persistence.*;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "participantes", schema = "projeto_tads_leiloes", catalog = "")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Participante implements UserDetails {
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
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "participantes_roles",
            joinColumns = @JoinColumn(name = "participante_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
