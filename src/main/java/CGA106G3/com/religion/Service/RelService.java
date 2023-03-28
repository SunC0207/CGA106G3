package CGA106G3.com.religion.Service;

import CGA106G3.com.religion.DTO.RelDTO;
import CGA106G3.com.religion.Entity.Rel;
import CGA106G3.com.religion.repository.RelRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class RelService {

    @Autowired
    private RelRepository relRepository;

    @Autowired
    private ModelMapper modelMapper;

    public boolean isRelNameExist(String relname) {
        return relRepository.findByRelname(relname) != null;
    }

    public Rel addRel(Rel rel) {
        return relRepository.save(rel);
    }

    public Rel updateRel(Rel rel) {

        return relRepository.save(rel);
    }

    public Optional<Rel> findRelById(Integer relno) {

        return relRepository.findById(relno);
    }

    public Optional<Rel> findByRelname(String relname) {

        return relRepository.findByRelname(relname);
    }

    public List<RelDTO> getAllRel() {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        return relRepository.findAll()
                .stream()
                .map(this::EntityToDTO)
                .collect(Collectors.toList());
    }

    private RelDTO EntityToDTO(Rel rel){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        RelDTO relDTO = new RelDTO();
        relDTO = modelMapper.map(rel, RelDTO.class);
        return relDTO;
    }
}
