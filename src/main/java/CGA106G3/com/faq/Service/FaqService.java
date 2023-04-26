package CGA106G3.com.faq.Service;


import CGA106G3.com.faq.DTO.FaqDTO;
import CGA106G3.com.faq.Entity.Faq;
import CGA106G3.com.faq.repository.FaqRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service

public class FaqService {
    private static final String Key = "faq:";


    @Autowired
    private RedisTemplate<String , Faq> redisTemplate;

    @Autowired
    private FaqRepository faqRepository;


//    public void setFaq(String faq, String faqname){
//        redisTemplate.opsForValue().set(faq,faqname);
//    }
//    public String getFaqname(String faq){
//        return redisTemplate.opsForValue().get(faq);
//    }
//
//    public Faq addFaq(Faq faq){
//        redisTemplate.opsForValue().set(faq.getFaqname().toString(), String.valueOf(faq));
//
//        return faqRepository.save(faq);
//    }










    @Autowired
    private ModelMapper modelMapper;

    public boolean isFaqNameExist(String faqname){

        return faqRepository.findByFaqname(faqname) != null;
    }
@Transactional
    public Faq addFaq(Faq faq){
        Faq savedFaq = faqRepository.save(faq);
//        redisTemplate.opsForValue().set(Key + faq.getFaqno(), savedFaq);
        return savedFaq;
    }

    public Faq updateFaq(Faq faq){
        Faq updatedFaq = faqRepository.save(faq);
//        redisTemplate.opsForValue().set(Key + faq.getFaqno(), updatedFaq);
        return updatedFaq;
    }

//    public Optional<Faq> findFaqByFaqno(Integer faqno) {
//        return faqRepository.findById(faqno);
//    }



    public Optional<Faq> findFaqById(Integer faqno){
        String key = Key + faqno;
        Faq faq = redisTemplate.opsForValue().get(key);
        if (faq == null){
            Optional<Faq> optionalFaq = faqRepository.findById(faqno);
            optionalFaq.ifPresent(f -> redisTemplate.opsForValue().set(key, f));
            return optionalFaq;
        }
        return Optional.of(faq);
    }

    public Optional<Faq> findByFaqname(String faqname){
        return faqRepository.findByFaqname(faqname);
    }
    public List<FaqDTO> getAllFaq(){
        String key = Key + "*";
//        Set<String> keys = redisTemplate.keys(key);
//        if (keys == null || keys.isEmpty()){
            List<Faq> faqs = faqRepository.findAll();

//            faqs.forEach(faq -> redisTemplate.opsForValue().set(Key + faq.getFaqno(), faq));
            return faqs.stream().map(this::EntityToDTO).collect(Collectors.toList());
        }
//        return redisTemplate.opsForValue().multiGet(keys).stream().map(this::EntityToDTO).collect(Collectors.toList());

//    }

//    public  void deleteFaq(Integer faqno) {
//         faqRepository.deleteById(faqno);
//        redisTemplate.delete(Key + faqno);
        public boolean deleteFaq(Integer faqno) {
            try {
                faqRepository.deleteById(faqno);
                return true; // 成功
            } catch (Exception e) {
                return false; // 失敗
            }
        }




    private FaqDTO EntityToDTO(Faq faq){
      FaqDTO faqDTO = modelMapper.map(faq, FaqDTO.class);
        return faqDTO;
    }




//    public Faq saveFaq(String faq){
//        redisTemplate.opsForValue().set(faq.getFaqname().toString(), faq);
//
//        return faqRepository.save(faq);
//    }
//    public Faq getFaqname(Object faqname){
//
//        Faq faq = redisTemplate.opsForValue().get(faqname.toString());
//
//        if (faq == null){
//
//            Optional<Faq> optionalFaq = faqRepository.findByFaqname(faqname);
//
//            if (optionalFaq.isPresent()){
//                faq = optionalFaq.get();
//
//                redisTemplate.opsForValue().set(faq.getFaqname().toString(), faq);
//            }
//        }
//        return faq;
//    }


    public Page<Faq> findAll(Pageable pageable){
        return faqRepository.findAll(pageable);
    }

    public Page<FaqDTO> findAllFaqDTO(Pageable pageable) {
        Page<Faq> faqPage = faqRepository.findAll(pageable);
        List<FaqDTO> faqq = faqPage.getContent()
                .stream()
                .map(this::EntityToDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(faqq, pageable, faqPage.getTotalElements());
    }

    public long count(){
        return faqRepository.count();
    }








}
