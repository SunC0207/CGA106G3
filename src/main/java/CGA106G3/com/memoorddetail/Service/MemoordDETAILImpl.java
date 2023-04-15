package CGA106G3.com.memoorddetail.Service;

import CGA106G3.com.memoorddetail.DTO.MemoorddetailDTO;
import CGA106G3.com.memoorddetail.Entity.Memoorddetail;
import CGA106G3.com.memoorddetail.Repository.MemoorddetailRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemoordDETAILImpl implements MemoorddetailService{
    @Autowired
    private MemoorddetailRepository memoorddetailRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<MemoorddetailDTO> getAllMemoorddetail(){
        return memoorddetailRepository.findAll()
                .stream()
                .map(this::EntityToDTO)
                .collect(Collectors.toList());
    }
    public List<MemoorddetailDTO> findByOrdno(Integer ordno){
        return memoorddetailRepository.findByOrdno(ordno)
                .stream()
                .map(this::EntityToDTO)
                .collect(Collectors.toList());
    }
    public List<MemoorddetailDTO> findByMino(Integer mino){
        return memoorddetailRepository.findByMino(mino)
                .stream()
                .map(this::EntityToDTO)
                .collect(Collectors.toList());
    }
    public List<MemoorddetailDTO> findByDate(Date midate){
        return memoorddetailRepository.findByMidate(midate)
                .stream()
                .map(this::EntityToDTO)
                .collect(Collectors.toList());
    }
    private MemoorddetailDTO EntityToDTO(Memoorddetail memoorddetail){
        MemoorddetailDTO memoorddetailDTO = new MemoorddetailDTO();
        memoorddetailDTO = modelMapper.map(memoorddetail,MemoorddetailDTO.class);
        return memoorddetailDTO;
    }
}
