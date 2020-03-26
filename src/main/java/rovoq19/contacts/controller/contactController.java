package rovoq19.contacts.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rovoq19.contacts.domain.Contact;
import rovoq19.contacts.reps.ContactRep;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("contact")
public class contactController {
    private final ContactRep contactRep;

    @Autowired
    public contactController (ContactRep contactRep) {
        this.contactRep = contactRep;
    }

    @GetMapping
    public List <Contact> list(){
        return contactRep.findAll ();
    }

    @GetMapping("{id}")
    public Contact getContactId(@PathVariable("id") Contact contact){
        return contact;
    }

    @PostMapping
    public Contact addContact(@RequestBody Contact contact){
        contact.setCreationDate(LocalDateTime.now ());
        return contactRep.save(contact);
    }

    @PutMapping("{id}")
    public Contact updContact(@PathVariable("id") Contact contactData, @RequestBody Contact contact){
        BeanUtils.copyProperties (contact, contactData, "id");

        return contactRep.save (contactData);
    }

    @DeleteMapping("{id}")
    public void delContact(@PathVariable("id") Contact contact){
        contactRep.delete (contact);
    }
}
