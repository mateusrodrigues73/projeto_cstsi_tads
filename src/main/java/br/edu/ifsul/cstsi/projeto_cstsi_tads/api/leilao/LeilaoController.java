package br.edu.ifsul.cstsi.projeto_cstsi_tads.api.leilao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.net.URI;

@RestController
@RequestMapping("api/v1/leiloes")

public class LeilaoController {
    @Autowired
    private LeilaoService service;

    @GetMapping
    public ResponseEntity<List<LeilaoDTO>> selectAll() {
        List<LeilaoDTO> leiloes = service.getLeiloes();
        return ResponseEntity.ok(leiloes);
    }

    @GetMapping("{id}")
    public ResponseEntity<LeilaoDTO> selectById(@PathVariable("id") Long id) {
        LeilaoDTO l = service.getProdutoById(id);
        return l != null ? ResponseEntity.ok(l) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> insert(@RequestBody Leilao leilao) {
        LeilaoDTO l = service.insert(leilao);
        URI location = getUri(l.getId());
        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<LeilaoDTO> update(@PathVariable("id") Long id, @RequestBody Leilao leilao) {
        leilao.setId(id);
        LeilaoDTO l = service.update(leilao, id);
        return l != null ? ResponseEntity.ok(l) : ResponseEntity.notFound().build();

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        return service.delete(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }
}
