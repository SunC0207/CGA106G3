package CGA106G3.com.memoitempic.Service;

import CGA106G3.Core.Service.ServiceCore;
import CGA106G3.com.memoitempic.DTO.MemoitempicDTO;

import java.util.List;

public interface MemoitempicService extends ServiceCore {
     List<MemoitempicDTO> getAllMemoitempic();
}
