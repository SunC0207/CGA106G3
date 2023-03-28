package CGA106G3.com.memoitemcate.Service;

import CGA106G3.com.memoitemcate.DTO.MemoitemcateDTO;
import CGA106G3.com.memoitemcate.Entity.Memoitemcate;
import CGA106G3.com.memoitemcate.repository.MemoitemcateRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemoitemcateServiceImpl  implements MemoitemcateService{
    @Autowired
    private MemoitemcateRepository MemoitemcateRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<MemoitemcateDTO> getAllMemoitemcate(){
        return MemoitemcateRepository.findAll()
                .stream()
                .map(this::EntityToDTO)
                .collect(Collectors.toList());
    }
    public MemoitemcateDTO EntityToDTO(Memoitemcate memoitemcate){
        MemoitemcateDTO memoitemcateDTO = new MemoitemcateDTO();
        memoitemcateDTO = modelMapper.map(memoitemcate, MemoitemcateDTO.class);
        return memoitemcateDTO;
    }
}
