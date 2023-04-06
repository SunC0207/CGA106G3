package CGA106G3.com.ceremony.Service;

import CGA106G3.com.ceremony.DTO.CereDTO;
import CGA106G3.com.ceremony.Entity.Cere;
import CGA106G3.com.ceremony.repository.CereRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
//    public List<CereDTO> getAllCere(Pageable pageable){
//        modelMapper.getConfiguration()
//                .setMatchingStrategy(MatchingStrategies.LOOSE);
//        Page<Cere> cerePage = cereRepository.findAll(pageable);
//        List<CereDTO> ceres = cerePage.getContent()
//                .stream()
//                .map(this::EntityToDTO)
//                .collect(Collectors.toList());
//        return ceres;
//    }
    private CereDTO EntityToDTO(Cere cere){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        CereDTO cereDTO = new CereDTO();
        cereDTO = modelMapper.map(cere, CereDTO.class);
        return cereDTO;
    }

    public Page<Cere> findAll(Pageable pageable){
        return cereRepository.findAll(pageable);
    }

    public Page<CereDTO> findAllCereDTO(Pageable pageable) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Page<Cere> cerePage = cereRepository.findAll(pageable);
        List<CereDTO> ceres = cerePage.getContent()
                .stream()
                .map(this::EntityToDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(ceres, pageable, cerePage.getTotalElements());
    }

    public long count(){
        return cereRepository.count();
    }
}
