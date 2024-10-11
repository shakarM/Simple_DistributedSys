package assignment;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerD {

	public static void main(String[] args) {
try (ServerSocket serverSocket = new ServerSocket(7001)
        		
        		) { 
            System.out.println("ServerC listening on port 7001");

            while (true) {
           
            	 Socket serverSocketC = serverSocket.accept();

              
               InputStream in = serverSocketC.getInputStream();
               int receivedNumber = in.read(); 
               System.out.println("ServerB received: " + receivedNumber);
                
                serverSocketC.close();
                
                try(Socket socketC = new Socket("localhost", 8001)) {
                	OutputStream os = socketC.getOutputStream();
                	os.write(receivedNumber);
                	os.flush();
                	  System.out.println("Server D forwarded the number to Server A ");
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
