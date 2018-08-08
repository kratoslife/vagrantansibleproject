import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

class NewThread extends Thread {
    protected Socket socket;

    public NewThread(Socket clientSocket) {
        this.socket = clientSocket;
    }

    public void run() {
        boolean isRunning = true;
        try
        {
            while (isRunning) {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                out.println("HTTP/1.1 200 "); 
                out.println("Content-Type: text/html"); 
                out.println("Connection: close"); 
                out.println("");
                out.println("<!DOCTYPE html>");
                out.println("<h1>Welcome... wait 5 sec...</h1>\r\n");
                String line;
                  while ((line = in.readLine()) != null) {
                        if (line.length() == 0)
                          break;
                      }
                out.flush();
                Thread.sleep(60000);
                out.close();
                HomeWork.connection_counter--;
                isRunning = false;
            }
        }
        catch(Exception e)
        {
            System.out.println("Error" + e);
        }
    }
}

class Socket0 implements Runnable
{
    public void run()
    {
        try {
            ServerSocket ss = new ServerSocket(5555);
            for (;;) {
              Socket client = ss.accept();
              BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
              PrintWriter out = new PrintWriter(client.getOutputStream());
              out.print("HTTP/1.1 200 \r\n"); 
              out.print("Content-Type: text/plain\r\n"); 
              out.print("Connection: close\r\n"); 
              out.print("\r\n");
              String line;
              out.print("Counter: " + HomeWork.connection_counter + "\r\n");
              while ((line = in.readLine()) != null) {
                if (line.length() == 0)
                  break;
              }
              out.close();
              in.close();
              client.close();
            }
          }
          catch (Exception e) {
            System.err.println(e);
          }
    }
}

public class HomeWork {
    public static int connection_counter = 0;
    public static void main(String args[]) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(8888, 0, InetAddress.getByName(null));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Service is started on local port: 127.0.0.1:8888, and monitoring port: 5555");
        Socket0 s0=new Socket0();
        Thread t1=new Thread(s0);
        t1.start();

        while (true) {
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
            connection_counter++;
            new NewThread(socket).start();
            System.out.println("Connections: " + connection_counter);
        }
    }
}