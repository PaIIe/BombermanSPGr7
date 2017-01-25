/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Paul
 */
package Client;
public interface ClientListener{
	public void unknownHost();
	public void couldNotConnect();
	public void recivedInput(String msg);
	public void serverClosed();
	public void disconnected();
	public void connectedToServer();
}