package CGA106G3.com.schedule.Service;

import CGA106G3.com.schedule.DTO.POrdDTO;
import CGA106G3.com.schedule.Entity.POrd;
import CGA106G3.com.schedule.Repository.POrdRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class POrdServiceImpl implements POrdService {
    @Autowired
    POrdRepository pOrdRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<POrdDTO> getAll() {

        return pOrdRepository.findAll()
                .stream()
                .map(this::EntityToDTO)
                .collect(Collectors.toList());

    }


    @Override
    public POrdDTO getOne(Integer pono) {
        return modelMapper.map(pOrdRepository.getReferenceById(pono), POrdDTO.class);
    }

    private POrdDTO EntityToDTO(POrd pOrd) {
        POrdDTO pOrdDTO = new POrdDTO();
        return modelMapper.map(pOrd, POrdDTO.class);
    }


}
