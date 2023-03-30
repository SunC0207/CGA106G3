package CGA106G3.com.schedule.Service;

import CGA106G3.com.schedule.DTO.PODetailDTO;
import CGA106G3.com.schedule.DTO.PODetailRangeDTO;

import java.sql.Date;
import java.util.List;

public interface PODetailService {

    List<PODetailDTO> getAll();

    PODetailDTO getOne(Integer pono);


    List<PODetailDTO> getByDateRange(PODetailRangeDTO poDetailRangeDTO);
}
