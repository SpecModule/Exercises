package com.huyenho.demo.repository;

import com.huyenho.demo.emtity.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDictionaryRepository extends JpaRepository<Dictionary, Integer> {
    Dictionary findByEnglish(String word);
}