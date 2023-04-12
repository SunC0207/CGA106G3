package CGA106G3.com.info.Service;


import CGA106G3.com.info.DTO.InfoDTO;
import CGA106G3.com.info.Entity.Info;

import java.util.List;

public interface InfoService{
    public List<InfoDTO> getAllinfo();

    public Boolean updateInfo(InfoDTO infoDTO);
    public Boolean insertInfo(InfoDTO infoDTO);
    public void deleteInfo(Integer infono);
    public InfoDTO findByName(String infoname);



}