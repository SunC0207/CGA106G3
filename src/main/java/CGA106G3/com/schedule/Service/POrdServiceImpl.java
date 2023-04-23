package CGA106G3.com.schedule.Service;


import CGA106G3.com.schedule.DTO.POrdDTO;
import CGA106G3.com.schedule.Entity.POrd;
import CGA106G3.com.schedule.Repository.POrdRepository;

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
//    @Autowired
//    PordMemberRepository pordMemberRepository;
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

    @Override
    public List<POrdDTO> findByMembno(Integer membno) {
        return pOrdRepository.findByMembno(membno)
                .stream().map(this::EntityToDTO)
                .collect(Collectors.toList());

    }

//    public List<MemberPOrdDTO>pOrdObjetctoDTO(List<Object[]> details){
//        List<MemberPOrdDTO>detailDTO=new ArrayList<>();
//        for(Object[] detail:details){
//            MemberPOrdDTO dto = new MemberPOrdDTO();
//            dto.setPoDate((Timestamp) detail[0]);
//            dto.setPoDate((Timestamp) detail[0]);
//            dto.setPono((Integer) detail[1]);
//            dto.setMembno((Integer) detail[2]);
//            dto.setTPrice((Integer) detail[4]);
//            dto.setPoDate((Timestamp) detail[5]);
//            dto.setPaysta((Integer) detail[7]);
//            dto.setPoType((Integer) detail[8]);
//            dto.setDname((String) detail[9]);
//            detailDTO.add(dto);
//
//        }
//        return detailDTO;
//    }
//
// @Override
//    public List<MemberPOrdDTO> findPordByMembno(Integer membno) {
//        List<Object[]> detail = pordMemberRepository.findPordByMembno(membno);
//        if(detail.isEmpty()){
//            return new ArrayList<>();
//        }
//        return pOrdObjetctoDTO(detail);
//    }








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





}
