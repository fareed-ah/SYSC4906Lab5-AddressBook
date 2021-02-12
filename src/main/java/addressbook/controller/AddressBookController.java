package addressbook.controller;

import addressbook.model.AddressBook;
import addressbook.model.BuddyInfo;
import addressbook.repositories.AddressBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class AddressBookController {

    @Autowired
    private AddressBookRepository addressBookRepo;

    @GetMapping("/")
    public String homePage(Model model) {
        List<AddressBook> addressBooks = (List<AddressBook>) addressBookRepo.findAll();
        model.addAttribute("addressBooks", !addressBooks.isEmpty()?addressBooks.get(0):null);
        model.addAttribute("buddyInfo", new BuddyInfo());
        return "homepage";
    }

    @PostMapping("/addBuddyInfo")
    public String addBuddyInfo(@ModelAttribute BuddyInfo buddyInfo, Model model) {
        List<AddressBook> addressBooks = (List<AddressBook>) addressBookRepo.findAll();

        if(addressBooks.isEmpty()){
            addressBooks.add(new AddressBook());
        }

        addressBooks.get(0).addBuddy(buddyInfo);
        addressBookRepo.save(addressBooks.get(0));
        model.addAttribute("addressBooks", addressBooks);
        return "homepage";
    }
}
