package com.huyenho.demo.service.impl;

import com.huyenho.demo.emtity.Dictionary;
import com.huyenho.demo.repository.IDictionaryRepository;
import com.huyenho.demo.service.IDictionaryService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DictionaryService implements IDictionaryService {
    @Autowired
    IDictionaryRepository dictionaryRepository;

    @Override
    public Dictionary getDictionary(String word) {
        return dictionaryRepository.findByEnglish(word);
    }
}
