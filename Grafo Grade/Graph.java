import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;
import java.io.FileReader;
import java.io.BufferedReader;

public class Graph {
    protected HashMap< Integer, Vertex> vertex_set;
    protected int time;
    protected Boolean acyclic;
    public boolean isBipartide = true;

    public void print() {   //Print do grafo
        System.out.printf("\n\nDados do Grafo:");

        for( Vertex v : vertex_set.values())
            v.print();
    }
    
	public void open_text( String arq_ent ) {   //Leitura do arquivo de texto
		String thisLine = null;
        vertex_set = new HashMap<Integer,Vertex>();
		String pieces[ ];

		try {
		    FileReader file_in = new FileReader( arq_ent );
		    BufferedReader br1 = new BufferedReader( file_in );
		    while ( (thisLine = br1.readLine( )) != null) {
			    thisLine = thisLine.replaceAll("\\s+", " ");
			    pieces = thisLine.split(" ");
			    int v1 = Integer.parseInt( pieces[0] );
			    this.add_vertex( v1 );
			    for( int i = 2; i < pieces.length; i++ ) {
   					int v2 = Integer.parseInt( pieces[ i ] );
   					// pode ser a primeira ocorrência do v2
					this.add_vertex( v2 );
					this.add_edge( v1, v2 );
				}
		    }       
			System.out.print("Arquivo lido com sucesso.");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
    
    public void add_vertex( int id ) { 
        if ( id < 1 || this.vertex_set.get( id ) == null ) {
            Vertex v = new Vertex( id );
            vertex_set.put( v.id, v );
            reset();
        }
    }

    public void add_edge( Integer id1, Integer id2) { 
        Vertex v1 = vertex_set.get( id1 );
        Vertex v2 = vertex_set.get( id2 );
        if ( v1 == null || v2 == null ) {
            System.out.printf("Vértice inexistente!");
            return;
        }
        v1.add_neighbor( v2 );
        v2.add_neighbor( v1 );
        reset();

    }

    public void del_vertex( int id ) {
        for( Vertex v1 : vertex_set.values()) {
			v1.nbhood.remove( id );
        }
        vertex_set.remove( id );
        reset();
    }

    protected void reset() {   // importante //
        acyclic = null;
        time = 0;
        for( Vertex v1 : vertex_set.values() ){
            v1.reset();
        }
    }

	
	
    
    public void bfs( Integer id_root ) {	// Busca em largura
	    reset();  
        Vertex root = vertex_set.get( id_root );
        root.dist = 0;
		Queue<Vertex> q1 = new LinkedList<Vertex>();
        q1.add( root );
        Vertex current;
		while ((current = q1.poll()) != null) {
            for( Vertex neig : current.nbhood.values() ) {
                if( neig.dist == null ) {
                    neig.discover( current );
                    q1.add( neig );
                }
                else if( neig.dist == current.dist ) {
                    System.out.println("O grafo não é bipartido");
                    isBipartide = false;
					break;
                    
                }
            }
        }

        for( Vertex vertex : vertex_set.values() ) { //testando conectividade do grafo//
			if( vertex.dist == null ) {
				System.out.println("O grafo dado é desconexo, logo ele não é grade");
				isBipartide = false; 
				break;
			
			}
		}
    }

    public int min_degree() {
        int min = 10^5;
        for( Vertex v1 : vertex_set.values()) {
            if( v1.degree() < min )
                min = v1.degree();
		}
        return min;
    }
    
    public void Bipartide(){    // importante //
        for( Vertex v1 : vertex_set.values()){
            bfs(v1.id);
                
            if (!isBipartide){
                break;
            }
        }
    }

       
}
