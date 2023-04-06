package CGA106G3.com.optpic.Service;

import CGA106G3.com.optpic.DTO.OpicDTO;
import CGA106G3.com.optpic.Entity.Opic;
import CGA106G3.com.optpic.repository.OpicRepository;
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
public class OpicService {

    @Autowired
    private OpicRepository opicRepository;

    @Autowired
    private ModelMapper modelMapper;


    public Opic addOpic(Opic opic) {
        return opicRepository.save(opic);
    }

    public Opic updateOpic(Opic opic) {
        return opicRepository.save(opic);
    }

    public Optional<Opic> findOpicById(Integer opicno) {
        return opicRepository.findById(opicno);
    }

    public List<OpicDTO> getAllOpic() {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        return opicRepository.findAll()
                .stream()
                .map(this::EntityToDTO)
                .collect(Collectors.toList());
    }

    private OpicDTO EntityToDTO(Opic opic){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        OpicDTO opicDTO = new OpicDTO();
        opicDTO = modelMapper.map(opic, OpicDTO.class);
        return opicDTO;
    }

    public Page<OpicDTO> findAllOpicDTO(Pageable pageable) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Page<Opic> opicPage = opicRepository.findAll(pageable);
        List<OpicDTO> opics = opicPage.getContent()
                .stream()
                .map(this::EntityToDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(opics, pageable, opicPage.getTotalElements());
    }

    public long count(){
        return opicRepository.count();
    }
}
