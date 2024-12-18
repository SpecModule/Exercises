package com.huyenho.demo.dto;

import com.huyenho.demo.dto.employee.EmployeeResponse;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;

@Data
public class CustomPage<T> {
    List<T> content;
    CustomPageable pageable;

    public CustomPage(Page<T> page) {
        this.content = page.getContent();
        this.pageable = new CustomPageable(page.getPageable().getPageNumber(),
                page.getPageable().getPageSize(), page.getTotalPages());
    }


    @Data
    class CustomPageable {
        int pageNumber;
        int pageSize;
        int totalPages;

        public CustomPageable(int pageNumber, int pageSize, int totalPages) {
            this.pageNumber = pageNumber;
            this.pageSize = pageSize;
            this.totalPages = totalPages;
        }
    }
}
