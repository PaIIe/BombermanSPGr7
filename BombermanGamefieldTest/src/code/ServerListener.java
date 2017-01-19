/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.PrintWriter;

public interface ServerListener{
	public void clientConncted(ClientInstance client, PrintWriter out);
	public void clientDisconnected(ClientInstance client);
	public void recivedInput(ClientInstance client, String msg);
	public void serverClosed();
}