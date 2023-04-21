package CGA106G3.com.memoitemord.Service;

import CGA106G3.com.memoitemord.DTO.MemoitemordDTO;
import CGA106G3.com.memoitemord.DTO.MemoitemordDetailDTO;
import CGA106G3.com.memoitemord.DTO.MemoritemordOrderDTO;
import CGA106G3.com.memoitemord.Entity.Memoitemord;
import CGA106G3.com.memoitemord.Repository.MemoitemOrdDetailRepository;
import CGA106G3.com.memoitemord.Repository.MemoitemordRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemoitemordServiceImpl implements MemoitemordService{

    @Autowired
    private MemoitemordRepository memoitemordRepository;

    @Autowired
    private MemoitemOrdDetailRepository memoitemOrdDetailRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<MemoitemordDTO> getAllMemoitemord(){
        return memoitemordRepository.findAll()
                .stream()
                .map(this:: EntityToDTo)
                .collect(Collectors.toList());
    }
    private MemoitemordDTO EntityToDTo(Memoitemord memoitemord){
        MemoitemordDTO memoitemordDTO = new MemoitemordDTO();
        memoitemordDTO = modelMapper.map(memoitemord,MemoitemordDTO.class);
        return memoitemordDTO;
    }

@Override

     public Memoitemord findDetailByOrdno(Integer ordno){
        return  memoitemordRepository.findById(ordno).orElse(null);
     }

    @Override
    public List<MemoitemordDTO> findByMemb(Integer memeno) {
        return memoitemordRepository.findByMembno(memeno)
                .stream().map(this::EntityToDTo)
                .collect(Collectors.toList());
    }
    public List<MemoritemordOrderDTO> ordObjecttoDTO (List<Object[]> details){
        List<MemoritemordOrderDTO>detailDTO=new ArrayList<>();
        for(Object[] detail: details){
            MemoritemordOrderDTO dto = new MemoritemordOrderDTO();
            dto.setMino((Integer) detail[0]);
            dto.setMiname((String) detail[1]);
            dto.setMiprice((Integer) detail[2]);
            dto.setMicateno((Integer) detail[3]);
            dto.setMiqty((Integer)detail[4]);
            dto.setMidate((Date) detail[5]);
            dto.setOrdno((Integer)  detail[6]);
            dto.setMembno((Integer) detail[7]);
            dto.setTotalPr((Integer) detail[8]);
            dto.setOrddate((Date) detail[9]);
            dto.setOrdsta((Integer) detail[10]);
            dto.setPaysta((Integer) detail[11]);
            dto.setRec((String) detail[12]);
            dto.setRecaddr((String)detail[13]);
            detailDTO.add(dto);
        }
        return  detailDTO;
    }
//    訂單編號查詢
    @Override
    public List<MemoritemordOrderDTO> findAllMemoitemordDetailbyOrderno(Integer ordno) {
        List<Object[]> detail = memoitemOrdDetailRepository.findAllMemoitemordDetailbyOrderno(ordno);
        if(detail.isEmpty()){
            return new ArrayList<>();
        }
        return ordObjecttoDTO(detail);
    }


    //    會員編號查詢
    @Override
    public List<MemoritemordOrderDTO> findAllMemoitemordDetailbyMembno(Integer membno) {
        List<Object[]> detail = memoitemOrdDetailRepository.findAllMemoitemordDetailbyMembno(membno);
        return ordObjecttoDTO(detail);


    }
}
