package CGA106G3.com.item.Controller;

import CGA106G3.com.item.DTO.ItemDTO;
import CGA106G3.com.item.Entity.Item;
import CGA106G3.com.item.Service.ItemService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemservice;
    @PostMapping("/add")
    public Item addItem(@RequestBody Item item){
        return itemservice.addItem(item);
    }

    @RequestMapping("/update/{row}")
    public Item updateItem(@PathVariable("row") int row, @RequestBody Item item) {
        item.setItemno(row);
        return itemservice.addItem(item);
    }

    @RequestMapping("/find")
    public Optional<Item> findItemById(Integer itemno, HttpServletResponse hsr){
        hsr.addHeader("Access-Control-Allow-Origin","*");
        return itemservice.findItemById(itemno);
    }

    @RequestMapping("/findAll")
    public Page<ItemDTO> getAllItem(@RequestParam int page, @RequestParam int size){
        Pageable pageable = PageRequest.of(page -1, size);
        return itemservice.findAllItemDTO(pageable);
    }

    @RequestMapping("/getTotalPages")
    public int getTotalPages(@RequestParam int size){
        int totalRecords = (int) itemservice.count();
        return (totalRecords + size - 1) / size;
    }

    @PostMapping("/updateIsta")
    public ResponseEntity<String> updateIsta(@RequestParam("itemno") Integer itemno, @RequestParam("ista") Integer ista){
        Optional<Item> optionalItem = itemservice.findItemById(itemno);
        if(optionalItem.isPresent()){
            Item item = optionalItem.get();
            item.setIsta(ista);
            itemservice.updateItem(item);
            return ResponseEntity.ok("Success");
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}
