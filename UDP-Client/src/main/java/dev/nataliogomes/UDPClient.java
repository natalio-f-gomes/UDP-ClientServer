package dev.nataliogomes;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class UDPClient {
    private String message;
    private byte[] messageBytes;
    private InetAddress serverIPAddress;
    private final int serverPORT = 9001;
    private byte[] receivedData;
    private DatagramSocket clientSocket;
    private String serverResponse;
    public UDPClient(){

        receivedData = new byte[1024];
        try{
            clientSocket = new DatagramSocket();
            serverIPAddress = InetAddress.getByName("localhost");

        }catch (IOException e){
            System.out.println(e);
            System.exit(1);
        }

    }

    public void sendPacketToServer(){
        try{
            DatagramPacket sendPacket = new DatagramPacket(messageBytes,messageBytes.length, serverIPAddress, serverPORT);
            clientSocket.send(sendPacket);
        }catch (IOException e){
            System.out.println(e);
            System.exit(1);
        }
    }

    public void receivePacketFromServer(){
        try{
            DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
            clientSocket.setSoTimeout(1000);
            clientSocket.receive(receivedPacket);
            serverResponse = new String(
                    receivedPacket.getData(), 0, receivedPacket.getLength()
            );
            System.out.println("FROM SERVER: " + serverResponse);
        }catch (IOException e){
            System.out.println(e);
            System.exit(1);
        }

    }
    public void runClient(){
        while (true){
            Scanner scanner = new Scanner(System.in);
            System.out.print("Please enter your message: ");
            message = scanner.nextLine();
            messageBytes = message.getBytes();
            sendPacketToServer();
            receivePacketFromServer();
            if (serverResponse.equalsIgnoreCase("exit")){
                break;
            }
        }
        System.out.println("Connection Closed!");
        clientSocket.close();
    }

}
