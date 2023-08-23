package br.edu.ifsul.cstsi.projeto_cstsi_tads.api.leilao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface LeilaoRepository extends JpaRepository<Leilao,Long> {

}
