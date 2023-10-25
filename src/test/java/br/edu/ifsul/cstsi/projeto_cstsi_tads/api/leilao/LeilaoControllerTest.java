package br.edu.ifsul.cstsi.projeto_cstsi_tads.api.leilao;

import br.edu.ifsul.cstsi.projeto_cstsi_tads.ProjetoCstsiTadsApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjetoCstsiTadsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LeilaoControllerTest extends BaseAPITest{

    //Métodos utilitários
    private ResponseEntity<LeilaoDTO> getLeilao(String url) {
        return get(url, LeilaoDTO.class);
    }

    private ResponseEntity<List<LeilaoDTO>> getLeiloes(String url) {
        HttpHeaders headers = getHeaders();
        return rest.exchange(
            url,
            HttpMethod.GET,
            new HttpEntity<>(headers),
            new ParameterizedTypeReference<List<LeilaoDTO>>() {}
        );
    }

    // Testes
    @Test
    public void selectAll() {
        List<LeilaoDTO> leiloes = getLeiloes("/api/v1/leiloes").getBody();
        assertNotNull(leiloes);
        assertEquals(5, leiloes.size());
        leiloes = getLeiloes("/api/v1/leiloes").getBody();
        assertNotNull(leiloes);
        assertEquals(5, leiloes.size());
    }

    @Test
    public void selectById() {
        assertNotNull(getLeilao("/api/v1/leiloes/1"));
        assertNotNull(getLeilao("/api/v1/leiloes/2"));
        assertNotNull(getLeilao("/api/v1/leiloes/3"));
        assertNotNull(getLeilao("/api/v1/leiloes/4"));
        assertNotNull(getLeilao("/api/v1/leiloes/5"));
        assertEquals(HttpStatus.NOT_FOUND, getLeilao("/api/v1/leiloes/1000").getStatusCode());
    }

    @Test
    public void testInsert() {
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

        // Insert
        ResponseEntity<Object> response = post("/api/v1/leiloes", leilao, null);
        System.out.println("response: ");
        System.out.println(response);

        // Verifica se criou
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        // Busca o objeto
        String location = Objects.requireNonNull(response.getHeaders().get("location")).get(0);
        LeilaoDTO l = getLeilao(location).getBody();

        assertNotNull(l);
        assertEquals(dataInicio, l.getDataInicio());

        // Deleta o objeto
        delete(location, null);

        // Verificar se deletou
        assertEquals(HttpStatus.NOT_FOUND, getLeilao(location).getStatusCode());
    }

    @Test
    public void testUpdate() {
        // Cria objetos Date e Time para as datas e horas desejadas
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

        // Insert
        ResponseEntity<Object> response = post("/api/v1/leiloes", leilao, null);
        System.out.println("response: ");
        System.out.println(response);

        // Verifica se criou
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        // Busca o objeto
        String location = Objects.requireNonNull(response.getHeaders().get("location")).get(0);
        LeilaoDTO l = getLeilao(location).getBody();

        assertNotNull(l);
        assertEquals(dataInicio, l.getDataInicio());

        // depois altera seu valor
        Leilao la = Leilao.create(l);
        la.setDataInicio(LocalDate.parse("25/06/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        // Update
        response = put("/api/v1/leiloes/" + l.getId(), la, null);
        System.out.println(response);
        assertEquals(LocalDate.parse("25/06/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy")), la.getDataInicio());

        // Deleta o objeto
        delete(location, null);

        // Verifica se deletou
        assertEquals(HttpStatus.NOT_FOUND, getLeilao(location).getStatusCode());
    }

    @Test
    public void testDelete() {
        this.testInsert();
    }

    @Test
    public void testGetNotFound() {
        ResponseEntity<LeilaoDTO> response = getLeilao("/api/v1/leiloes/1100");
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }
}

