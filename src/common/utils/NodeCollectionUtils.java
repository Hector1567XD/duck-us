package common.utils;
import common.game.engine.node.NodeCollection;
import common.game.engine.node.Node;
import java.util.ArrayList;

/**
* Node Collection Helper.
* 
* <P>Esta clase permite realizar ciertas logicas sobre una instnacia de NodeCollection
* que normalmente no agregariamos al propio NodeCollection debido a:
*   1. Son funciones de DEBUG
*   2. Son funciones no tan comunes o importantes como para estar en NodeCollection
*  
* @author hecto
*/
public class NodeCollectionUtils {
    /** 
    * Imprimir NodeCollection.
    * 
    * Recibe una NodeCollection e <em>imprime</em> todos los nodos en su lista, cumple fines de DEBUG.
    * @param nodeCollection el NodeCollection sobre el cual se quiere operar.
    */
    public static void print(NodeCollection nodeCollection) {
        ArrayList<Node> nodeList = nodeCollection.getList();
        NodeCollectionUtils.print(nodeList);
    }
    
    /** 
    * Imprimir ArrayList de Nodos.
    * 
    * Recibe una ArrayList e <em>imprime</em> todos los nodos de la misma, cumple fines de DEBUG.
    * @param nodeList el ArrayList de Nodos que se busca imprimir.
    */
    public static <T extends Node> void print(ArrayList<T> nodeList) {
        for (T node: nodeList) {
            System.out.println(node);
        }
    }
}
