package CGA106G3.com.faq.Controller;

import CGA106G3.com.faq.Entity.Faq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Faq")
public class FaqController {
    @Autowired
    private FaqServiceImpl service;
    @GetMapping("/getFaq")
    public Faq getFaq(@PathVariable("faqno") Integer faqno ){
        Faq faq = new Faq();


        return faq;
    }
}
