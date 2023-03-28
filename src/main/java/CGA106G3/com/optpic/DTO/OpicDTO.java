package CGA106G3.com.optpic.DTO;

import CGA106G3.com.optpic.Controller.OpicController;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.sql.Blob;

@Data
public class OpicDTO {
    private Integer picno;
    private String picname;
    private Integer optno;
    @JsonSerialize(using = OpicController.class)
    private Blob upfile;
}
