package dev.code.bomberman;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class Logs {
	private Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	public Logs(){
		Logger root = Logger.getLogger("");
		FileHandler txt = null;
		try
		{
			txt = new FileHandler("Log.txt");
		} catch (SecurityException | IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		root.setLevel(Level.INFO);
		txt.setFormatter(new Formatter() {
			@Override
			public String format(LogRecord record)
			{
				String ret = "";
				if(record.getLevel().intValue() >= Level.WARNING.intValue())
				{
					ret += "ATTENTION!:";
				}
				ret += record.getLevel();

				
				ret += this.formatMessage(record);
				ret += "\r\n";
				return ret;
			}
		});
		root.addHandler(txt);
	}
	public void BombLog(int player, int xpos, int ypos)//included
	{
		log.info("	Player "+ player +" placed a bomb at pos " + xpos + ", " + ypos);
	}
	
	public void MoveLog(int player, int xpos, int ypos)
	{
		player=player-50;
		if(player>4)
			player=player-4;
		log.info("	Player "+ player +" moved to " + xpos + ", " + ypos);
	}
	
	public void DiedLog(int player)//included
	{
		log.info("	Player " + player + " died");
	}
	
	public void LostArmorLog(int player)//included
	{
		log.info("	Player " + player + " lost his Armor");
	}

	public void ExplodeLog(int xpos, int ypos)//included
	{
		log.info("	Bomb exploded at pos " + xpos + ", " + ypos);
	}
	
	public void DestroyedWallLog(int xpos, int ypos)//included
	{
		log.info("	Wall destroyed at " + xpos + ", " + ypos);
	}
	
	public void PowerUpLog(int player, String bonus)//included
	{
		log.info("	Player " + player + " got " + bonus + " PowerUp" );
	}



}