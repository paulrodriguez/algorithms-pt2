/***
  * Author:        Paul Rodriguez
  * Date Created:  4/2/2014
  * Last Updated:  4/28/2014
  * 
  */
import java.util.HashMap;

public class WordNet {
    
    private HashMap<Integer, String> vertices;  //  this represents a vertex with id and string of nouns
    private HashMap<String, Bag<Integer>> nounInIds;  //  the key is a noun and value is the list of ids it appears in
    private SAP sap;
    //  constructor takes the name of two input files
    public WordNet(String synsets, String hypernyms)
    {
        vertices = new HashMap<Integer, String>();
        nounInIds = new HashMap<String, Bag<Integer>>();
        
        In s = new In(synsets);
        Bag<Integer> bag_ids;
        while (!s.isEmpty())
        {
            String line = s.readLine();
            String[] elements = line.split(",");
            int id = Integer.parseInt(elements[0]);
            vertices.put(id, elements[1]);
            for (String nouns : elements[1].split(" "))
            {
                bag_ids = nounInIds.get(nouns);
                if(bag_ids == null)
                {
                    bag_ids = new Bag<Integer>();
                    bag_ids.add(id);
                    nounInIds.put(nouns, bag_ids);
                }
                else
                {
                    bag_ids.add(id);
                }
            }
        }
        
        Digraph g = createDigraph(hypernyms, vertices.size());
        
        DirectedCycle dc = new DirectedCycle(g);
        if (dc.hasCycle())
        {
            throw new IllegalArgumentException("the graph is not a DAG");
        }
        
        int roots = 0;
        for (int i = 0; i < g.V(); i++)
        {
            //  if vertex does not have a adjacent vertices then its a root 
            if (!g.adj(i).iterator().hasNext())
            {
                roots++;
            }
        }
        
        if (roots != 1)
        {
            throw new IllegalArgumentException("the graph has more than one root");
        }
    }
    
    
    private Digraph createDigraph(String hypernyms, int size)
    {
        Digraph g= new Digraph(size);
        In file = new In(hypernyms);
        int synsetid = 0;
        String line = null;
        String[] contents = null;
        while (!file.isEmpty())
        {
            line = file.readLine();
            contents = line.split(",");
            synsetid = Integer.parseInt(contents[0]);
            for (int i = 1; i < contents.length; i++)
            {
                g.addEdge(synsetid, Integer.parseInt(contents[i]));
            }
        }
        
        return g;
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
        if (isNoun(nounA) && isNoun(nounB))
        {
            return sap.length(nounInIds.get(nounA), nounInIds.get(nounB));
        }
        else
        {
            throw new IllegalArgumentException("one or more of the noun(s) not found in the wordnet");
        }
        
        //return 0;
    }
    
    //  a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    //  in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB)
    {
        if (isNoun(nounA) && isNoun(nounB))
        {
            return vertices.get(sap.ancestor(nounInIds.get(nounA), nounInIds.get(nounB)));
        }
        else
        {
            throw new IllegalArgumentException("one or more of the noun(s) not found in the wordnet");
        }
        //return null;
    }
    
    //  for unit testing of this class
    public static void main(String[] args)
    {
        WordNet wn = new WordNet(args[0], args[1]);
    }
}