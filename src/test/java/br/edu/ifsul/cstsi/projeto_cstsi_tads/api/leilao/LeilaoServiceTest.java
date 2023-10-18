package br.edu.ifsul.cstsi.projeto_cstsi_tads.api.leilao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class LeilaoServiceTest {

    @Autowired
    private LeilaoService service;

    @Test
    void startDatabase() {
    }

    @Test
    void getLeiloes() {
        List<LeilaoDTO> leiloes = service.getLeiloes();
        assertEquals(5, leiloes.size());
    }

    @Test
    void getLeilaoById() throws ParseException {
        Date dataEsperada = Date.valueOf("2023-06-21");
        LeilaoDTO l = service.getLeilaoById(1L);
        assertNotNull(l);
        assertEquals(dataEsperada, l.getDataInicio());
    }

    @Test
    void insert() {
        // Cria objetos Date e Time para as datas e horas desejadas
        Date dataInicio = Date.valueOf("2023-10-21");
        Date dataFinal = Date.valueOf("2023-10-21");
        Time horaInicio = Time.valueOf("12:00:00");
        Time horaFinal = Time.valueOf("16:00:00");

        //cria o leilão para teste
        Leilao leilao = new Leilao();
        leilao.setDataInicio(dataInicio);
        leilao.setDataFinal(dataFinal);
        leilao.setHoraInicio(horaInicio);
        leilao.setHoraFinal(horaFinal);

        //insere o leilão na base da dados
        LeilaoDTO l = service.insert(leilao);

        //se inseriu
        assertNotNull(l);

        //confirma se o leilão foi realmente inserido na base de dados
        Long id = l.getId();
        assertNotNull(id);
        l = service.getLeilaoById(id);
        assertNotNull(l);

        //compara os valores inseridos com os valores pesquisados para confirmar
        assertEquals(dataInicio, l.getDataInicio());
        assertEquals(dataFinal, l.getDataFinal());
        assertEquals(horaInicio, l.getHoraInicio());
        assertEquals(horaFinal, l.getHoraFinal());

        // Deletar o objeto
        service.delete(id);

        //Verificar se deletou
        if(service.getLeilaoById(id) != null){
            fail("O leilão não foi excluído");
        }
    }

    @Test
    void update() {
        Date newDataInicio = Date.valueOf("2023-10-23");
        Date newDataFinal = Date.valueOf("2023-10-24");

        LeilaoDTO lDTO = service.getLeilaoById(1L);
        Date dataInicio = lDTO.getDataInicio();
        lDTO.setDataInicio(newDataInicio);
        Leilao l = Leilao.create(lDTO);
        l.setDataFinal(newDataFinal);

        lDTO = service.update(l, l.getId());
        assertNotNull(lDTO);
        assertEquals(newDataInicio, lDTO.getDataInicio());

        //volta ao valor original
        l.setDataInicio(dataInicio);
        lDTO = service.update(l, l.getId());
        assertNotNull(lDTO);
    }

    @Test
    void delete() {
        // Cria objetos Date e Time para as datas e horas desejadas
        Date dataInicio = Date.valueOf("2023-10-21");
        Date dataFinal = Date.valueOf("2023-10-21");
        Time horaInicio = Time.valueOf("12:00:00");
        Time horaFinal = Time.valueOf("16:00:00");

        //cria o leilão para teste
        Leilao leilao = new Leilao();
        leilao.setDataInicio(dataInicio);
        leilao.setDataFinal(dataFinal);
        leilao.setHoraInicio(horaInicio);
        leilao.setHoraFinal(horaFinal);

        //insere o leilão na base da dados
        LeilaoDTO l = service.insert(leilao);

        assertNotNull(l);

        //confirma se o leilão foi realmente inserido na base de dados
        Long id = l.getId();
        assertNotNull(id);
        l = service.getLeilaoById(id);
        assertNotNull(l);

        // Deletar o objeto
        service.delete(id);
        //Verificar se deletou
        if(service.getLeilaoById(id) != null){
            fail("O produto não foi excluído");
        }
    }
}