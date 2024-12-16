package com.huyenho.demo.controller;

import com.huyenho.demo.entity.Dictionary;
import com.huyenho.demo.service.IDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class DictionaryController {
    @Autowired
    IDictionaryService dictionaryService;

    @GetMapping("/dictionary")
    public ResponseEntity<String> getDictionary(@RequestParam String word) {
        Dictionary result = dictionaryService.getDictionary(word.trim().toLowerCase());
        return result != null ?
                new ResponseEntity<>(result.getVietNam(), HttpStatus.OK) :
                new ResponseEntity<>("Từ vựng này chưa được thêm vào từ điển", HttpStatus.NOT_FOUND);
    }
}
