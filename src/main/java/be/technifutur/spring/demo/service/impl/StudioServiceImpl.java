package be.technifutur.spring.demo.service.impl;

import be.technifutur.spring.demo.exceptions.ResourceNotFoundException;
import be.technifutur.spring.demo.models.entity.Address;
import be.technifutur.spring.demo.models.entity.Studio;
import be.technifutur.spring.demo.repository.AddressRepository;
import be.technifutur.spring.demo.repository.StudioRepository;
import be.technifutur.spring.demo.service.StudioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudioServiceImpl implements StudioService {

    private final StudioRepository studioRepository;
    private final AddressRepository addressRepository;

    public StudioServiceImpl(
            StudioRepository studioRepository,
            AddressRepository addressRepository
    ) {
        this.studioRepository = studioRepository;
        this.addressRepository = addressRepository;
    }

    /**
     * adds a Studio to the data base
     * <br/>
     * ex: <code>service.add(studio)</code>
     *
     * @param studio a studio to add to the database
     * @return this id of the studio
     * @author A.Kimtsaris
     */
    @Override
    public Long add(Studio studio) {
        return studioRepository.save( studio ).getId();
    }

    @Override
    public List<Studio> getAll() {
        return studioRepository.findAll();
    }

    @Override
    public Studio getOne(Long id) {
        return studioRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException(id, Studio.class));
    }

    @Override
    public void update(Long id, Studio studio) {

        Studio entity = getOne( id );

        entity.setName( studio.getName() );
        entity.setAddress( studio.getAddress() );

        studioRepository.save( entity );

    }

    @Override
    public void delete(Long id) {
        studioRepository.delete( getOne(id) );
    }
}
