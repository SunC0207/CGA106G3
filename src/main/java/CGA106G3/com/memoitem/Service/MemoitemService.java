package CGA106G3.com.memoitem.Service;

import CGA106G3.Core.Service.ServiceCore;
import CGA106G3.com.memoitem.DTO.MemoitemDTO;

import java.util.List;

public interface MemoitemService extends ServiceCore {

    List<MemoitemDTO> getAll();
}
