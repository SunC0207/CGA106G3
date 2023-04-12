package CGA106G3.com.info.Service;

import CGA106G3.com.info.DTO.InfoDTO;
import CGA106G3.com.info.Entity.Info;
import CGA106G3.com.info.Respository.InfoRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Transactional
@Service
public class InfoServiceImpl implements InfoService{
    @Autowired
    private InfoRepository repository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<InfoDTO> getAllinfo() {
        return repository.findAll()
                .stream()
                .map(this::EntityTODTO)
                .collect(Collectors.toList());

    }

    @Override
    public Boolean updateInfo(InfoDTO infoDTO) {
        repository.save(modelMapper
                .map(infoDTO,Info.class));
        return true;
    }

    @Override
    public Boolean insertInfo(InfoDTO infoDTO) {
        repository.save(modelMapper
                .map(infoDTO,Info.class));

        return true;
    }

    @Override
    public void deleteInfo(Integer infono) {
         repository.deleteById(infono);
    }

    @Override
    public InfoDTO findByName(String infoname) {
        return modelMapper.map(
        repository.findByInfoname(infoname),InfoDTO.class);
    }

    private InfoDTO EntityTODTO(Info info){
        InfoDTO infoDTO = new InfoDTO();
        infoDTO = modelMapper.map(info,InfoDTO.class);
        return  infoDTO;
    }
}
