package com.reservation.service.reserva;

import com.reservation.service.book.BookFeign;
import com.reservation.service.book.StatusBook;
import com.reservation.service.book.dto.BookPatchRequestDTO;
import com.reservation.service.reserva.dto.ReservaDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository repository;
    private final BookFeign bookFeign;

    public Reserva save(final ReservaDTO reserva) throws EntityNotFoundException {

        final Boolean isDisponivel = bookFeign.isDisponivelByIsbn(reserva.isbn());

        if (!isDisponivel) {
            throw new IllegalArgumentException("Livro indisponivel");
        }

        bookFeign.patchStatus(reserva.isbn(), new BookPatchRequestDTO(StatusBook.RESERVADO));
        return repository.save(Reserva.fromDTO(reserva));
    }

    public List<Reserva> findByUserId(final Long userId) {
        return repository.findByUserId(userId);
    }

    public void delete(final Long id) throws EntityNotFoundException {
        final Reserva reserva = findById(id);
        reserva.inative();
        bookFeign.patchStatus(reserva.getIsbn(), new BookPatchRequestDTO(StatusBook.DISPONIVEL));
        repository.save(reserva);
    }

    private Reserva findById(final Long id) {
        return repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}
