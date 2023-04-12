package CGA106G3.com.memoitem.Service;

import CGA106G3.com.memoitem.DTO.MemoitemDTO;
import CGA106G3.com.memoitem.DTO.imgDTO;
import CGA106G3.com.memoitem.Entity.Memoitem;
import CGA106G3.com.memoitem.Repository.MemoitemRepository;
import CGA106G3.com.memoitemcate.Entity.Memoitemcate;
import CGA106G3.com.memoitemcate.repository.MemoitemcateRepository;
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
    public MemoitemDTO getOne(Integer mino){
        return EntityToDTO(memoitemRepository.getReferenceById(mino));
    }
    public MemoitemDTO addMemoitem(MemoitemDTO memoitemDTO){
        Memoitem memoitem = modelMapper.map(memoitemDTO,Memoitem.class);
        memoitemRepository.save(memoitem);
        return memoitemDTO;
    }

    public List<MemoitemDTO> getBySTA(Integer mista){
        return memoitemRepository.findBymista(mista)
                .stream()
                .map(this::EntityToDTO)
                .collect(Collectors.toList());
    }
    public List<MemoitemDTO> getByName(String miname){
        return memoitemRepository.findByminame(miname)
                .stream()
                .map(this::EntityToDTO)
                .collect(Collectors.toList());
    }
    public List<MemoitemDTO> findByMicateno(Integer micateno) {
        return memoitemRepository.findByMicateno(micateno)
                .stream()
                .map(this::EntityToDTO)
                .collect(Collectors.toList());
    }
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
