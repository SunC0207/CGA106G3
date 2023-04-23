package CGA106G3.com.indexNote.Controller;

import CGA106G3.com.indexNote.Entity.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/note")
@RestController
@CrossOrigin("*")
public class indexNoteController {
    @Autowired
    RedisTemplate redisTemplate;
    @PostMapping("/saveNote")
    public void saveNote(@RequestBody Note note){
        redisTemplate.opsForValue().set(note.getKey(),note.getNotes());

    }

    @GetMapping("/getNote")
    public Object getNotes(@RequestParam String key){
        return redisTemplate.opsForValue().get(key);
    }
}
