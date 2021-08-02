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
            System.out.println("Enter options \n 1.Add Contact.\n 2.Edit Contact.\n 3.Display Contact.\n 4.Exit.");
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
                    service.displayList();
                    break;
                case 4:
                    isExit = true;
                    break;
                default:
                    System.out.println("Please enter valid input");
            }
        }
    }
}