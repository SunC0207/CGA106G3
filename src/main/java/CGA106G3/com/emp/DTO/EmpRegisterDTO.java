package CGA106G3.com.emp.DTO;

import lombok.Data;

import java.sql.Date;

@Data
public class EmpRegisterDTO {
    private String ename;

    private Date hiredate;

    private Integer empsta;

    private String emppw;
}
