import java.util.HashMap;

public class Vertex {
    protected Integer id;
    protected HashMap< Integer, Vertex> nbhood;
    protected Vertex parent, root;
    protected Integer dist, d, f;
    protected int size;

    public Vertex ( int id ) {    
        this.id = id;
        nbhood = new HashMap<Integer,Vertex>();
        parent = null;
        dist = d = null;
    }
 
    public void print() {   
        System.out.print("\nId do vértice " + id + ", Vizinhança: " );
        for( Vertex v : nbhood.values())
            System.out.print(" " + v.id );
    }

	protected void reset() {  
		parent = null;
		d = null;
		f = null;
		dist = null;
	}

    public void add_neighbor( Vertex viz ) {   
        nbhood.put(viz.id, viz);
    }
    
    
    public void discover( Vertex parent ) {
        this.parent = parent;
        this.dist = parent.dist + 1;
    }

    public int degree() {
        return nbhood.size();
    }
    
}
