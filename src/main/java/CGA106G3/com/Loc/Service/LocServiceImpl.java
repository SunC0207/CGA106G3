package CGA106G3.com.Loc.Service;

import CGA106G3.com.Loc.DTO.LocDTO;
import CGA106G3.com.Loc.Entity.Loc;
import CGA106G3.com.Loc.Repository.LocRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;




@Service
public class LocServiceImpl implements LocService {

    @Autowired
    private LocRepository locRepository;

    @Autowired
    private ModelMapper modelMapper;

    public LocServiceImpl(LocRepository locRepository, ModelMapper modelMapper) {
        this.locRepository = locRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public Loc add(Loc loc){

        return null;
    }

    @Override
    public Loc edit(Loc loc){
        return null;

    }

    @Override
    public List<LocDTO> getAllLoc() {

        return locRepository.findAll()
                .stream()
                .map(this::EntityToDTO)
                .collect(Collectors.toList());
    }
    private LocDTO EntityToDTO(Loc loc){
        LocDTO locDTO = new LocDTO();
        locDTO = modelMapper.map(loc, LocDTO.class);
        return locDTO;
    }

    @Override
    public LocDTO getOneLoc(){
        return null;
    }

    @Override
    public boolean update(Loc loc) {
        return false;
    }

}
