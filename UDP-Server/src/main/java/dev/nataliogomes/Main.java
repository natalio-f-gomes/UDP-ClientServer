package dev.nataliogomes;

import java.io.IOException;
import java.net.SocketException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {

        UDPServer udpServer = new UDPServer();
        udpServer.run();
    }
}
