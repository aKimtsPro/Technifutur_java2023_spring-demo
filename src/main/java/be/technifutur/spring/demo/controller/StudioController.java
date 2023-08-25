package be.technifutur.spring.demo.controller;

import be.technifutur.spring.demo.models.dto.StudioDTO;
import be.technifutur.spring.demo.models.form.StudioForm;
import be.technifutur.spring.demo.service.StudioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studio")
public class StudioController {

    private final StudioService studioService;

    public StudioController(StudioService studioService) {
        this.studioService = studioService;
    }

    @PostMapping
    public ResponseEntity<Long> add(@RequestBody @Valid StudioForm form){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body( studioService.add( form.toEntity() ) );
    }

    @GetMapping
    public ResponseEntity<List<StudioDTO>> findAll(){
        return ResponseEntity.ok(
                studioService.getAll().stream()
                        .map( StudioDTO::toDTO )
                        .toList()
        );
    }

    @GetMapping("/{id:[0-9]+}")
    public ResponseEntity<StudioDTO> findOne(@PathVariable Long id){
        return ResponseEntity.ok( StudioDTO.toDTO(studioService.getOne(id)) );
    }


    @PutMapping("/{id:^[0-9]+$}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid StudioForm form){
        studioService.update(id, form.toEntity());
        return ResponseEntity.noContent()
                .build();
    }

    @DeleteMapping("/{id:[0-9]+}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        studioService.delete(id);
        return ResponseEntity.noContent()
                .build();
    }




}
