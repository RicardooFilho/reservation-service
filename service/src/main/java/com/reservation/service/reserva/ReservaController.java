package com.reservation.service.reserva;

import com.reservation.service.reserva.dto.ReservaDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService service;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid ReservaDTO reserva) {

        final Reserva saved = service.save(reserva);
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Reserva>> findByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(service.findByUserId(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
