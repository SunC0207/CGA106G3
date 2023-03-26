package CGA106G3.com.emp.Service;

import CGA106G3.com.emp.DTO.EmpDTO;
import CGA106G3.com.emp.DTO.PerDTO;
import CGA106G3.com.emp.Entity.Per;
import CGA106G3.com.emp.repository.PerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class PerServiceImpl implements PerService{
    @Autowired
    private PerRepository perRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Boolean updatePer(ArrayList<PerDTO> perDTOList) {
        for(PerDTO perDTO :perDTOList){
            perRepository.save(modelMapper.map(perDTO, Per.class));
        }

        return true;
    }

    @Override
    public List<PerDTO> getPerByEmpno(Integer empno) {

        return  perRepository.findPerByEmpno(empno)
                .stream()
                .map(this::EntityToDTO)
                .collect(Collectors.toList());

    }

    @Override
    public void deletePer(Integer empno) {
        perRepository.removePerByEmpno(empno);
    }

    private PerDTO EntityToDTO(Per per){
        PerDTO perDTO = new PerDTO();
        perDTO = modelMapper.map(per,PerDTO.class);
        return perDTO;
    }
}
