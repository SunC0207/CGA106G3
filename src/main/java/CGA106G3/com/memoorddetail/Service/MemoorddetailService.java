package CGA106G3.com.memoorddetail.Service;

import CGA106G3.Core.Service.ServiceCore;
import CGA106G3.com.memoorddetail.DTO.MemoorddetailDTO;

import java.util.List;


public interface MemoorddetailService extends ServiceCore {

    List<MemoorddetailDTO> getAllMemoorddetail();
}
