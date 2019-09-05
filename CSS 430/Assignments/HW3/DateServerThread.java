import java.net.*;
import java.io.*;

/**
 * Margaret Connor
 * CSS430 HW3
 *
 * This is a DateServer which creates a new thread for all requests
 */
public class DateServerThread {

    public static void main(String[] args) {
        try {
            ServerSocket sock = new ServerSocket(6013);
            //listen for connections
            while (true) {
                Thread newThread = new Thread(new DateThread(sock.accept()));
                newThread.start();
                /* I don' tuse newThread.join() here because I want the server
                 * to be able to process multiple request at the same time */
            }
        } catch (Exception ioe) {
            System.err.println(ioe);
        }
    }
}

/**
 * Runnable thread class to perform task
 */
class DateThread implements Runnable {

    Socket myClient;

    public DateThread(Socket client) {
        this.myClient = client;
    }

    public void run() {
        try {
            //write date to socket
            PrintWriter pout = new PrintWriter(myClient.getOutputStream(), true);
            pout.println(new java.util.Date().toString());
            //Thread.sleep(5000); //for testing purposes
            //close socket
            myClient.close();
        } catch (Exception ioe) {
            System.err.println(ioe);
        }
    }
}
