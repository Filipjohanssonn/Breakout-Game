
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.DefaultListModel;


/* Källa för delar av koden
 * https://stackoverflow.com/questions/37590801/java-highscore-list 
  */

public class HighScore
{
	private DefaultListModel<Person> dlm = new DefaultListModel<Person>();
	
	public HighScore()
	{
		dlm = new DefaultListModel<Person>();
	}
	
	public void add(Person person)
	{
		dlm.addElement(person);
	}
	
	public DefaultListModel<Person> getHighScore()
	{
		return dlm;
	}
	
	public int getHighScoreSize()
	{
		return dlm.getSize();
	}	
	
	public void remove(Person person)
	{
		dlm.removeElement(person);
	}
	
}
