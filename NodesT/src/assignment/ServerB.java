package assignment;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerB {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5001)) { 
            System.out.println("ServerB is listeaning to port 5001");

            while (true) {
               
                Socket serverSocketB = serverSocket.accept();

                InputStream in = serverSocketB.getInputStream();
                int receivedNumber = in.read(); 
                System.out.println("ServerB received a number! " );

          
                serverSocketB.close();
        

              
                try (Socket socketB = new Socket("localhost", 6001)) {
                    OutputStream os = socketB.getOutputStream();
                    os.write(receivedNumber); 
                    os.flush();

                    System.out.println("ServerB forwarded the number to Server C ");
                    os.close();
                } catch (Exception ex) {
                    System.out.println("Error sending to ServerC: " + ex.getMessage());
                }
            }
        } catch (Exception ex) {
            System.out.println("Error in ServerB: " + ex.getMessage());
        }
    }
}
