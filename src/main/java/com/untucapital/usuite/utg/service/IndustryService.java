package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.Industry;
import com.untucapital.usuite.utg.model.User;
import com.untucapital.usuite.utg.repository.IndustryRepository;
import com.untucapital.usuite.utg.untu_capital.response.GlobalImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;


@Service
@javax.transaction.Transactional
public class IndustryService extends AbstractService<Industry> {
    @Autowired
    private final GlobalImageService globalImageService;

    private static final Logger log = LoggerFactory.getLogger(IndustryService.class);

    private final IndustryRepository industryRepository;

    public IndustryService(GlobalImageService globalImageService, IndustryRepository industryRepository) {
        this.globalImageService = globalImageService;
        this.industryRepository = industryRepository;
    }

    @Override
    protected JpaRepository<Industry, String> getRepository() {
        return industryRepository;
    }

    @Override
    public List<User> getUserByRole(String name) {
        return null;
    }

    public Industry getIndustryById(String id) {
        return industryRepository.findIndustriesById(id);
    }

    public int getIndustryIdByName(String name){
        Industry industry = industryRepository.findByName(name);

        log.info("industryCode: {}", industry.getCode());
        return industry.getCode();
    }

    @Transactional(value = "transactionManager")
    public String addIndustry(MultipartFile file, String name, int code) {
        Industry industry = new Industry();
        String imageUrl= globalImageService.saveFile(file);

        industry.setName(name);
        industry.setCode(code);
        industry.setImageUrl(imageUrl);

        industryRepository.save(industry);
        return imageUrl;
    }
}
