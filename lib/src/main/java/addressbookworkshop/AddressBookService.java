package addressbookworkshop;

import java.util.ArrayList;
import java.util.Scanner;

public class AddressBookService
{
    static Scanner scanner = new Scanner(System.in);
    ArrayList<Contact> contactlist = new ArrayList<>();

    public void addNewContact()
    {
        Contact contact = new Contact();
        System.out.println("Enter First name:");
        contact.setFirstName(scanner.next());

        System.out.println("Enter Last Name:");
        contact.setLastName(scanner.next());

        System.out.println("Enter Address:");
        contact.setAddress(scanner.next());

        System.out.println("Enter City:");
        contact.setCity(scanner.next());

        System.out.println("Enter State:");
        contact.setState(scanner.next());

        System.out.println("Enter Zip:");
        contact.setZip(scanner.next());
        
        System.out.println("Enter Phone:");
        contact.setPhoneNumber(scanner.next());

        System.out.println("Enter Email:");
        contact.setEmail(scanner.next());

        contactlist.add(contact);
        System.out.println("Contact Added Successfully");
    }
    public void editContact()
    {
        String enteredFirstName;
        System.out.println("Enter First name of contact to edit it ");
        enteredFirstName = scanner.next();
        for (int i = 0; i < contactlist.size(); i++) 
        {
            if (contactlist.get(i).getFirstName().equals(enteredFirstName))
            {
                System.out.println("Enter the field to edit:\n1.First Name\n2.Last Name\n3.Address\n4.city\n5.State\n6.Zip\n7.Phone\n8.Email");
                int userInput = scanner.nextInt();
                switch (userInput)
                {
                    case 1:
                        System.out.println("Enter new first name");
                        contactlist.get(i).setFirstName(scanner.next());
                        break;
                    case 2:
                        System.out.println("Enter new last name");
                        contactlist.get(i).setLastName(scanner.next());
                        break;
                    case 3:
                        System.out.println("Enter new Address");
                        contactlist.get(i).setAddress(scanner.next());
                        break;
                    case 4:
                        System.out.println("Enter new city");
                        contactlist.get(i).setCity(scanner.next());
                        break;
                    case 5:
                        System.out.println("Enter new state");
                        contactlist.get(i).setState(scanner.next());
                        break;
                    case 6:
                        System.out.println("Enter new zip");
                        contactlist.get(i).setZip(scanner.next());
                        break;
                    case 7:
                        System.out.println("Enter new phone number");
                        contactlist.get(i).setPhoneNumber(scanner.next());
                        break;
                    case 8:
                        System.out.println("Enter new email");
                        contactlist.get(i).setEmail(scanner.next());
                        break;
                    default:
                        System.out.println("Invalid Entry");
                        
                }     
            }
        }
        System.out.println("Contact Edited Successfully");
    }
    
    public void deleteContact(String name)
    {
        for (int i = 0; i < contactlist.size(); i++) 
        {
            if (contactlist.get(i).getFirstName().equals(name)) 
            {
                Contact contact = contactlist.get(i);
                contactlist.remove(contact);
            }
        }
        System.out.println("Contact Deleted Successfully");
    }
    public void displayList() 
    {
        for (Contact iterator : contactlist) System.out.println(iterator);
    }

}
