package addressbookworkshop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class AddressBookService
{
    public HashMap<Integer,Contact> contacts = new HashMap<>();

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Contact> contactlist = new ArrayList<>();
    private Map<String, ArrayList<Contact>> AddressBooks = new HashMap<>();
    public static String addressBookFile = "AddressBookFile.txt";



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

        System.out.println("Contact Added Successfully");
        System.out.println("Enter Book name to which you have to add contact");
        String bookName  = scanner.next();
        if(AddressBooks.containsKey(bookName))
        {
        	contactlist.stream().filter(value -> value.getFirstName(). equals(contact.getFirstName())).forEach(value -> 
    	    {
    	    	System.out.println("Duplicate Contact");
    	    	addNewContact();
    	    });            contactlist.add(contact);
            AddressBooks.put(bookName,contactlist);
            System.out.println("New Contact Added Successfully");
        }
        else
        {
            contactlist.add(contact);
            AddressBooks.put(bookName,contactlist);
            System.out.println("New book created and added Contact Added Successfully");
        }

    }
    public void writeToFile()
    {
        StringBuffer addressBuffer = new StringBuffer();
        contactlist.forEach(address -> { String addressDataString = address.toString().concat("\n");addressBuffer.append(addressDataString);});
        try
        {
            Files.write(Paths.get(addressBookFile),addressBuffer.toString().getBytes(StandardCharsets.UTF_8));
            System.out.println("Data successfully written to file.");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void readDataFromFile()
    {
        try
        {
            System.out.println("Reading Data From File :");
            Files.lines(new File(addressBookFile).toPath()).map(line -> line.trim()).forEach(line -> System.out.println(line));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
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
    public void searchPersonByCity (String city)
    {
        System.out.println("following are the persons who belongs to :" + city);
        for(String bookName : AddressBooks.keySet())
        {
            AddressBooks.get(bookName);
            contactlist.stream().filter(value -> value.getCity().equals(city)).map(Contact::getFirstName).forEach(System.out::println);
        }
    }
    public void searchPersonByState (String state)
    {
        System.out.println("following are the persons who belongs to :" + state);
        for(String bookName : AddressBooks.keySet())
        {
            AddressBooks.get(bookName);
            contactlist.stream().filter(value -> value.getState().equals(state)).map(Contact::getFirstName).forEach(System.out::println);
        }
    }
    public void viewPersonByCity (String city)
    {
        for(String bookName : AddressBooks.keySet())
        {
            int countPerson = 0;
            AddressBooks.get(bookName);
            contactlist.stream().filter(value -> value.getCity().equals(city)).map(Contact::getFirstName).forEach(System.out::println);
            countPerson++;
            System.out.println("total persons:"+countPerson);
        }
    }
    public void viewPersonByState (String state)
    {
        for(String bookName : AddressBooks.keySet())
        {
            int countPerson = 0;
            AddressBooks.get(bookName);
            contactlist.stream().filter(value -> value.getState().equals(state)).map(Contact::getFirstName).forEach(System.out::println);
            countPerson++;
            System.out.println("total persons:"+countPerson);
        }
    }
    public void sortByFirstName() {
		System.out.println(AddressBooks.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toList()));
	}
    public void sortByCity() {
        AddressBooks.keySet().forEach((String key) -> {
            AddressBooks.get(key).stream()
                    .sorted(Comparator.comparing(Contact::getCity))
                    .collect(Collectors.toList())
                    .forEach(person -> System.out.println(person.toString()));
        });
    }
    public void displayList() 
    {
        for (Contact iterator : contactlist) System.out.println(iterator);
    }
    
}
