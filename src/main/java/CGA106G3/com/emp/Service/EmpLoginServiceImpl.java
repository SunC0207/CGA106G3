package CGA106G3.com.emp.Service;

import CGA106G3.com.emp.DTO.EmpDTO;
import CGA106G3.com.emp.DTO.EmpLoginDTO;
import CGA106G3.com.emp.DTO.PerDTO;
import CGA106G3.com.emp.DTO.PersistEmpDTO;
import CGA106G3.com.emp.Entity.Emp;
import CGA106G3.com.emp.Entity.Per;
import CGA106G3.com.emp.Repository.EmpRepository;
import CGA106G3.com.emp.Repository.PerRepository;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class EmpLoginServiceImpl implements EmpLoginService {

    @Autowired
    private EmpRepository empRepository;
    @Autowired
    private PerRepository perRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EntityManager entityManager;



    @Override
    public Boolean login(EmpLoginDTO empLoginDTO) {

        Emp emp = empRepository.getReferenceById(empLoginDTO.getEmpno());

        if (empLoginDTO.getEmppw().equals(emp.getEmppw())) {

            Session session = entityManager.unwrap(Session.class);

            PersistEmpDTO persistEmpDTO = new PersistEmpDTO();

            ArrayList<PerDTO> perDTOList = (ArrayList<PerDTO>) perRepository.findPerByEmpno(emp.getEmpno())
                    .stream()
                    .map(this::EntityToDTO)
                    .collect(Collectors.toList());

            persistEmpDTO.setEmpDTO(modelMapper.map(emp, EmpDTO.class));
            persistEmpDTO.setPerDTOS(perDTOList);

            session.persist("EmpInfo", persistEmpDTO);

            return true;
        } else {
            return false;
        }


    }

    private PerDTO EntityToDTO(Per per) {
        PerDTO perDTO = new PerDTO();
        return modelMapper.map(per, PerDTO.class);
    }
}
