package br.edu.ifsul.cstsi.projeto_cstsi_tads.api.infra.security;

import br.edu.ifsul.cstsi.projeto_cstsi_tads.api.participante.ParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private ParticipanteRepository rep;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return rep.findByLogin(username);
    }
}






















