//
// Alice de Oliveira Duarte - 10419323
// Pedro Roberto Fernandes Noronha - 10443434
//
// Referências:
// - w3Schools AVL: https://www.w3schools.com/dsa/dsa_data_avltrees.php
// - Geeksforgeeks AVL: https://www.geeksforgeeks.org/dsa/insertion-in-an-avl-tree/
// - Material de aula - Prof. André Kishimoto, Prof. Jean M. Laine
//

package ed2;

public class Main {

	public static void main(String[] args) {

		// Teste a) Inserir 1, 2, 3
		System.out.println("===== Teste (a): Inserir 1, 2, 3 =====");
		AVL avlA = new AVL();
		avlA.AVLinsert(1);
		avlA.AVLinsert(2);
		avlA.AVLinsert(3);
		System.out.println("\nArvore resultante:");
		System.out.println(avlA.levelOrderTraversal());

		// Teste b) Inserir 3, 2, 1
		System.out.println("\n===== Teste (b): Inserir 3, 2, 1 =====");
		AVL avlB = new AVL();
		avlB.AVLinsert(3);
		avlB.AVLinsert(2);
		avlB.AVLinsert(1);
		System.out.println("\nArvore resultante:");
		System.out.println(avlB.levelOrderTraversal());

		// Teste c) Inserir 3, 1, 2
		System.out.println("\n===== Teste (c): Inserir 3, 1, 2 =====");
		AVL avlC = new AVL();
		avlC.AVLinsert(3);
		avlC.AVLinsert(1);
		avlC.AVLinsert(2);
		System.out.println("\nArvore resultante:");
		System.out.println(avlC.levelOrderTraversal());

		// Teste d) Inserir 1, 3, 2
		System.out.println("\n===== Teste (d): Inserir 1, 3, 2 =====");
		AVL avlD = new AVL();
		avlD.AVLinsert(1);
		avlD.AVLinsert(3);
		avlD.AVLinsert(2);
		System.out.println("\nArvore resultante:");
		System.out.println(avlD.levelOrderTraversal());

		// Teste e) Inserir 5, 4, 3, 1, 2, 6, 7, 9, 8
		System.out.println("\n===== Teste (e): Inserir 5, 4, 3, 1, 2, 6, 7, 9, 8 =====");
		AVL avlE = new AVL();
		avlE.AVLinsert(5);
		avlE.AVLinsert(4);
		avlE.AVLinsert(3);
		avlE.AVLinsert(1);
		avlE.AVLinsert(2);
		avlE.AVLinsert(6);
		avlE.AVLinsert(7);
		avlE.AVLinsert(9);
		avlE.AVLinsert(8);
		System.out.println("\nArvore resultante:");
		System.out.println(avlE.levelOrderTraversal());

		// Teste f) Remover 4 da árvore do item (e)
		System.out.println("\n===== Teste (f): Remover 4 =====");
		avlE.AVLremove(4);
		System.out.println("\nArvore resultante:");
		System.out.println(avlE.levelOrderTraversal());

		// Teste g) Remover 5 da árvore do item (f)
		System.out.println("\n===== Teste (g): Remover 5 =====");
		avlE.AVLremove(5);
		System.out.println("\nArvore resultante:");
		System.out.println(avlE.levelOrderTraversal());

		// Teste h) Remover 1 da árvore do item (g)
		System.out.println("\n===== Teste (h): Remover 1 =====");
		avlE.AVLremove(1);
		System.out.println("\nArvore resultante:");
		System.out.println(avlE.levelOrderTraversal());
	}
}
