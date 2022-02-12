

/* Källa delar av koden: https://stackoverflow.com/questions/36897412/java-arrays-sorting-scores-and-displaying-with-proper-name */
public class Person
{
	private String name;
    private int score;

    public Person(String name, int score) {
        this.score = score;
        this.name = name;
    }

	public int getScore()
	{
		return score;
	}

	public void setScore(int score)
	{
		this.score = score;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	
	@Override 
	 public String toString() {
		return name + " " + score;
	 }
    
}
