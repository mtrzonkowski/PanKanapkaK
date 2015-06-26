/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pankanapkas;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author mtrzonkowski
 */
public class ServerUDP implements Runnable{

    private int port=9876;
    private boolean working=true;
    private String slimak="slimak.png";
    
    private DatagramSocket serverSocket;
    private DatagramSocket clientSocket;
    private DatagramPacket recivePacket;
    private DatagramPacket sendPacket;
    private InetAddress ipAddress;
    private int answerPort;
    
    byte[]reciveData= new byte[1024];
    byte[]sendData = new byte[8192];
    
    @Override
    public void run() {
        try {
            serverSocket=new DatagramSocket(port);
            while(true){
                recivePacket=new DatagramPacket(reciveData, reciveData.length);
                serverSocket.receive(recivePacket);
                ipAddress=recivePacket.getAddress();
                answerPort=recivePacket.getPort();
                String zdanie=new String(recivePacket.getData());
                
                BufferedImage img = ImageIO.read(new File(slimak));
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(img, "png", baos);
                baos.flush();
                byte[] buffer = baos.toByteArray();
                
                clientSocket=new DatagramSocket();
                sendPacket=new DatagramPacket(buffer, buffer.length, ipAddress, answerPort);
                clientSocket.send(sendPacket);
                
            }
        } catch (SocketException ex) {
            Logger.getLogger(ServerUDP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServerUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    }
    
}
