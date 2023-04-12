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
public class MemoitemcateServiceImpl implements MemoitemcateService {
    @Autowired
    private MemoitemcateRepository memoitemcateRepository;
    @Autowired
    private ModelMapper modelMapper;

    public MemoitemcateDTO addMemoitemcate(MemoitemcateDTO memoitemcateDTO) {
        Memoitemcate memoitemcate = modelMapper.map(memoitemcateDTO, Memoitemcate.class);
        memoitemcateRepository.save(memoitemcate);
        return memoitemcateDTO;
    }

    public MemoitemcateDTO getOne(Integer micateno) {
        return EntityToDTO(memoitemcateRepository.getReferenceById(micateno));
    }

    public List<MemoitemcateDTO> getAllMemoitemcate() {
        return memoitemcateRepository.findAll()
                .stream()
                .map(this::EntityToDTO)
                .collect(Collectors.toList());
    }

    public List<MemoitemcateDTO> getBySTA(Integer micatesta) {
        return memoitemcateRepository.findByMicatesta(micatesta)
                .stream()
                .map(this::EntityToDTO)
                .collect(Collectors.toList());
    }

    public List<MemoitemcateDTO> getByName(String micname) {
        return memoitemcateRepository.findByMicname(micname)
                .stream()
                .map(this::EntityToDTO)
                .collect(Collectors.toList());
    }

    public MemoitemcateDTO EntityToDTO(Memoitemcate memoitemcate) {
        MemoitemcateDTO memoitemcateDTO = new MemoitemcateDTO();
        memoitemcateDTO = modelMapper.map(memoitemcate, MemoitemcateDTO.class);
        return memoitemcateDTO;
    }
}
