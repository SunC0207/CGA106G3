package CGA106G3.com.ceremony.Service;

import CGA106G3.com.ceremony.DTO.CeremonyDTO;
import CGA106G3.com.ceremony.Entity.Ceremony;
import CGA106G3.com.ceremony.repository.CeremonyRepository;
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
public class CeremonyService {

    @Autowired
    private CeremonyRepository ceremonyRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Ceremony addCeremony(Ceremony ceremony){
        return ceremonyRepository.save(ceremony);
    }
    public Ceremony updateCeremony(Ceremony ceremony){
        return ceremonyRepository.save(ceremony);
    }
    public Optional<Ceremony> findCeremonyById(Integer cerNo){
        return ceremonyRepository.findById(cerNo);
    }
    private CeremonyDTO EntityToDTO(Ceremony ceremony){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        CeremonyDTO ceremonyDTO = new CeremonyDTO();
        ceremonyDTO = modelMapper.map(ceremony, CeremonyDTO.class);
        return ceremonyDTO;
    }

    public Page<Ceremony> findAll(Pageable pageable){
        return ceremonyRepository.findAll(pageable);
    }

    public Page<CeremonyDTO> findAllCeremonyDTO(Pageable pageable) {
        Page<Ceremony> ceremonyPage = ceremonyRepository.findAll(pageable);
        List<CeremonyDTO> ceremonys = ceremonyPage.getContent()
                .stream()
                .map(this::EntityToDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(ceremonys, pageable, ceremonyPage.getTotalElements());
    }

    public long count(){
        return ceremonyRepository.count();
    }
}
