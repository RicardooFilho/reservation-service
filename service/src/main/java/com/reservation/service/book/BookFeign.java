package com.reservation.service.book;

import com.reservation.service.book.dto.BookPatchRequestDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "BookFeign", url = "http://localhost:8080")
public interface BookFeign {

    @GetMapping("/api/books/disponivel/{isbn}/isbn")
    Boolean isDisponivelByIsbn(@PathVariable String isbn) throws EntityNotFoundException;

    @PutMapping("/api/books/isbn/{isbn}")
    void patchStatus(@PathVariable("isbn") String isbn, @RequestBody BookPatchRequestDTO request) throws EntityNotFoundException;
}
