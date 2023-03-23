package CGA106G3.com.fct.Service;

import CGA106G3.com.fct.DTO.FctDTO;
import CGA106G3.com.fct.Entity.Fct;
import CGA106G3.com.fct.Repository.FctRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FctService {
    @Autowired
    private FctRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    public List<FctDTO> getAllFct(){
        return repository.findAll()
                .stream()
                .map(this::EntityToDTO)
                .collect(Collectors.toList());
    }

    public FctDTO EntityToDTO(Fct fct){
        FctDTO fctDTO = new FctDTO();
        fctDTO = modelMapper.map(fct, FctDTO.class);
        return fctDTO;
    }
}
