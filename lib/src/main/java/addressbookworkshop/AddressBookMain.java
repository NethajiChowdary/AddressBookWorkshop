package addressbookworkshop;

import java.util.List;
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
            System.out.println("\nEnter options\n 1.Add Contact.\n 2.Edit Contact.\n 3.Delete Contact.\n 4.Display Contact."
            		+ "\n 5.Search by City.\n 6.Search by State.\n 7.View by City.\n 8.View by State."
            		+ "\n 9.Sort By Name.\n 10.Sort By City.\n 11.Read From File.\n 12.Write From File."
            		+ "\n 13.Write To CSV.\n 14.Read From CSV.\n 15.Write to JSON.\n 16.Read From JSON.\n 17.Exit.");
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
                    System.out.println("Enter City Name to view person by city:");
                    String viewcity = scanner.next();
                	service.viewPersonByCity(viewcity);
                case 8:
                	System.out.println("Enter State Name to view person by state :");
                    String viewstate = scanner.next();
                	service.viewPersonByState(viewstate);
                case 9:
                	service.sortByFirstName();
                case 10:
                	service.sortByCity();
                case 11:
                	service.writeToFile();
                case 12:
                	service.readDataFromFile();
                case 13:
                	service.writeToCsv();
                case 14:
                	service.readFromCsvFile();
                case 15:
                	service.writeToJson();
                case 16:
                	service.readFromJson();
                case 17:
                    isExit = true;
                    break;
                default:
                    System.out.println("Please enter valid input");
            }
        }
    }

	public AddressBookMain getInstance() {
		return null;
	}

	public List<Contact> readData() {
		return null;
	}
}