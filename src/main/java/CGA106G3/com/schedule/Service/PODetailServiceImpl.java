package CGA106G3.com.schedule.Service;

import CGA106G3.com.Location.Entity.Loc;
import CGA106G3.com.Location.Repository.LocRepository;
import CGA106G3.com.schedule.DTO.PODetailDTO;
import CGA106G3.com.schedule.DTO.PODetailRangeDTO;
import CGA106G3.com.schedule.DTO.POrdDTO;
import CGA106G3.com.schedule.DTO.ScheduleDTO;
import CGA106G3.com.schedule.Entity.PODetail;
import CGA106G3.com.schedule.Entity.POrd;
import CGA106G3.com.schedule.Repository.PODetailRepository;
import CGA106G3.com.schedule.Repository.POrdRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PODetailServiceImpl implements PODetailService {

    @Autowired
    private PODetailRepository poDetailRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private POrdRepository pOrdRepository;
    @Autowired
    private LocRepository locRepository;


    @Override
    public List<PODetailDTO> getAll() {

        return poDetailRepository.findAll()
                .stream()
                .map(this::EntityToDTO)
                .collect(Collectors.toList());


    }

    @Override
    public PODetailDTO getOne(Integer pono) {
        return modelMapper.map(poDetailRepository.getReferenceById(pono), PODetailDTO.class);
    }

    @Override
    public List<PODetailDTO> getByDateRange(PODetailRangeDTO poDetailRangeDTO) {


        return poDetailRepository.findByDateRange(poDetailRangeDTO.getStartDate(), poDetailRangeDTO.getEndDate())
                .stream()
                .map(this::EntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ScheduleDTO> getByDateRangeAndPono(PODetailRangeDTO poDetailRangeDTO) {
        //先拿員工編號 取得方案訂單列表 再用方案訂單列表 來取得明細
        List<POrdDTO> pOrdDTOList = pOrdRepository.findByEmpno(poDetailRangeDTO.getEmpno())
                .stream()
                .map(this::EntityToDTO)
                .collect(Collectors.toList());
        List<ScheduleDTO> list = new ArrayList<>();
        for (POrdDTO pOrdDTO : pOrdDTOList) {

            List<PODetailDTO> PODetailDTOList = poDetailRepository.findByDateRangeAndPono(poDetailRangeDTO.getStartDate(), poDetailRangeDTO.getEndDate(), pOrdDTO.getPono())
                    .stream()
                    .map(this::EntityToDTO)
                    .collect(Collectors.toList());


            for (PODetailDTO poDetailDTO : PODetailDTOList) {
                ScheduleDTO scheduleDTO = new ScheduleDTO();
                scheduleDTO.setDate(poDetailDTO.getDate());
                Loc loc = locRepository.getReferenceById(poDetailDTO.getLocno());
                scheduleDTO.setLocation(loc.getLocname());
                list.add(scheduleDTO);
            }


        }


        return list;
    }

    @Override
    public List<PODetailDTO> findByLocno(Integer locno) {
        return poDetailRepository.findByLocno(locno)
                .stream()
                .map(this::EntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ScheduleDTO> listAll() {
        List<PODetailDTO> poDetailDTOList = poDetailRepository.findAll()
                .stream()
                .map(this::EntityToDTO)
                .collect(Collectors.toList());
        List<ScheduleDTO> list = new ArrayList<>();
        for (PODetailDTO poDetailDTO : poDetailDTOList) {
            ScheduleDTO scheduleDTO = new ScheduleDTO();
            scheduleDTO.setDate(poDetailDTO.getDate());
            Loc loc = locRepository.getReferenceById(poDetailDTO.getLocno());
            scheduleDTO.setLocation(loc.getLocname());
            list.add(scheduleDTO);
        }
        return list;
    }

    private POrdDTO EntityToDTO(POrd pOrd) {
        POrdDTO pOrdDTO = new POrdDTO();
        return modelMapper.map(pOrd, POrdDTO.class);
    }



    private PODetailDTO EntityToDTO(PODetail poDetail) {
        return modelMapper.map(poDetail, PODetailDTO.class);
    }

}
