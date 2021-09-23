import java.util.Scanner;

public class AlgGrafos {
    public static void main(String args[]) {
        
        Scanner scan1 = new Scanner(System.in);
        
        String line1 = "\n 0 Sair \n 1 Ler arquivo \n 2 Print \n 3 Grade \n 4 Caminho customizado \n 5 Checa caminhos \n 6 Grau mínimo\n Escolha a opção: ";
        String menu = line1;
        
        Graph g1 = new Graph();
                
        while( true ) {
            System.out.printf( menu );
            int choice = scan1.nextInt();
            switch( choice ) {
                case 0:
                    return;
                case 1:
                    String arq_ent = "myfiles\\Grafo3.txt";
                    /*Em 'myfiles' temos 3 exemplos de grafos para cada exceção presente no programa*/
                    g1.open_text( arq_ent );
                    break;
                case 2:
                    g1.print();
                    break;
				
                case 3://Faz uma geral no grafo, checando se ele é bipartido e com os graus mínimos do grafo grade
					g1.Bipartide();
                    
					if(g1.isBipartide && g1.min_degree() >= 2){
						System.out.println("O grafo é Grade.");
					}else if(g1.isBipartide && g1.min_degree() < 2){
                        System.out.println("Grafo não é grade devido ao grau de seus vértices!");
                    }else{
                        System.out.println("Grafo não é Grade pois não é bipartido.");
                    }
					break;
                    
                case 4://Digita manualmente um subgrafo gerado pelos caminhos do grafo texto
                    
                    //Digite os vértices que deseja remover:
                    g1.del_vertex( 1 );
                    g1.del_vertex( 2 );
                    g1.del_vertex( 3 );
                    g1.del_vertex( 4 );
                    g1.del_vertex( 5 );

                    //Digite os vértices que deseja adicionar:
                    g1.add_vertex( 1 );
                    g1.add_vertex( 2 );
                    g1.add_vertex( 3 );
                    g1.add_vertex( 4 );        
                    
                    
                    //Adiciona as arestas que deseja adicionar:
                    g1.add_edge( 1, 2 );        
					g1.add_edge( 1, 3 );
					g1.add_edge( 1, 4 );

                    break;

                case 5://Checa se o caminho informado ( subgrafo ) é bipartido
					g1.Bipartide();
                    
					if(g1.isBipartide){
                        System.out.println("Caminhos informados formam um grafo bipartido!");
                    }else{
                        System.out.println("Caminhos informados não formam um grafo bipartido!");
                    }
					break;
                case 6:
                    System.out.println(g1.min_degree());
                    break;
				

            }
        }
    }
}
