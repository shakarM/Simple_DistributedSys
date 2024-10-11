package assignment;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerC {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(6001)
        		
        		) { 
            System.out.println("ServerC listening on port 6001");

            while (true) {
           
            	 Socket serverSocketC = serverSocket.accept();

              
               InputStream in = serverSocketC.getInputStream();
               int receivedNumber = in.read(); 
               System.out.println("ServerB received a number! ");
                
                serverSocketC.close();
                
                try(Socket socketC = new Socket("localhost", 7001)) {
                	OutputStream os = socketC.getOutputStream();
                	os.write(receivedNumber);
                	os.flush();
                	  System.out.println("ServerC forwarded the number to Server D " );
                	os.close();
                	
                } catch(Exception e) {
                	System.out.println(e);
                }
                
            }
        } catch (Exception e) {
            System.out.println( e);
        }
    }
}
