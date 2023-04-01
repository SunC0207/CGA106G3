package CGA106G3.com.Location.Service;

import CGA106G3.com.Location.DTO.LocDTO;
import CGA106G3.com.Location.Entity.Loc;

import java.util.List;
import java.util.Optional;

public interface LocService {
    Loc addLoc(Loc loc);

    Loc updateLoc(Loc loc);

    Optional<Loc> getOneLoc(Integer locno);

    List<LocDTO> getAllLoc();

    boolean update(Loc loc);

}
