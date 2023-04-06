package CGA106G3.com.optpic.Controller;

import CGA106G3.com.optpic.DTO.OpicDTO;
import CGA106G3.com.optpic.Entity.Opic;
import CGA106G3.com.optpic.Service.OpicService;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Optional;

@RestController
@RequestMapping("/opic")
public class OpicController extends JsonSerializer<Blob> {
    @Autowired
    private OpicService opicservice;
    @PostMapping("/add")
    public Opic addOpic(Opic opic){
        return opicservice.addOpic(opic);
    }

    @RequestMapping("/update")
    public Opic updateOpic(Opic opic) {
        return opicservice.addOpic(opic);
    }

    @RequestMapping("/find")
    public Optional<Opic> findOpicById(Integer opicno, HttpServletResponse hsr){
        hsr.addHeader("Access-Control-Allow-Origin","*");
        return opicservice.findOpicById(opicno);
    }

    @RequestMapping("/findAll")
    public Page<OpicDTO> getAllOpice(@RequestParam int page, @RequestParam int size){
        Pageable pageable = PageRequest.of(page -1, size);
        return opicservice.findAllOpicDTO(pageable);
    }

    @RequestMapping("/getTotalPages")
    public int getTotalPages(@RequestParam int size){
        int totalRecords = (int) opicservice.count();
        return (totalRecords + size - 1) / size;
    }
    @Override
    public void serialize(Blob value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value != null) {
            try (InputStream is = value.getBinaryStream()) {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                int bytesRead;
                while ((bytesRead = is.read(buf)) != -1) {
                    bos.write(buf, 0, bytesRead);
                }
                gen.writeString(Base64.getEncoder().encodeToString(bos.toByteArray()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
