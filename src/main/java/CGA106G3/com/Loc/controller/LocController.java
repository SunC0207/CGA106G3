package CGA106G3.com.Loc.controller;

import CGA106G3.com.Loc.DTO.LocDTO;
import CGA106G3.com.Loc.Entity.Loc;
import CGA106G3.com.Loc.Service.LocService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Loc")
public class LocController {
    @Autowired
    private final LocService service;

    @Autowired
    private ModelMapper modelMapper;

    public LocController(LocService service) {
        this.service = service;
    }

    @PostMapping(value = "/addLoc")
    public LocDTO addLoc(@RequestBody Loc loc) {
        Loc addedLoc = service.addLoc(loc);
        return modelMapper.map(addedLoc,LocDTO.class);
    }

    @PostMapping(value = "/editLoc")
    public LocDTO editLoc(@RequestBody Loc loc) {
        Loc editedLoc = service.editLoc(loc);
        return modelMapper.map(editedLoc, LocDTO.class);
    }

    @RequestMapping(value = "/getOneLoc")
    public Optional<Loc> getOneLoc() {
        return service.getOneLoc(null);
    }
    @PostMapping(value= "/getAllLoc")
    public List<LocDTO> getAllLoc(){

        return service.getAllLoc();
    }

}
