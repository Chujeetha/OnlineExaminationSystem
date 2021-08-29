package quizSystem;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
public class ServerStart {
    ServerSocket serverSocket;
    Socket socket;
    //ObjectOutputStream oout;
    //ObjectInputStream oin;
    public ServerStart()
    {
        createServer();
        runServer();
    }
    public void createServer()
    {
        try {
            serverSocket = new ServerSocket(6666);
        }
        catch (Exception e)
        {
            System.out.println("Server start - createServer : "+e);
        }
    }
    public void runServer()
    {
        while (true) {
            try {
                System.out.println("@-runServer");
                socket = serverSocket.accept();
                new Server(socket).start();

            } catch (Exception e) {
                System.out.println("Server start - runServer : " + e);
            }
        }
    }
    public void exitServer()
    {
        try
        {
            serverSocket.close();
        }
        catch (Exception e)
        {
            System.out.println("Server start - exitServer : "+e);
        }
    }
    public static void main(String [] args)
    {
        new ServerStart();
    }
}
