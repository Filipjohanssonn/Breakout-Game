import javax.swing.DefaultListModel;

public class LatestRuns
{
	private DefaultListModel dlm;
	public LatestRuns()
	{
		dlm = new DefaultListModel();
	}
	
	public void add(Object anElement) 
	{
		dlm.addElement(anElement);
	}
	
	public void remove()
	{
		dlm.remove(1);
	}
	public DefaultListModel getLatestRuns()
	{
		return dlm;
	}
	
	public int getEntireLatestRunList()
	{
		return dlm.getSize();
	}
	
}
