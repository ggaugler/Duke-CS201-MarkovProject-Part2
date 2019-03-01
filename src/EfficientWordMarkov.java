import java.util.HashMap;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Map;

public class EfficientWordMarkov extends BaseWordMarkov 
{
	private Map<WordGram,ArrayList<String>> myMap;
	
	public EfficientWordMarkov()
	{
		this(2);
	}
	public EfficientWordMarkov(int order)
	{
		super(order);
		myOrder = order;
	}
	@Override
	public void setTraining(String text)
	{
		myMap = new HashMap<WordGram, ArrayList<String>>();
		myWords = text.split("\\s+");
		String next;
		for (int k = 0; k < myWords.length + 1 - myOrder; k++)
		{
			WordGram markov = new WordGram(myWords, k, myOrder);
			if(!myMap.containsKey(markov))
				myMap.put(markov,  new ArrayList<String>());
			if(myWords.length == myOrder + k)
				myMap.get(markov).add(PSEUDO_EOS);
			else
			{
				next = myWords[myOrder + k];
				myMap.get(markov).add(next);
			}
		}
	}
	@Override
	public ArrayList<String> getFollows(WordGram key)
	{
		if (!myMap.containsKey(key))
			throw new NoSuchElementException(key+" not in map");
		return myMap.get(key);
	}
}
