package com.reservation.service.reserva;

import com.reservation.service.reserva.dto.ReservaDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private Long userId;

    @NotNull
    @Size(max = 255)
    private String isbn;

    private LocalDate dataReserva = LocalDate.now();

    @Column(length = 10)
    @Enumerated(value = EnumType.STRING)
    private Status status = Status.ATIVA;

    public static Reserva fromDTO(final ReservaDTO reserva) {
        return new Reserva(null, reserva.userId(), reserva.isbn(), LocalDate.now(), Status.ATIVA);
    }

    public void inative() {
        status = Status.INATIVA;
    }
}


