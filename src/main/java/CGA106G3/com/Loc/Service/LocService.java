package CGA106G3.com.Loc.Service;

import CGA106G3.com.Loc.DTO.LocDTO;
import CGA106G3.com.Loc.Entity.Loc;

import java.util.List;
import java.util.Optional;

public interface LocService {
    Loc addLoc(Loc loc);

    Loc editLoc(Loc loc);

    Optional<Loc> getOneLoc(Integer locno);

    List<LocDTO> getAllLoc();

    boolean update(Loc loc);

}
