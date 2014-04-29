/***
  * Author:        Paul Rodriguez
  * Date Created:  4/28/2014
  * Last Updated:  4/28/2014
  * 
  */

public class SAP 
{
    //  constructor takes a digraph (not necessarily a DAG)
    public SAP (Digraph G)
    {
    
    }

    //  length of shortest ancestral path between v and w
    public int length(int v, int w)
    {
        return 0;
    }
    
    //a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor (int v, int w)
    {
        return 0;
    }
    
    //length of shortest ancestral path between any vertex in v and any vertex in w; -1 of not such path
    public int length(Iterable<Integer> v, Iterable<Integer> w)
    {
        return 0;
    }
    
    //  a common ancestor that participates in shortest
    public int ancestor (Iterable<Integer> v, Iterable<Integer> w)
    {
        return 0;
    }
    
    //  for unit testing of this class
    public static void main(String[] args)
    {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length   = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }   
    }
}