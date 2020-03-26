package rovoq19.contacts.reps;

import org.springframework.data.jpa.repository.JpaRepository;
import rovoq19.contacts.domain.Contact;

public interface ContactRep extends JpaRepository<Contact, Long> {
}
