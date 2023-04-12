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
    private ItemService itemService;
    @PostMapping("/add")
    public Item addItem(@RequestBody Item item){
        return itemService.addItem(item);
    }

    @RequestMapping("/update/{row}")
    public Item updateItem(@PathVariable("row") int row, @RequestBody Item item) {
        item.setItemNo(row);
        return itemService.addItem(item);
    }

    @RequestMapping("/find")
    public Optional<Item> findItemById(Integer itemNo, HttpServletResponse hsr){
        hsr.addHeader("Access-Control-Allow-Origin","*");
        return itemService.findItemById(itemNo);
    }

    @RequestMapping("/findAll")
    public Page<ItemDTO> getAllItem(@RequestParam int page, @RequestParam int size){
        Pageable pageable = PageRequest.of(page -1, size);
        return itemService.findAllItemDTO(pageable);
    }

    @RequestMapping("/getTotalPages")
    public int getTotalPages(@RequestParam int size){
        int totalRecords = (int) itemService.count();
        return (totalRecords + size - 1) / size;
    }

    @PostMapping("/updateIsta")
    public ResponseEntity<String> updateIsta(@RequestParam("itemNo") Integer itemNo, @RequestParam("iSta") Integer iSta){
        Optional<Item> optionalItem = itemService.findItemById(itemNo);
        if(optionalItem.isPresent()){
            Item item = optionalItem.get();
            item.setISta(iSta);
            itemService.updateItem(item);
            return ResponseEntity.ok("Success");
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}
