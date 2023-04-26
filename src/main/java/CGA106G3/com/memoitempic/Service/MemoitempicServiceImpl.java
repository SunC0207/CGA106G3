package CGA106G3.com.memoitempic.Service;

import CGA106G3.com.memoitempic.DTO.MemoitempicDTO;
import CGA106G3.com.memoitempic.Entity.Memoitempic;
import CGA106G3.com.memoitempic.Repository.MemoitempicRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class MemoitempicServiceImpl implements MemoitempicService{
    @Autowired
    private MemoitempicRepository memoitempicRepository;
    @Autowired
    private ModelMapper modelMapper;

//    public void saveImage(Integer id, InputStream imageStream) throws IOException {
//        Memoitempic memoitempic = memoitempicRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid memoitempic id: " + id));
//
//        // 將圖像數據寫入 Blob 對象中
//        Blob imageBlob = new SerialBlob(IOUtils.toByteArray(imageStream));
//
//        // 設置實體類的 mino 屬性
//        memoitempic.setMino(imageBlob);
//
//        // 將更新後的實體類保存到數據庫中
//        memoitempicRepository.save(memoitempic);
//    }
    public List<MemoitempicDTO> getAllMemoitempic(){
        return memoitempicRepository.findAll()
                .stream()
                .map(this::EntityToDTO)
                .collect(Collectors.toList());
    }
    public Memoitempic getByMino(Integer mino){
        return EntityToDTO2(memoitempicRepository.findByMino(mino));
    }
    public Memoitempic addPic(Memoitempic memoitempicDTO){
        Memoitempic memoitempic = modelMapper.map(memoitempicDTO,Memoitempic.class);
        memoitempicRepository.save(memoitempic);
        return memoitempicDTO;
    }

    public MemoitempicDTO EntityToDTO(Memoitempic memoitempic) {
            MemoitempicDTO memoitempicDTO = modelMapper.map(memoitempic, MemoitempicDTO.class);
            return memoitempicDTO;
    }
    public Memoitempic EntityToDTO2(Memoitempic memoitempic) {
            MemoitempicDTO memoitempicDTO = modelMapper.map(memoitempic, MemoitempicDTO.class);
            return memoitempic;
    }


}
