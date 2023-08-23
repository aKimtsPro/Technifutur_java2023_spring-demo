package be.technifutur.spring.demo.service.impl;

import be.technifutur.spring.demo.exceptions.ResourceNotFoundException;
import be.technifutur.spring.demo.models.entity.Studio;
import be.technifutur.spring.demo.repository.StudioRepository;
import be.technifutur.spring.demo.service.StudioService;
import org.springframework.stereotype.Service;

@Service
public class StudioServiceImpl implements StudioService {

    private final StudioRepository studioRepository;

    public StudioServiceImpl(StudioRepository studioRepository) {
        this.studioRepository = studioRepository;
    }

    @Override
    public Studio getOne(Long id) {
        return studioRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException(id, Studio.class));
    }
}
