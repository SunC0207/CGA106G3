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
    private LocService service;

    @Autowired
    private ModelMapper modelMapper;


    @PostMapping(value = "/add")
    public Loc addLoc(Loc loc) {return service.addLoc(loc);}


    @RequestMapping(value = "/update")
    public Loc updateLoc(Loc loc){ return service.updateLoc(loc);}

    @RequestMapping(value = "/getOneLoc")
    public Optional<Loc> getOneLoc(Integer locno) {
        return service.getOneLoc(locno);
    }

    @RequestMapping(value= "/getAllLoc")
    public List<LocDTO> getAllLoc(){return service.getAllLoc();}
}
