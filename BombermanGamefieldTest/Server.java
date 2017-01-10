/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asus
 */
public class Server {
    private List <User> playingUsers;
    int [][]bombeLegen=new int[2][2];
    
    public class User{
        public String name;
        public String farbe;
        public OutputStream out;
        public InputStream in;
        public Socket socket;
    public User (String name,String farbe,Socket socket){
        this.name = name;
        this.farbe = farbe;
        this.socket = socket;
    try{
        this.out = socket.getOutputStream();
        this.in = socket.getInputStream();
    }
     catch(IOException ex){
         Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
     }
    
    }
    }
        
    }
 
    
    

