package CGA106G3.com.member.Core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Transient;
import lombok.Data;

import java.io.Serializable;
@Data
@JsonIgnoreProperties({ "hibernateLazyInitializer" })
public class Core implements Serializable {
    private  boolean successful;
@Transient
    private  String message;
    public Core(){}

    public Core(boolean successful, String message) {
        this.successful = successful;
        this.message = message;
    }
}
