package CGA106G3.com.emp.Service;

import org.modelmapper.ModelMapper;

import CGA106G3.com.emp.DTO.EmpDTO;
import CGA106G3.com.emp.Entity.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import CGA106G3.com.emp.repository.EmpRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpRepository empRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Transactional
    @Override
    public Emp register(Emp emp) {
        return null;
    }

    @Override
    public Emp login(Emp emp) {
//        final Integer empno = emp.getEmpno();
//        final String emppw = emp.getEmppw();
//
//        emp = empRepository.selectForLogin(empno, emppw);
//
//        emp.setMessage("登入成功");
//        emp.setLoginOrNot(true);

        return emp;

    }

    @Override
    public Emp edit(Emp emp) {
        return null;
    }

    @Override
    public List<EmpDTO> getAllEmp() {

        return empRepository.findAll()
                .stream()
                .map(this::EntityToDTO)
                .collect(Collectors.toList());
    }

    private EmpDTO EntityToDTO(Emp emp) {

        EmpDTO empDTO = new EmpDTO();
        empDTO = modelMapper.map(emp, EmpDTO.class);//(輸入型態,要轉換的型態.class)
        return empDTO;
    }

    @Override
    public boolean update(Emp emp) {
        return false;
    }
}
