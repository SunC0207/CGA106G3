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
//    @Autowired
//    PordMemberRepository pordMemberRepository;
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
