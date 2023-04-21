package CGA106G3.com.memoitemord.Service;

import CGA106G3.Core.Service.ServiceCore;
import CGA106G3.com.memoitemord.DTO.MemoitemordDTO;
import CGA106G3.com.memoitemord.DTO.MemoitemordDetailDTO;
import CGA106G3.com.memoitemord.DTO.MemoritemordOrderDTO;
import CGA106G3.com.memoitemord.Entity.Memoitemord;

import java.util.List;

public interface MemoitemordService extends ServiceCore {
    List<MemoitemordDTO> getAllMemoitemord();

    Memoitemord findDetailByOrdno(Integer ordno);

    List<MemoitemordDTO> findByMemb(Integer memeno);


    List<MemoritemordOrderDTO> findAllMemoitemordDetailbyOrderno(Integer ordno);

    List<MemoritemordOrderDTO> findAllMemoitemordDetailbyMembno(Integer membno);
}
