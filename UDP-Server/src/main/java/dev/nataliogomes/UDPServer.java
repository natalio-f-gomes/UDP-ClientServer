package dev.nataliogomes;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;

public class UDPServer {
    private DatagramSocket serverSocket;

    private DatagramPacket receivedPacket;

    private final byte[] receivedData;
    private final int PORT = 9001;
    private String messageFromClient;
    private InetAddress clientIPAddress;
    private int clientPort;
    private SocketAddress clientSocketAddress;
    public UDPServer(){
        this.receivedData = new byte[1024];
        try{
            this.serverSocket = new DatagramSocket(PORT);
            System.out.println("UDP SERVER IS UP AND RUNNING");
        }
        catch (java.net.SocketException e){
            System.out.println(e);
            System.exit(1);

        }
    }
    public void run(){
        while(true){
            try{
                receivedPacket = new DatagramPacket(receivedData, receivedData.length);
                serverSocket.receive(receivedPacket);
                messageFromClient = new String(
                        receivedPacket.getData(),0, receivedPacket.getLength()
                );
                clientIPAddress = receivedPacket.getAddress();
                clientPort = receivedPacket.getPort();
                clientSocketAddress = receivedPacket.getSocketAddress();
                printClientInfo();
                sendPacketToClient();
                if(messageFromClient.equalsIgnoreCase("exit")){
                    break;
                }

            }
            catch (IOException e){
                System.out.println(e);
                System.exit(1);
            }

        }
    }
    private void printClientInfo(){
        System.out.println("Client's IP Address: " + this.clientIPAddress);
        System.out.println("Client's PORT: " + this.clientPort);
        System.out.println("Client's Socket Address: " + this.clientSocketAddress);
        System.out.println("Client's message: " + this.messageFromClient);
    }

    public void sendPacketToClient(){

        String messageToClient = this.messageFromClient.toUpperCase();
        byte[] data = messageToClient.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(data, data.length, clientIPAddress, clientPort);
        try{
            this.serverSocket.send(sendPacket);
        }catch (IOException e){
            System.out.println(e);
            System.exit(1);
        }

    }


}
