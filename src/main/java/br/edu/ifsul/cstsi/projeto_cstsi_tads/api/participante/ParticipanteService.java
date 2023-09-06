package br.edu.ifsul.cstsi.projeto_cstsi_tads.api.participante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParticipanteService {
    @Autowired
    private ParticipanteRepository rep;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<ParticipanteDTO> getParticipantes() {
        return rep
                .findAll()
                .stream()
                .map(ParticipanteDTO::create)
                .collect(Collectors.toList());
    }

    public ParticipanteDTO getParticipanteById(Long id) {
        Optional<Participante> participante = rep.findById(id);
        return participante.map(ParticipanteDTO::create).orElse(null);
    }

    public ParticipanteDTO insert(Participante participante) {
        Assert.isNull(participante.getId(),"Não foi possível cadastrar sua conta");
        participante.setSenha(passwordEncoder.encode(participante.getSenha()));
        return ParticipanteDTO.create(rep.save(participante));
    }

    public ParticipanteDTO update(Participante participante, Long id) {
        Assert.notNull(id,"Não foi possível atualizar seu perfil");
        Optional<Participante> optional = rep.findById(id);
        if(optional.isPresent()) {
            Participante db = optional.get();
            db.setNome(participante.getNome());
            db.setLogin(participante.getLogin());
            db.setSenha(participante.getSenha());
            db.setEmail(participante.getEmail());
            db.setEndereco(participante.getEndereco());
            db.setTelefone(participante.getTelefone());
            rep.save(db);
            return ParticipanteDTO.create(db);
        } else {
            return null;
        }
    }

    public boolean delete(Long id) {
        Optional<Participante> optional = rep.findById(id);
        if(optional.isPresent()) {
            rep.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
}
