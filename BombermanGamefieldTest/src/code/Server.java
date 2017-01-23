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
 * @author feng
 */


public class Server{
    private int port;
    private boolean open = true;
    private ServerSocket ss;
    private ServerListener serverListener;
    private ArrayList<Socket> clients = new ArrayList<>();
        
    public Server(int port, ServerListener listener){
	serverListener=listener;
	try{
            ss=new ServerSocket(port);
            if(this.port==0)    this.port=ss.getLocalPort();
            else    this.port=port;
            Thread serverThread = new Thread(new Runnable(){
		public void run(){
                    while(open){
			try{
                            final Socket s = ss.accept();
                            Thread clientThread = new Thread(new Runnable(){
				public void run(){
                                    try{
                                                    clients.add(s);
                                                    BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                                                    PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                                                    ClientInstance client = new ClientInstance(s.getInetAddress(), s.getPort());
                                                    serverListener.clientConncted(client, out);
                                                    while(open){
							try{ serverListener.recivedInput(client, in.readLine());
                                                            }catch(IOException e){
                                                                                    serverListener.clientDisconnected(client);
										    try{
											if(!s.isClosed()){
                                                                                            s.shutdownOutput();
                                                                                            s.close();
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
