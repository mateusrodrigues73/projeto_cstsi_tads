package br.edu.ifsul.cstsi.projeto_cstsi_tads.api.leilao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
        LocalDate dataEsperada = LocalDate.parse("2023-06-21");
        LeilaoDTO l = service.getLeilaoById(1L);
        assertNotNull(l);
        assertEquals(dataEsperada, l.getDataInicio());
    }

    @Test
    void insert() {
        LocalDate dataInicio = LocalDate.parse("21/06/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate dataFinal = LocalDate.parse("21/06/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalTime horaInicio = LocalTime.parse("12:00");
        LocalTime horaFinal = LocalTime.parse("16:00");

        //cria o leilao para teste
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
        LocalDate newDataInicio = LocalDate.parse("21/06/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate newDataFinal = LocalDate.parse("21/06/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        LeilaoDTO lDTO = service.getLeilaoById(1L);
        LocalDate dataInicio = lDTO.getDataInicio();
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
        LocalDate dataInicio = LocalDate.parse("21/06/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate dataFinal = LocalDate.parse("21/06/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalTime horaInicio = LocalTime.parse("12:00");
        LocalTime horaFinal = LocalTime.parse("16:00");

        //cria o leilao para teste
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