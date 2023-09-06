package br.edu.ifsul.cstsi.projeto_cstsi_tads.api.participante;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipanteRepository extends JpaRepository<Participante, Long>{
    Participante findByLogin(String login);
}
