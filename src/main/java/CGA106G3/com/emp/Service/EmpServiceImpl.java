package CGA106G3.com.emp.Service;

import CGA106G3.com.emp.DTO.PerDTO;
import CGA106G3.com.emp.DTO.PersistEmpDTO;
import CGA106G3.com.emp.Entity.Per;
import CGA106G3.com.emp.repository.PerRepository;
import org.modelmapper.ModelMapper;

import CGA106G3.com.emp.DTO.EmpDTO;
import CGA106G3.com.emp.Entity.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import CGA106G3.com.emp.repository.EmpRepository;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpRepository empRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PerRepository perRepository;


    @Override
    public EmpDTO getOne(Integer empno) {

       return EntityToDTO(empRepository.getReferenceById(empno));



    }

    @Override
    public Boolean persistEmp(PersistEmpDTO persistEmpDTO) {
        Emp emp = modelMapper.map(persistEmpDTO.getEmpDTO(), Emp.class);
        perRepository.removePerByEmpno(emp.getEmpno());
        empRepository.save(emp);
        for(PerDTO perDTO :persistEmpDTO.getPerDTOS()){
            perRepository.save(modelMapper.map(perDTO, Per.class));

        }
        return true;
    }


    @Override
    public Emp login(Emp emp) {


        return emp;

    }


    @Override
    public List<EmpDTO> getAllEmp() {

        return empRepository.findAll()
                .stream()
                .map(this::EntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Integer addEmp(EmpDTO empDTO) {
        Emp emp = modelMapper.map(empDTO,Emp.class);

        empRepository.save(emp);
        return emp.getEmpno();
    }

    @Override
    public List<EmpDTO> getEmpByEname(String ename) {
        return empRepository.findEmpByEname(ename)
                .stream()
                .map(this::EntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmpDTO> getEmpBySta(Integer empsta) {
        return empRepository.findEmpByEmpsta(empsta)
                .stream()
                .map(this::EntityToDTO)
                .collect(Collectors.toList());
    }

    private EmpDTO EntityToDTO(Emp emp) {

        EmpDTO empDTO = new EmpDTO();
        empDTO = modelMapper.map(emp, EmpDTO.class);//(輸入型態,要轉換的型態.class)
        return empDTO;
    }


}
