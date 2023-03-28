package CGA106G3.com.ceremony.Service;

import CGA106G3.com.ceremony.DTO.CereDTO;
import CGA106G3.com.ceremony.Entity.Cere;
import CGA106G3.com.ceremony.repository.CereRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class CereService {

    @Autowired
    private CereRepository cereRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Cere addCere(Cere cere){
        return cereRepository.save(cere);
    }
    public Cere updateCere(Cere cere){
        return cereRepository.save(cere);
    }
    public Optional<Cere> findCereById(Integer cereno){
        return cereRepository.findById(cereno);
    }
    public List<CereDTO> getAllCere(){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        return cereRepository.findAll()
                .stream()
                .map(this::EntityToDTO)
                .collect(Collectors.toList());
    }
    private CereDTO EntityToDTO(Cere cere){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        CereDTO cereDTO = new CereDTO();
        cereDTO = modelMapper.map(cere, CereDTO.class);
        return cereDTO;
    }
}
