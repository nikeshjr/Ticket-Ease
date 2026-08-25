package project.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.practice.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
