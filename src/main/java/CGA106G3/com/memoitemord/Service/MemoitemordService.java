package CGA106G3.com.memoitemord.Service;

import CGA106G3.Core.Service.ServiceCore;
import CGA106G3.com.memoitemord.DTO.MemoitemordDTO;

import java.util.List;

public interface MemoitemordService extends ServiceCore {
    List<MemoitemordDTO> getAllMemoitemord();
}
