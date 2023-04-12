package CGA106G3.com.schedule.Service;

import CGA106G3.com.schedule.DTO.PODetailDTO;
import CGA106G3.com.schedule.DTO.PODetailRangeDTO;
import CGA106G3.com.schedule.DTO.ScheduleDTO;

import java.util.List;

public interface PODetailService {

    List<PODetailDTO> getAll();

    PODetailDTO getOne(Integer pono);


    List<PODetailDTO> getByDateRange(PODetailRangeDTO poDetailRangeDTO);


    List<ScheduleDTO> getByDateRangeAndPono(PODetailRangeDTO poDetailRangeDTO);


    List<PODetailDTO> findByLocno(Integer locno);

    List<ScheduleDTO> listAll();
}
