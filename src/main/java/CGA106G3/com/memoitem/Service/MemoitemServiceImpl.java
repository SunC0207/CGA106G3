package CGA106G3.com.memoitem.Service;

import CGA106G3.com.memoitem.DTO.MemoitemDTO;
import CGA106G3.com.memoitem.Entity.Memoitem;
import CGA106G3.com.memoitem.Repository.MemoitemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemoitemServiceImpl implements MemoitemService{
    @Autowired
    private MemoitemRepository memoitemRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<MemoitemDTO> getAll(){
        return  memoitemRepository.findAll()
                .stream()
                .map(this :: EntityToDTO)
                .collect(Collectors.toList());
    }
    private MemoitemDTO EntityToDTO(Memoitem memoitem){
        MemoitemDTO memoitemDTO = new MemoitemDTO();
        memoitemDTO = modelMapper.map(memoitem,MemoitemDTO.class);
        return memoitemDTO;
    }
}
