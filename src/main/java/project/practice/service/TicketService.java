package project.practice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import project.practice.dto.TicketDto;
import project.practice.entity.Ticket;
import project.practice.repository.TicketRepository;

import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class TicketService {

    private final TicketRepository ticketRepository;

    public ResponseEntity<List<TicketDto>> getAllTickets() {
        List<TicketDto> dtos = ticketRepository.findAll().stream().map(this::mapDto).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    public ResponseEntity<TicketDto> getTicketById(Long id) {
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

    private TicketDto mapDto(Ticket ticket) {
        return new TicketDto(
                ticket.getId(),
                ticket.getEventname());
    }
}