package CGA106G3.com.Location.controller;

import CGA106G3.com.Location.DTO.LocDTO;
import CGA106G3.com.Location.Entity.Loc;
import CGA106G3.com.Location.Service.LocService;
import CGA106G3.com.Location.Service.LocServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Loc")
public class LocController {
    @Autowired
    private LocServiceImpl service;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/update")
    public LocDTO updateLoc(@RequestBody LocDTO locDTO){ return service.updateLoc(locDTO);}

    @GetMapping("/getOneLoc")
    public Optional<Loc> getOneLoc(@RequestParam Integer locno) {
        return service.getOneLoc(locno);
    }

    @GetMapping("/getAllLoc")
    public List<LocDTO> getAllLoc(){return service.getAllLoc();}
}
