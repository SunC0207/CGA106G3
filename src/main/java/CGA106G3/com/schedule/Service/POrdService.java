package CGA106G3.com.schedule.Service;

import CGA106G3.com.schedule.DTO.POrdDTO;

import java.util.List;


public interface POrdService {

    List<POrdDTO> getAll();

    POrdDTO getOne(Integer pono);





}
