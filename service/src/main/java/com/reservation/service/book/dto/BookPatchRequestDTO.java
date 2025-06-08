package com.reservation.service.book.dto;

import com.reservation.service.book.StatusBook;

public record BookPatchRequestDTO(
        StatusBook status
) {
}
