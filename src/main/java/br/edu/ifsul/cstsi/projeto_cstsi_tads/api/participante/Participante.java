package br.edu.ifsul.cstsi.projeto_cstsi_tads.api.participante;

import br.edu.ifsul.cstsi.projeto_cstsi_tads.api.lance.Lance;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
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
    @NotBlank(message= "Campo Nome não pode ser vazio!")
    @Size(min = 2, max = 50, message = "Nome deve ter entre 2 e 50 caracteres!")
    private String nome;
    @NotBlank(message= "Campo Login não pode ser vazio!")
    @Size(min = 2, max = 30, message = "Login deve ter entre 2 e 30 caracteres!")
    private String login;
    @NotBlank(message = "Campo Senha não pode ser vazio!")
    private String senha;
    @Email(message = "Email possui formato inválido!")
    @NotBlank(message = "Campo Email não pode ser vazio!")
    private String email;
    @NotBlank(message = "Campo Endereço não pode ser vazio!")
    @Size(max = 100, message = "Endereço deve ter no máximo 100 caracteres!")
    private String endereco;
    @NotBlank(message = "Campo Telefone não pode ser vazio!")
    @Size(min = 8, max = 15, message = "Telefone deve ter entre 8 e 15 caracteres!")
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
