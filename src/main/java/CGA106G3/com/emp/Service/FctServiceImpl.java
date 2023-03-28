package CGA106G3.com.emp.Service;

import CGA106G3.com.emp.DTO.FctDTO;
import CGA106G3.com.emp.Entity.Fct;
import CGA106G3.com.emp.Repository.FctRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FctServiceImpl {
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
