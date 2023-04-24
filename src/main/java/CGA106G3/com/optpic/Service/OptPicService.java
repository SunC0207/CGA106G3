package CGA106G3.com.optpic.Service;

import CGA106G3.com.optpic.DTO.OptPicDTO;
import CGA106G3.com.optpic.Entity.OptPic;
import CGA106G3.com.optpic.repository.OptPicRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class OptPicService {

    @Autowired
    private OptPicRepository optPicRepository;

    @Autowired
    private ModelMapper modelMapper;

    public OptPic findByOptNo(Integer optNo){
        return optPicRepository.findByOptNo(optNo);
    }

    @Transactional
    public void deleteByOptNo(Integer optNo){
        optPicRepository.deleteByOptNo(optNo);
    }

    public OptPic addOptPic(OptPic optPic) {
        return optPicRepository.save(optPic);
    }

    public OptPic updateOptPic(OptPic optPic) {
        return optPicRepository.save(optPic);
    }

    public Optional<OptPic> findOptPicById(Integer optPicno) {
        return optPicRepository.findById(optPicno);
    }

    public List<OptPicDTO> getAllOptPic() {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        return optPicRepository.findAll()
                .stream()
                .map(this::EntityToDTO)
                .collect(Collectors.toList());
    }

    private OptPicDTO EntityToDTO(OptPic optPic){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        OptPicDTO optPicDTO = new OptPicDTO();
        optPicDTO = modelMapper.map(optPic, OptPicDTO.class);
        return optPicDTO;
    }

    public Page<OptPic> findAll(Pageable pageable) {
        return optPicRepository.findAll(pageable);
    }


    public long count(){
        return optPicRepository.count();
    }
}
