package pl.sdaacademy.conferenceroomreservationsystem.test;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface TodoRepository extends JpaRepository<Todo, Integer> {

    Optional<Todo> findByName(String name);
}
