package com.huyenho.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class DictionaryController {
    Map<String, String> dictionary = new HashMap<String, String>() {{
        put("hi", "Xin chào");
        put("eat", "Ăn");
        put("learn", "Học");
        put("agreement", "Thoả thuận");
    }};

    @GetMapping("/dictionary")
    public ResponseEntity<String> getDictionary(@RequestParam String word) {
        String result = dictionary.get(word.trim().toLowerCase());
        return result != null ?
                new ResponseEntity<>(result, HttpStatus.OK) :
                new ResponseEntity<>("Từ vựng này chưa được thêm vào từ điển", HttpStatus.NOT_FOUND);
    }
}
