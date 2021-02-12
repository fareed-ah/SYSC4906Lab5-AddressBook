package addressbook.model;

import javax.persistence.*;
import java.util.*;

@Entity
public class AddressBook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = null;

    @OneToMany(cascade = CascadeType.ALL)
    private List<BuddyInfo> friends;

    public  AddressBook(){
        friends = new ArrayList<>();
    }

    public  AddressBook(List<BuddyInfo> friends){
        this.friends = friends;
    }

    public void addBuddy(BuddyInfo friend){
        friends.add(friend);
    }

    public void removeBuddy(BuddyInfo friend){
        friends.remove(friend);
    }

    public List<BuddyInfo> getFriends() {
        return friends;
    }

    @Override
    public String toString() {
        String output = "";
        output += "AddressID:"+id +
                "\nBuddies:\n ";
        if(getFriends().isEmpty()){
            output+="Add a friend!\n";
        }
        for( BuddyInfo info: getFriends()){
            output += info +"\n";
        }
        return output ;
    }
}