import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class EfficientWordMarkov extends BaseWordMarkov 
{
	private Map<WordGram,ArrayList<String>> myMap;
	
	public EfficientWordMarkov(int order)
	{
		super(order);
		myMap = new HashMap<WordGram, ArrayList<String>>();
	}
	public EfficientWordMarkov()
	{
		this(2);
		myMap = new HashMap<WordGram,ArrayList<String>>();
	}
	@Override
	public void setTraining(String text)
	{
		myMap.clear();
		myWords = text.split("\\s+");
		String next;
		for (int k = 0; k < myWords.length + 1 - myOrder; k++)
		{
			WordGram markov = new WordGram(myWords, k, myOrder);
			if(!myMap.containsKey(markov))
				myMap.put(markov,  new ArrayList<String>());
			if(myWords.length < myOrder + k)
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
		if (myMap.containsKey(key))
			return myMap.get(key);
		else
			throw new NoSuchElementException(key+" not in map");	
	}
}
