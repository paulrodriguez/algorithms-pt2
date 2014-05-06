/***
  * Author:        Paul Rodriguez
  * Date Created:  4/2/2014
  * Last Updated:  5/5/2014
  * 
  */
import java.util.HashMap;

public class WordNet {
    
    private HashMap<Integer, String> vertices;  //  this represents a vertex with id and string of nouns
    private HashMap<String, Bag<Integer>> nounToId;  //  the key is a noun and value is the list of ids it appears in
    private final Digraph my_graph;
    //private final SAP sap_var;
    //  constructor takes the name of two input files
    public WordNet(String synsets, String hypernyms)
    {
        vertices = new HashMap<Integer, String>();
        nounToId = new HashMap<String, Bag<Integer>>();
        
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
                bag_ids = nounToId.get(nouns);
                if(bag_ids == null)
                {
                    Bag<Integer> bag_new = new Bag<Integer>();
                    
                    bag_new.add(id);
                    nounToId.put(nouns, bag_new);
                }
                else
                {
                    bag_ids.add(id);
                }
            }
        }
        
        this.my_graph = createDigraph(hypernyms, vertices.size());
        
        DirectedCycle dc = new DirectedCycle(this.my_graph);
        if (dc.hasCycle())
        {
            throw new IllegalArgumentException("the graph is not a DAG");
        }
        
        int roots = 0;
        for (int i = 0; i < this.my_graph.V(); i++)
        {
            //  if vertex does not have a adjacent vertices then its a root 
            if (!this.my_graph.adj(i).iterator().hasNext())
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
        Digraph g = new Digraph(size);
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
        return nounToId.keySet();
    }
    
    //  is the word a WordNet noun?
    public boolean isNoun(String word)
    {
        return nounToId.containsKey(word);
    }
    
    //  distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB)
    {
        
        if (!isNoun(nounA) || !isNoun(nounB))
        {
            throw new IllegalArgumentException("one or more of the noun(s) not found in the wordnet");
        }
            
       
        SAP sap_var = new SAP(my_graph);
        return sap_var.length(nounToId.get(nounA), nounToId.get(nounB));
        
    }
    
    //  a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    //  in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB)
    {
        if (!isNoun(nounA) || !isNoun(nounB))
        {
            throw new IllegalArgumentException("one or more of the noun(s) not found in the wordnet");
        }
            
        SAP sap_var = new SAP(my_graph);
        return vertices.get(sap_var.ancestor(nounToId.get(nounA), nounToId.get(nounB)));
       
    }
    
    //  for unit testing of this class
    public static void main(String[] args)
    {
        WordNet wn = new WordNet(args[0], args[1]);
    }
}