package br.edu.ifsul.cstsi.projeto_cstsi_tads.api.leilao;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.net.URI;

@RestController
@RequestMapping("api/v1/leiloes")
@Api(value = "Leiloẽs")
public class LeilaoController {
    @Autowired
    private LeilaoService service;

    @GetMapping
    @ApiOperation(value = "Retorna todos os leilões cadastrados.")
    public ResponseEntity<List<LeilaoDTO>> selectAll() {
        List<LeilaoDTO> leiloes = service.getLeiloes();
        return ResponseEntity.ok(leiloes);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Retorna um leilão pelo campo identificador.")
    public ResponseEntity<LeilaoDTO> selectById(@PathVariable("id") Long id) {
        LeilaoDTO l = service.getLeilaoById(id);
        return l != null ? ResponseEntity.ok(l) : ResponseEntity.notFound().build();
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping
    @ApiOperation(value = "Insere um novo leilão.")
    public ResponseEntity<String> insert(@RequestBody Leilao leilao) {
        LeilaoDTO l = service.insert(leilao);
        URI location = getUri(l.getId());
        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    @ApiOperation(value = "Atualiza um leilao existente.")
    public ResponseEntity<LeilaoDTO> update(@PathVariable("id") Long id, @RequestBody Leilao leilao) {
        leilao.setId(id);
        LeilaoDTO l = service.update(leilao, id);
        return l != null ? ResponseEntity.ok(l) : ResponseEntity.notFound().build();

    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Deleta um leilão.")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        return service.delete(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }
}
