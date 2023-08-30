package br.edu.ifsul.cstsi.projeto_cstsi_tads.api.participante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.net.URI;

@RestController
@RequestMapping("api/v1/participantes")

public class ParticipanteController {
    @Autowired
    private ParticipanteService service;

    @GetMapping
    public ResponseEntity<List<ParticipanteDTO>> selectAll() {
        List<ParticipanteDTO> participante = service.getParticipantes();
        return ResponseEntity.ok(participante);
    }

    @GetMapping("{id}")
    public ResponseEntity<ParticipanteDTO> selectById(@PathVariable("id") Long id) {
        ParticipanteDTO p = service.getParticipanteById(id);
        return p != null ? ResponseEntity.ok(p) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> insert(@RequestBody Participante participante) {
        ParticipanteDTO p = service.insert(participante);
        URI location = getUri(p.getId());
        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<ParticipanteDTO> update(@PathVariable("id") Long id, @RequestBody Participante participante) {
        participante.setId(id);
        ParticipanteDTO p = service.update(participante, id);
        return p != null ? ResponseEntity.ok(p) : ResponseEntity.notFound().build();

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        return service.delete(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }
}
