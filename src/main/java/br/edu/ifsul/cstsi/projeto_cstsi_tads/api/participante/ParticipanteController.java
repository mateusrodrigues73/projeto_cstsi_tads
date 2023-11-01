package br.edu.ifsul.cstsi.projeto_cstsi_tads.api.participante;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.net.URI;

@RestController
@RequestMapping("api/v1/participantes")
@Api(value = "Leilões")
public class ParticipanteController {
    @Autowired
    private ParticipanteService service;

    @GetMapping
    @ApiOperation(value = "Retorna todos os leilões cadastrados.")
    public ResponseEntity<List<ParticipanteDTO>> selectAll() {
        List<ParticipanteDTO> participante = service.getParticipantes();
        return ResponseEntity.ok(participante);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Retorna um leilão pelo campo identificador.")
    public ResponseEntity<ParticipanteDTO> selectById(@PathVariable("id") Long id) {
        ParticipanteDTO p = service.getParticipanteById(id);
        return p != null ? ResponseEntity.ok(p) : ResponseEntity.notFound().build();
    }

    @PostMapping("/new")
    @ApiOperation(value = "Insere um novo leilão.")
    public ResponseEntity<String> insert(@RequestBody Participante participante) {
        ParticipanteDTO p = service.insert(participante);
        URI location = getUri(p.getId());
        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    @ApiOperation(value = "Altera um leilão existente.")
    public ResponseEntity<ParticipanteDTO> update(@PathVariable("id") Long id, @RequestBody Participante participante) {
        participante.setId(id);
        ParticipanteDTO p = service.update(participante, id);
        return p != null ? ResponseEntity.ok(p) : ResponseEntity.notFound().build();

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
