package CGA106G3.com.Loc.Service;

import CGA106G3.com.Loc.DTO.LocDTO;
import CGA106G3.com.Loc.Entity.Loc;

import java.util.List;

public interface LocService {
    Loc add(Loc loc);

    Loc edit(Loc loc);

    List<LocDTO> getAllLoc();


    LocDTO getOneLoc();

    boolean update(Loc loc);

}
