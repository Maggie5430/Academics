import java.net.*;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Margaret Connor
 * CSS430 HW3
 *
 * This is a DateServer which creates a pool of thread and uses it for requests
 */
public class DateServerPool {

    public static void main(String[] args) {
        try {
            /* A thread pool that creates new threads as needed, but will reuse 
             * previously constructed threads when they are available */
            ExecutorService pool = Executors.newCachedThreadPool();
            //ExecutorService pool = Executors.newFixedThreadPool(2); // testing
            ServerSocket sock = new ServerSocket(6013);
            
            //listen for new connections
            while (true) {
                Thread newThread = new Thread(new DateThread(sock.accept()));
                pool.execute(newThread);
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
            //close socket
            myClient.close();
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
    }
}
