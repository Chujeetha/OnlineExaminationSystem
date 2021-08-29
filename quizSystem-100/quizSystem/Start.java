package quizSystem;
import java.util.Scanner;
public class Start {
    public static void main(String [] args)
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the url : ");
        String url = input.nextLine();
        System.out.print("Enter the port number : ");
        int port = input.nextInt();
        Client client = new Client(url,port);

    }
}
