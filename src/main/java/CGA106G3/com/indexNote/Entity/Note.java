package CGA106G3.com.indexNote.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note {
    private  String key;

    private List<String> notes;
}
