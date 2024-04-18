package ru.shulenin.simple_messanger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shulenin.simple_messanger.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
