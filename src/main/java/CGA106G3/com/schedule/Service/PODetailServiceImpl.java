package CGA106G3.com.schedule.Service;

import CGA106G3.com.schedule.DTO.PODetailDTO;
import CGA106G3.com.schedule.DTO.PODetailRangeDTO;
import CGA106G3.com.schedule.Entity.PODetail;
import CGA106G3.com.schedule.Repository.PODetailRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PODetailServiceImpl implements PODetailService {

    @Autowired
    private PODetailRepository poDetailRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<PODetailDTO> getAll() {

        return poDetailRepository.findAll()
                .stream()
                .map(this::EntityToDTO)
                .collect(Collectors.toList());


    }

    @Override
    public PODetailDTO getOne(Integer pono) {
        return modelMapper.map(poDetailRepository.getReferenceById(pono), PODetailDTO.class);
    }

    @Override
    public List<PODetailDTO> getByDateRange(PODetailRangeDTO poDetailRangeDTO) {


        return poDetailRepository.findByDateRange(poDetailRangeDTO.getStartDate(), poDetailRangeDTO.getEndDate())
                .stream()
                .map(this::EntityToDTO)
                .collect(Collectors.toList());
    }


    private PODetailDTO EntityToDTO(PODetail poDetail) {
        return modelMapper.map(poDetail, PODetailDTO.class);
    }

}
