package com.reservation.service.reserva.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ReservaDTO(

        @NotNull(message = "UserID obrigatório")
        Long userId,

        @NotNull(message = "ISBN obrigatório")
        @Size(max = 255, message = "Máximo 255 caracteres")
        String isbn

) {
}
