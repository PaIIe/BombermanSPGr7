/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.net.InetAddress;

public class ClientInstance{
	public final InetAddress ip;
	public final int port;
	public ClientInstance(InetAddress ip, int port){
		this.ip=ip;
		this.port=port;
	}
	@Override public String toString(){ return ip.toString()+":"+port; }
}
/**
 *
 * 
