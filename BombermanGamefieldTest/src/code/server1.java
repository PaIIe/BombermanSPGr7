/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
/**
 *
 * @author Paul
 */


public class Server{
    private int port;
    private boolean open = true;
    //die Stauts von Verbindung ist true
    private ServerSocket ss;
    private ServerListener serverListener;
    //verbindt Client und Information empfangen
    private ArrayList<Socket> clients = new ArrayList<>();
    // List von Client
        
    public Server(int port, ServerListener listener){
	serverListener=listener;
	try{
            ss=new ServerSocket(port);
            if(this.port==0)    this.port=ss.getLocalPort();
            else    this.port=port;
            Thread serverThread = new Thread(new Runnable(){
                //neue Thread aufbauen
		public void run(){
                    while(open){
			try{
                            final Socket s = ss.accept();
                            //Server Socket emfangt Client Verbindung
                            Thread clientThread = new Thread(new Runnable(){
                                //neue Thread aufbauen
				public void run(){
                                    try{
                                                    clients.add(s);
                                                    BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                                                    //Socket empfant und liest die Information von Client
                                                    PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                                                    //Socket schreibt die Information zu Client 
                                                    ClientInstance client = new ClientInstance(s.getInetAddress(), s.getPort());
                                                    //neue Cleint aufbauen
                                                    serverListener.clientConncted(client, out);
                                                    //Server verbindt die neue Client
                                                    while(open){
							try{ serverListener.recivedInput(client, in.readLine());
                                                        //Server liest die Information von neue Client wenn "open"
                                                            }catch(IOException e){
                                                                                    serverListener.clientDisconnected(client);
                                                                                     //wenn Exception dann disconnect                                                                                   //wenn Exception dann disconnect
										    try{
											if(!s.isClosed()){
                                                                                            s.shutdownOutput();
                                                                                            s.close();
                                                                                            //Wenn Socket schließt,die Verbingdung abrechen
											}
                                                                                        }catch(Exception exception){ exception.printStackTrace(); }
                                                                                            clients.remove(s);
                                                                                            return;
											}
										   }
									}catch(Exception exception){ exception.printStackTrace(); }
									try{ s.close();
									}catch(Exception exception){ exception.printStackTrace(); }
									clients.remove(s);
								}
							});
							clientThread.setDaemon(true);
							clientThread.setName("Client "+s.getInetAddress().toString());
							clientThread.start();
						}catch(SocketException e){  //Do nothing
						}catch(IOException e){ e.printStackTrace(); }
					}
				}
			});
			serverThread.setDaemon(true);
			serverThread.setName("Server");
			serverThread.start();
		}catch(IOException e){ e.printStackTrace(); }
	}
}

