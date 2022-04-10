package ru.battalovasadyrov.meach.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.battalovasadyrov.meach.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
