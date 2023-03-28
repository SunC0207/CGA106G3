package CGA106G3.com.memoitemord.Service;

import CGA106G3.com.memoitemord.DTO.MemoitemordDTO;
import CGA106G3.com.memoitemord.Entity.Memoitemord;
import CGA106G3.com.memoitemord.Repository.MemoitemordRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemoitemordServiceImpl implements MemoitemordService{
    @Autowired
    private MemoitemordRepository memoitemordRepository;
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
}
