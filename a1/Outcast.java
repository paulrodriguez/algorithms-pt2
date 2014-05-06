/*******
  * Author: Paul Rodriguez
  * Created: 5/5/2014
  * Last Updated: 5/5/2014
  * 
  */


public class Outcast
{
    private WordNet wd;
    public Outcast(WordNet wordnet)
    {
        this.wd = wordnet;
    }
    
    public String outcast(String[] nouns)
    {
        String outcast = null;
        int distance = 0;
        int max = 0;
        for (int i = 0; i < nouns.length; i++)
        {
            distance = 0;
            for(int j = 0; j < nouns.length; j++)
            {
                if (nouns[i] != nouns[j])
                {
                    distance += this.wd.distance(nouns[i], nouns[j]);
                }
            }
            
            if (distance > max)
            {
                max = distance;
                outcast = nouns[i];
            }
        }
        
        return outcast;
    }
    
    public static void main(String[] args)
    {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readStrings(args[t]);
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}