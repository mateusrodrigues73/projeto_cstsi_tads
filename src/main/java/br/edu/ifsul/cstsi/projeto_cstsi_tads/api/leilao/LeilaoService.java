package br.edu.ifsul.cstsi.projeto_cstsi_tads.api.leilao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class LeilaoService {
    @Autowired
    private LeilaoRepository rep;

    public List<LeilaoDTO> getLeiloes() {
        return rep
            .findAll()
            .stream()
            .map(LeilaoDTO::create)
            .collect(Collectors.toList());
    }

    public LeilaoDTO getLeilaoById(Long id) {
        Optional<Leilao> leilao = rep.findById(id);
        return leilao.map(LeilaoDTO::create).orElse(null);
    }

    public LeilaoDTO insert(Leilao leilao) {
        Assert.isNull(leilao.getId(),"Não foi possível cadastrar o leilão");
        return LeilaoDTO.create(rep.save(leilao));
    }

    public LeilaoDTO update(Leilao leilao, Long id) {
        Assert.notNull(id,"Não foi possível atualizar o leilão");
        Optional<Leilao> optional = rep.findById(id);
        if(optional.isPresent()) {
            Leilao db = optional.get();
            db.setDataInicio(leilao.getDataInicio());
            db.setDataFinal(leilao.getDataFinal());
            db.setHoraInicio(leilao.getHoraInicio());
            db.setHoraFinal(leilao.getHoraFinal());
            rep.save(db);
            return LeilaoDTO.create(db);
        } else {
            return null;
        }
    }

    public boolean delete(Long id) {
        Optional<Leilao> optional = rep.findById(id);
        if(optional.isPresent()) {
            rep.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
}
