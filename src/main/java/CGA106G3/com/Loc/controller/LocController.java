package CGA106G3.com.Loc.controller;

import CGA106G3.com.Loc.DTO.LocDTO;
import CGA106G3.com.Loc.Service.LocService;
import CGA106G3.com.Loc.Service.LocServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Loc")
public class LocController {
    @Autowired
    private LocService service;

    @PostMapping(value="/getAll")
    public List<LocDTO> getAllLoc(){
        return service.getAllLoc();
    }

    @PostMapping(value = "/getOne")
    public LocDTO getOneLoc() { return service.getOneLoc();}

    @PostMapping(value = "/add")
    public LocDTO add() { return service.add();}

    @PostMapping(value = "/update")
    public LocDTO update(){ return service.update();}
}
