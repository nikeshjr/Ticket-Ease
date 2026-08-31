package project.practice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.server.ResponseStatusException;
import project.practice.dto.TicketDto;
import project.practice.entity.Ticket;
import project.practice.repository.TicketRepository;

import java.util.Optional;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor

public class TicketService {

    private final TicketRepository ticketRepository;

    public ResponseEntity<Page<TicketDto>> getAllTickets(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TicketDto> dtos = ticketRepository.findAll(pageable).map(this::mapDto);
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    public ResponseEntity<TicketDto> getTicketById(UUID id) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);

        if (optionalTicket.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(mapDto(optionalTicket.get()));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found with id: " + id);
        }
    }

    public ResponseEntity<TicketDto> createTicket(TicketDto ticketDto) {
        Ticket ticket = Ticket.builder()
                .eventname(ticketDto.getEventname())
                .build();
        Ticket savedTicket = ticketRepository.save(ticket);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapDto(savedTicket));
    }

    public ResponseEntity<TicketDto> updateTicket(UUID id, TicketDto updatedDto) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isPresent()) {
            ticket.get().setEventname(updatedDto.getEventname());
            ticketRepository.save(ticket.get());
            return ResponseEntity.status(HttpStatus.OK).body(mapDto(ticket.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public ResponseEntity<Void> deleteTicket(UUID id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isPresent()) {
            ticketRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    private TicketDto mapDto(Ticket ticket) {
        return new TicketDto(
                ticket.getId(),
                ticket.getEventname());
    }
}