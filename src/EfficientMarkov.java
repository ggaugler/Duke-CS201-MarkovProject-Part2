import java.util.*;

public class EfficientMarkov extends BaseMarkov 
{

	private Map<String,ArrayList<String>> myMap;
	
	public EfficientMarkov()
	{
		this(3);
		myMap = new HashMap<String,ArrayList<String>>();
	}
	public EfficientMarkov(int order)
	{
		super(order);
		myMap = new HashMap<String, ArrayList<String>>();
	}
	@Override
	public void setTraining(String text)
	{
		myMap.clear();
		myText = text;
		String markov;
		String next;
		for (int k = 0; k < myText.length() + 1 - myOrder; k++)
		{
			markov = myText.substring(k, myOrder + k);
			if(!myMap.containsKey(markov))
				myMap.put(markov,  new ArrayList<String>());
			if(myText.length() == myOrder + k)
				myMap.get(markov).add(PSEUDO_EOS);
			else
			{
				next = myText.substring(myOrder + k, myOrder + k + 1);
				myMap.get(markov).add(next);
			}
		}
	}
	@Override
	public ArrayList<String> getFollows(String key)
	{
		if (!myMap.containsKey(key))
			throw new NoSuchElementException(key+" not in map");	
		return myMap.get(key);
	}
}	
