/***
  * Author:        Paul Rodriguez
  * Date Created:  4/28/2014
  * Last Updated:  5/5/2014
  * 
  */

public class SAP 
{
    private final Digraph g;
    //  constructor takes a digraph (not necessarily a DAG)
    public SAP (Digraph G)
    {
        g = new Digraph(G);
    }

    //  length of shortest ancestral path between v and w
    public int length(int v, int w)
    {
        if ((v < 0 || v > this.g.V()) || (w < 0 || w > this.g.V()))
        {
            throw new IndexOutOfBoundsException("the indices are not in range");
        }
        
        BreadthFirstDirectedPaths bfsv = new BreadthFirstDirectedPaths(g, v);
        BreadthFirstDirectedPaths bfsw = new BreadthFirstDirectedPaths(g, w);
        
        int ancestor = ancestor(v, w);
        int path_length = -1;
        if (ancestor != -1) 
        {
            path_length = bfsv.distTo(ancestor) + bfsw.distTo(ancestor);
        }
        
        return path_length;
    }
    
    //a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor (int v, int w)
    {
        if ((v < 0 || v > this.g.V()) || (w < 0 || w > this.g.V()))
        {
            throw new IndexOutOfBoundsException("the indices are not in range");
        }
        
        BreadthFirstDirectedPaths bfsv = new BreadthFirstDirectedPaths(g, v);
        BreadthFirstDirectedPaths bfsw = new BreadthFirstDirectedPaths(g, w);
        
        int short_ancestor = -1;
        int shortest_path = Integer.MAX_VALUE;
       
        for (int i = 0; i < this.g.V(); i++)
        {
            if (bfsv.hasPathTo(i) && bfsw.hasPathTo(i))
            {
                if ((bfsv.distTo(i)+bfsw.distTo(i)) < shortest_path)
                {
                    shortest_path = bfsv.distTo(i) + bfsw.distTo(i);
                    short_ancestor = i;
                }
            }
        }
        return short_ancestor;
    }
    
    //length of shortest ancestral path between any vertex in v and any vertex in w; -1 of not such path
    public int length(Iterable<Integer> v, Iterable<Integer> w)
    {
        
        for (Integer i : v)
        {
            if (i < 0 || i > (g.V()-1))
            {
                throw new IndexOutOfBoundsException("indices out of range");
            }
        }
        
        for (Integer i : w)
        {
            if (i < 0 || i > (this.g.V()-1))
            {
                throw new IndexOutOfBoundsException("indices out of range");
            }
        }
        
        BreadthFirstDirectedPaths bfsv = new BreadthFirstDirectedPaths(g, v);
        BreadthFirstDirectedPaths bfsw = new BreadthFirstDirectedPaths(g, w);
        
        int ancestor = ancestor(v, w);
        int path_length = -1;
        if (ancestor != -1) 
        {
            path_length = bfsv.distTo(ancestor) + bfsw.distTo(ancestor);
        }
        
        return path_length;
    }
    
    //  a common ancestor that participates in shortest
    public int ancestor (Iterable<Integer> v, Iterable<Integer> w)
    {
        for (Integer i : v)
        {
            if (i < 0 || i > (g.V()-1))
            {
                throw new IndexOutOfBoundsException("indices out of range");
            }
        }
        
        for (Integer i : w)
        {
            if (i < 0 || i > (g.V()-1))
            {
                throw new IndexOutOfBoundsException("indices out of range");
            }
        }
        
        BreadthFirstDirectedPaths bfsv = new BreadthFirstDirectedPaths(g, v);
        BreadthFirstDirectedPaths bfsw = new BreadthFirstDirectedPaths(g, w);
        
        int short_ancestor = -1;
        int shortest_path = Integer.MAX_VALUE;
    
        for (int i = 0; i < this.g.V(); i++)
        {
            if (bfsv.hasPathTo(i) && bfsw.hasPathTo(i))
            {
                if ((bfsv.distTo(i) + bfsw.distTo(i)) < shortest_path)
                {
                    shortest_path = bfsv.distTo(i) + bfsw.distTo(i);
                    short_ancestor = i;
                }
            }
        }
        return short_ancestor;
        
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