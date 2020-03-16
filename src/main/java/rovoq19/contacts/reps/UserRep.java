package rovoq19.contacts.reps;

import org.springframework.data.jpa.repository.JpaRepository;
import rovoq19.contacts.domain.User;

public interface UserRep extends JpaRepository<User, Long> {
}
