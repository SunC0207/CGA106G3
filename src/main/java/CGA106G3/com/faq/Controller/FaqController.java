package CGA106G3.com.faq.Controller;

import CGA106G3.com.faq.DTO.FaqDTO;
import CGA106G3.com.faq.Entity.Faq;
import CGA106G3.com.faq.Service.FaqService;
import CGA106G3.com.faq.repository.FaqRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/faq")
//@ServerEndpoint("/faq")

public class FaqController {
    @Autowired
    private FaqService faqservice;


//    @PostMapping
//
//    public Faq addFaq(@RequestBody Faq faq){
//        return faqservice.addFaq(faq);
//    }





    @PostMapping("/add")
    public Faq addFaq(@RequestBody Faq faq){
        return  faqservice.addFaq(faq);
    }

    @GetMapping("/{faqname}")
    public Optional<Faq> findFaqByFaqname(@PathVariable String faqname){
        return  faqservice.findByFaqname(faqname);
    }

//    @GetMapping("/findByNo/{faqno}")
//    public Optional<Faq> findFaqByFaqno(@PathVariable Integer faqno){ return  faqservice.findFaqByFaqno(faqno);}
    @RequestMapping("/update/{row}")
    public Faq updateFaq(@PathVariable("row") int row, @RequestBody Faq faq){
        faq.setFaqno(row);
        return faqservice.addFaq(faq);

    }
    @RequestMapping("/delete/{row}")
    public  boolean deleteFaq(@PathVariable("row") int row){
        return faqservice.deleteFaq(row);




    }

    @RequestMapping("/findbyid")
    public Optional<Faq> findFaqById(Integer faqno, HttpServletResponse htpsr){
     htpsr.addHeader("Access-Control-Allow-Origin", "*");
     return faqservice.findFaqById(faqno);
    }

    @RequestMapping("/findAll")
    public List<FaqDTO> getAllFaq(){
        return  faqservice.getAllFaq();
    }

    @RequestMapping("/getTotalPages")
    public int getTotalPages(@RequestParam int size){
        int totalRecords = (int) faqservice.count();
        return (totalRecords + size - 1) / size;
    }


    @Autowired
    private FaqRepository faqRepository;

    @GetMapping("/search")
    public List<Faq> searchFaq(@RequestParam("faqtag") String faqtag) {
        System.out.println("參數 : "+faqtag);
        return faqRepository.findByFaqtagContaining(faqtag);
    }



}

