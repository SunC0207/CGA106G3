package CGA106G3.com.item.Service;

import CGA106G3.com.item.DTO.ItemDTO;
import CGA106G3.com.item.Entity.Item;
import CGA106G3.com.item.repository.ItemRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Item addItem(Item item){
        return itemRepository.save(item);
    }
    public Item updateItem(Item item){
        return itemRepository.save(item);
    }
    public Optional<Item> findItemById(Integer itemNo){
        return itemRepository.findById(itemNo);
    }
    private ItemDTO entityToDTO(Item item){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        ItemDTO itemDTO = new ItemDTO();
        itemDTO = modelMapper.map(item, ItemDTO.class);
        return itemDTO;
    }

    public Page<Item> findAll(Pageable pageable){
        return itemRepository.findAll(pageable);
    }

    public Page<ItemDTO> findAllItemDTO(Pageable pageable) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Page<Item> itemPage = itemRepository.findAll(pageable);
        List<ItemDTO> items = itemPage.getContent()
                .stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(items, pageable, itemPage.getTotalElements());
    }

    public long count(){
        return itemRepository.count();
    }
}