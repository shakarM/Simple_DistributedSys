package assignment;

import java.net.ServerSocket;
import java.net.Socket; 
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

public class DataTrans {
    public static void main(String[] args) {
        try (Socket socketA = new Socket("localhost", 5001)) { 
            Random random = new Random();
            int number = random.nextInt(100);

           
            OutputStream dos = socketA.getOutputStream();
    		
            dos.write(number);
            dos.flush();
            
            System.out.println("DataTrans sends a random number to server B");
            
            try(ServerSocket serverSocket = new ServerSocket(8001)){
            	 while (true) {
                     
                     Socket serverSocketA = serverSocket.accept();

                     InputStream in = serverSocketA.getInputStream();
                     int receivedNumber = in.read(); 
                     System.out.println("Server A received the number from Server D which is " + receivedNumber);

               
                     serverSocketA.close();
         
            }} catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
