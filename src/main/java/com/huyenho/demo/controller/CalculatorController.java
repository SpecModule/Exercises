package com.huyenho.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CalculatorController {

    @GetMapping("/calculator")
    public ResponseEntity<Object> calculator(@RequestParam String firstNumber,
                                             @RequestParam String secondNumber,
                                             @RequestParam String operator) {
        float firstNumberFloat;
        float secondNumberFloat;
        float result;

        try {
            firstNumberFloat = Float.parseFloat(firstNumber);
            secondNumberFloat = Float.parseFloat(secondNumber);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>("Vui lòng nhập số hợp lệ!", HttpStatus.BAD_REQUEST);
        }

        switch (operator) {
            case "Cộng":
                result = firstNumberFloat + secondNumberFloat;
                break;
            case "Trừ":
                result = firstNumberFloat - secondNumberFloat;
                break;
            case "Nhân":
                result = firstNumberFloat * secondNumberFloat;
                break;
            case "Chia":
                if (secondNumberFloat == 0.0f) {
                    return new ResponseEntity<>("Không thể chia cho 0!", HttpStatus.BAD_REQUEST);
                }
                result = firstNumberFloat / secondNumberFloat;
                break;
            default:
                return new ResponseEntity<>("Phép toán không hợp lệ!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
