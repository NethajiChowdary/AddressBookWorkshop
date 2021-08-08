package addressbookworkshop;

import java.util.Scanner;

public class AddressBookMain 
{
     static AddressBookService service = new AddressBookService();

    public static void main(String[] args)
    {
        boolean isExit = false;
        while (!isExit)
        {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nEnter options\n 1.Add Contact.\n 2.Edit Contact.\n 3.Delete Contact.\n 4.Display Contact.\n 5.Search by City.\n 6.Search by State.\n 7.Exit.");
            int userInput = scanner.nextInt();
            switch (userInput)
            {
                case 1:
                    service.addNewContact();
                    break;
                case 2:
                    service.editContact();
                    break;
                case 3:
                    System.out.println("Enter the name of the person do you wants to delete:");
                    String personName = scanner.next();
                    service.deleteContact(personName);
                    break;
                case 4:
                    service.displayList();
                    break;
                case 5:
                    System.out.println("Enter City Name to search person by city:");
                    String searchcity = scanner.next();
                	service.searchPersonByCity(searchcity);
                	
                case 6:
                    System.out.println("Enter State Name to search person by state :");
                    String searchstate = scanner.next();
                	service.searchPersonByState(searchstate);
                case 7:
                    isExit = true;
                    break;
                default:
                    System.out.println("Please enter valid input");
            }
        }
    }
}