import java.util.*;

public class EfficientMarkov extends BaseMarkov 
{
	protected String myText;
	protected Random myRandom;
	protected int myOrder;
	protected static String PSEUDO_EOS = "";
	protected static long RANDOM_SEED = 1234;
	private Map<String,ArrayList<String>> myMap;
	
	EfficientMarkov(int order)
	{
		super(order);
		myMap = new HashMap<String, ArrayList<String>>();
	}
	public EfficientMarkov()
	{
		this(2);
		myMap = new HashMap<String,ArrayList<String>>();
	}
	@Override
	public void setTraining(String text)
	{
		myMap.clear();
		myText = text;
		String markov, next;
		for (int k = 0; k < myText.length() + 1 - myOrder; k++)
		{
			markov = myText.substring(k, myOrder + k);
			if(!myMap.containsKey(markov))
				myMap.put(markov,  new ArrayList<String>());
			if(myText.length() < myOrder + k)
				myMap.get(markov).add(PSEUDO_EOS);
			else
				next = myText.substring(myOrder + k, myOrder + k + 1);
		}
	}
	@Override
	public ArrayList<String> getFollows(String key)
	{
		if (myMap.containsKey(key))
			return myMap.get(key);
		else
			throw new NoSuchElementException(key+" not in map");	
	}
}	
