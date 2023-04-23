package CGA106G3.com.schedule.Service;

import CGA106G3.com.schedule.DTO.PODetailJoinDto;
import CGA106G3.com.schedule.DTO.POrdDTO;
import CGA106G3.com.schedule.Entity.POrd;
import CGA106G3.com.schedule.Repository.POrdRepository;
import CGA106G3.com.schedule.Service.POrdService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class POrdServiceImpl implements POrdService {
    @Autowired
    POrdRepository pOrdRepository;
    @Autowired
    ModelMapper modelMapper;

    public POrd updatePOrd(POrd pOrd){
        return pOrdRepository.save(pOrd);
    }

    public POrd addPOrd(POrd pOrd) {
        return pOrdRepository.save(pOrd);
    }


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

    public Page<POrdDTO> findAllPOrdDTO(Pageable pageable) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Page<POrd> POrdPage = pOrdRepository.findAll(pageable);
        List<POrdDTO> POrds = POrdPage.getContent()
                .stream()
                .map(this::EntityToDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(POrds, pageable, POrdPage.getTotalElements());
    }

    public long count(){
        return pOrdRepository.count();
    }


    public List<PODetailJoinDto> detailToDto(List<Object[]> details){
        List<PODetailJoinDto> dtos = new ArrayList<>();

        for(Object[] detail: details){
            PODetailJoinDto dto = new PODetailJoinDto();
            dto.setPoDate((Date) detail[0]);
            dto.setPoNo((Integer) detail[1]);
            dto.setRelName((String) detail[2]);
            dto.setDName((String) detail[3]);
            dto.setDBirth((Date) detail[4]);
            dto.setDDate((Date) detail[5]);
            dto.setDate((Timestamp) detail[6]);
            dto.setIName((String) detail[7]);
            dto.setIPrice((Integer) detail[8]);
            dto.setLocName((String) detail[9]);
            dto.setTotalPr((Integer) detail[10]);
            dto.setPaySta((Integer) detail[11]);
            dto.setPoSta((Integer) detail[12]);
            dto.setDetailNo((Integer) detail[13]);
            dto.setItemNo((Integer) detail[14]);
            dto.setLocNo((Integer) detail[15]);
            dtos.add(dto);
        }
        return dtos;
    }

    public List<PODetailJoinDto> detailByPoNO(Integer pono){
        List<Object[]> details = pOrdRepository.detailByPoNO(pono);
        return detailToDto(details);
    }

}
