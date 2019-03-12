import java.io.BufferedReader;
import java.io.InputStreamReader;

class Bank
{
    public static void main(String args[]) 
    {
        String str;
        int choice;
        choice=0;

        BankManageTest bankManageTest = new BankManageTest();

        do
        {
        System.out.println("Choose Your Choices ...");
        System.out.println("1) New Customer Entry ");
        System.out.println("2) Display Record Details ");
        System.out.println("3) Transaction...");
        System.out.println("4) Exit");
        System.out.print("Enter your choice :  ");
        System.out.flush();
              try{
                   BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
                   str=obj.readLine();
                   choice=Integer.parseInt(str);

                          switch(choice)
                           {
                            case 1 :  //New customer Entry
                            		bankManageTest.newEntry();
                                    break;
                            case 2 :  //Displaying Record Details
                            		bankManageTest.display();
                                    break;
                            case 3 : //Transaction
                            		bankManageTest.transaction();
                                    break;
                            
                            case 4  :  System.out.println("exit");
                                            break;
                            default : System.out.println("\nInvalid Choice \n\n");
                          }
                    }
                catch(Exception e)
                {}
            }while(choice!=4);
    }
}