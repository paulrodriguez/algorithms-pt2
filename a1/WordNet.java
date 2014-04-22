/***
  * Author:        Paul Rodriguez
  * Date Created:  4/2/2014
  * Last Updated:  4/21/2014
  * 
  */
import java.util.HashMap;

public class WordNet {
    
    private HashMap<Integer, String> vertices;  //  this represents a vertex with id and string of nouns
    private HashMap<String, Bag<Integer>> nounInIds;  //  the key is a noun and value is the list of ids it appears in
    //  constructor takes the name of two input files
    public WordNet(String synsets, String hypernyms)
    {
        vertices = new HashMap<Integer, String>();
        nounInIds = new HashMap<String, Bag<Integer>>();
        
        In s = new In(synsets);
        
        while (!s.isEmpty())
        {
            String line = s.readLine();
            String[] elements = line.split(",");
            int id = Integer.parseInt(elements[0]);
            vertices.put(id, elements[1]);
            for (String nouns : elements[1].split(" "))
            {
                Bag<Integer> bag_ids = nounInIds.get(nouns);
                if(bag_ids == null)
                {
                    bag_ids = new Bag<Integer>();
                    bag_ids.add(id);
                    nounInIds.put(nouns, bag_ids);
                }
                else
                {
                    bag_ids.add(id);
                    nounInIds.put(nouns, bag_ids);
                }
            }
        }
        
        Digraph g = createDigraph(hypernyms, vertices.size());
    }
    
    
    private Digraph createDigraph(String hypernyms, int size)
    {
        Digraph = new Digraph(size);
    }   
     
    //  the set of nouns (no duplicates), returned as an Iterable
    public Iterable<String> nouns()
    {
        return nounInIds.keySet();
    }
    
    //  is the word a WordNet noun?
    public boolean isNoun(String word)
    {
        return nounInIds.containsKey(word);
    }
    
    //  distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB)
    {
        return 0;
    }
    
    //  a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    //  in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB)
    {
        return null;
    }
    
    //  for unit testing of this class
    public static void main(String[] args)
    {
    
    }
}