package CGA106G3.com.memoorddetail.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;

@Data
public class memoorddetailPK implements Serializable {


    private Integer ordno;

    private Integer mino;
}
