package CGA106G3.Core.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
@Data
@JsonIgnoreProperties({ "hibernateLazyInitializer" })

public class EntityCore implements Serializable {

    private  boolean successful;
    private  String message;
    public EntityCore(){}

    public EntityCore(boolean successful, String message) {
        this.successful = successful;
        this.message = message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public String getReferenceByEmail(String email) {
        return email;
    }
}
