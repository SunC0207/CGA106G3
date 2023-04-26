package CGA106G3.com.memoitempic.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemoitempicDTO {
    private Integer mipicnomino;
    private MultipartFile mipicno;
    private String mipicname;
}
