package br.edu.ifsul.cstsi.projeto_cstsi_tads.api.leilao;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/leiloes")

public class LeilaoController {
    @GetMapping
    public ResponseEntity<String> selectAll() {
        return ResponseEntity.ok("selectAll()");
    }

    @GetMapping("{id}")
    public ResponseEntity<String> selectById(@PathVariable("id") Long id) {
        return ResponseEntity.ok("SelectById: " + id);
    }

    @PostMapping
    public ResponseEntity<String> insert(@RequestBody Leilao leilao) {
        return ResponseEntity.ok("insert(): " + leilao);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody Leilao leilao) {
        return ResponseEntity.ok("update(): " + id + "\nleilão: " + leilao);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok("delete(): " + id);
    }
}
