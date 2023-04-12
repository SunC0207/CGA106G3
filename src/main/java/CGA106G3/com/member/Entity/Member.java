package CGA106G3.com.member.Entity;

import CGA106G3.Core.Entity.EntityCore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table
@Data
public class Member extends EntityCore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer membno;

    @Column(nullable = false, length = 20)
    private String mname;
    @Column(nullable = false, length = 20)
    private String mpw;
    @Lob
    private byte[] mpic;
    @Column(nullable = false, length = 1)
    private Integer sex;
    @Column(nullable = false, length = 10)
    private Integer mobile;
    @Column(nullable = false, length = 30)
    private String email;
    @Column(name = "ver_sta", length = 1, nullable = false)
    private Integer versta;

    //失敗計數器用
//    private Integer loginAttempts;
//
//    public Integer getLoginAttempts() {
//        return  loginAttempts;
//    }
//
//    public void setLoginAttempts(int loginAttempts) {
//        this.loginAttempts=loginAttempts;
//    }

//圖片第一版
//    public byte[] getMpic() {
//        return mpic;
//    }

//    public void setMpic(MultipartFile multipartFile) {
//        try {
//            this.mpic = multipartFile.getBytes();
//        } catch (IOException e) {
//            e.printStackTrace();
//            ;
//        }
//    }
//    public void setMpicBytes(byte[] mpic){
//        this.mpic = mpic;
//    }

//圖片第二版
//    public String getType() {
//        if (this.mpic != null && this.mpic.contains(".")) {
//            String extension = this.mpic.substring(this.mpic.hashCode(".") + 1);
//            return MediaType.valueOf("image/" + extension).toString();
//        }
//        return MediaType.IMAGE_JPEG_VALUE; // 如果無法判斷檔案類型，則默認為JPEG
//    }
}
