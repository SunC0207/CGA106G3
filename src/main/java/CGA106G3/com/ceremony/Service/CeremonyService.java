package CGA106G3.com.ceremony.Service;

import CGA106G3.com.ceremony.DTO.CeremonyDTO;
import CGA106G3.com.ceremony.DTO.JoinRelDto;
import CGA106G3.com.ceremony.Entity.Ceremony;
import CGA106G3.com.ceremony.repository.CeremonyRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
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

    public List<Ceremony> findAllCere (Ceremony ceremony) {
        return ceremonyRepository.findAll();
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

    @Transactional
    public List<Ceremony> findCeremoniesByRelNo(@RequestParam("relNo") Integer relNo){
        return ceremonyRepository.findByRelNo(relNo);
    }

    public List<Object> findRelNameByCerno(Integer cerNo){
        return ceremonyRepository.findRelNameByCerno(cerNo);
    }

    public List<JoinRelDto> findAllJoinRel() {
        List<Object[]> objects = ceremonyRepository.findAllJoinRel();
        return joinRelToDto(objects);
    }

    public List<JoinRelDto> joinRelToDto(List<Object[]> objects){
        List<JoinRelDto> detailDtos = new ArrayList<>();
        for (Object[] obj: objects) {
            JoinRelDto dto = new JoinRelDto();
            dto.setCerNo((Integer) obj[0]);
            dto.setRelNo((Integer) obj[1]);
            dto.setRelName((String) obj[2]);
            dto.setCerName((String) obj[3]);
            dto.setCerSta((Boolean) obj[4]);
            detailDtos.add(dto);
        }
        return detailDtos;
    }
}
