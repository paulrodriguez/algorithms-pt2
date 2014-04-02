/***
  * Author:        Paul Rodriguez
  * Date Created:  4/2/2014
  * Last Updated:  4/2/2014
  * 
  */
import java.util.HashMap;

public class WordNet {
    
    private HashMap<Integer, String> vertices;  //  this represents a vertex with id and string of nouns
    private HashMap<String, Bag<String>> nounInIds;  //  the key is a noun and value is the list of ids it appears in
    //  constructor takes the name of two input files
    public WordNet(String synsets, String hypernyms)
    {
        vertices = new HashMap<Integer, String>();
        nounInIds = new HashMap<String, Bag<String>>();
        
        In s = new In(synsets);
        
        while (!s.isEmpty())
        {
            String[] line = s.readLine.split(',');
            vertices.put(Integer.parseInt(line[0]), line[1]);
            for (String s : line[1].split(" "))
            {
                
            }
        }
    }
    
    //  the set of nouns (no duplicates), returned as an Iterable
    public Iterable<String> nouns()
    {
    
    }
    
    //  is the word a WordNet noun?
    public boolean isNound(String word)
    {
    
    }
    
    //  distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB)
    {
    
    }
    
    //  a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    //  in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB)
    {
    
    }
    
    //  for unit testing of this class
    public static void main(String[] args)
    {
    
    }
}