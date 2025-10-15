// Alice de Oliveira Duarte - 10419323
// Pedro Roberto Fernandes Noronha - 10443434
//
// Árvore AVL (Rotações) - Exemplo de implementação em Java
// Copyright (C) 2024 André Kishimoto
//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program.  If not, see <https://www.gnu.org/licenses/>.
//

package ed2;

public class AVL extends BST {

	public AVL() {
		super();
	}

	public AVL(Node root) {
		super(root);
	}

	private void updateParentChild(Node parent, final Node child, Node newChild) {
		if (parent != null) {
			if (parent.getLeft() == child) {
				parent.setLeft(newChild);
			} else {
				parent.setRight(newChild);
			}
		} else {
			root = newChild;
			newChild.setParent(null);
		}
	}
	
	// Rotação LL.
	private Node rotateLeft(Node node) {
		if (node == null) {
			return null;
		}

		System.out.println("Aplicando rotacao LL (esquerda simples)");

		// O nó atual deve ter um filho direito, que será a nova raiz desta subárvore.
		Node newRoot = node.getRight();
		if (newRoot == null) {
			return null;
		}

		// Troca as conexões do nó pai (newRoot vira filho de parent, no lugar de node).
		Node parent = node.getParent();
		updateParentChild(parent, node, newRoot);

		// newRoot é a nova raiz desta subárvore, então seu filho esquerdo se torna o
		// filho direito de node (que deixa de ser raiz desta subárvore).
		Node left = newRoot.getLeft();
		node.setRight(left);

		// node agora vira filho esquerdo de newRoot.
		newRoot.setLeft(node);

		return newRoot;
	}
	
	// Rotação RR.
	private Node rotateRight(Node node) {
		if (node == null) {
			return null;
		}

		System.out.println("Aplicando rotacao RR (direita simples)");

		// O nó atual deve ter um filho esquerdo, que será a nova raiz desta subárvore.
		Node newRoot = node.getLeft();
		if (newRoot == null) {
			return null;
		}

		// Troca as conexões do nó pai (newRoot vira filho de parent, no lugar de node).
		Node parent = node.getParent();
		updateParentChild(parent, node, newRoot);

		// newRoot é a nova raiz desta subárvore, então seu filho direito se torna o
		// filho esquerdo de node (que deixa de ser raiz desta subárvore).
		Node right = newRoot.getRight();
		node.setLeft(right);

		// node agora vira filho direito de newRoot.
		newRoot.setRight(node);

		return newRoot;
	}
	
	// Rotação LR.
	private Node rotateLeftRight(Node node) {
		System.out.println("Aplicando rotacao LR (esquerda-direita dupla)");
		node.setLeft(rotateLeft(node.getLeft()));
		return rotateRight(node);
	}
	
	// Rotação RL.
	private Node rotateRightLeft(Node node) {
		System.out.println("Aplicando rotacao RL (direita-esquerda dupla)");
		node.setRight(rotateRight(node.getRight()));
		return rotateLeft(node);
	}
	

	// Teste de rotação (manual) para árvore AVL com inserção de 1, 2, 3 (nesta sequência).
	public void testRotateLeft() {
		rotateLeft(root);
	}
	
	// Teste de rotação (manual) para árvore AVL com inserção de 3, 2, 1 (nesta sequência).
	public void testRotateRight() {
		rotateRight(root);
	}
	
	// Teste de rotação (manual) para árvore AVL com inserção de 3, 1, 2 (nesta sequência).
	public void testRotateLeftRight() {
		rotateLeftRight(root);
	}
	
	// Teste de rotação (manual) para árvore AVL com inserção de 1, 3, 2 (nesta sequência).
	public void testRotateRightLeft() {
		rotateRightLeft(root);
	}
	

	public void AVLinsert(int data) {
		root = AVLinsertNode(root, null, data);
	}

	// Insere e balanceia recursivamente
	private Node AVLinsertNode(Node node, Node parent, int data) {
		// Inserção normal BST
		if (node == null) {
			return new Node(data, parent);
		}

		int diff = data - node.getData();

		if (diff < 0) {
			node.setLeft(AVLinsertNode(node.getLeft(), node, data));
		} else if (diff > 0) {
			node.setRight(AVLinsertNode(node.getRight(), node, data));
		} else {
			throw new RuntimeException("Essa AVL não pode ter duplicatas!");
		}

		// Balanceia após inserção
		return balanceNode(node);
	}

	// Balanceia um nó baseado no balance factor
	private Node balanceNode(Node node) {
		if (node == null) {
			return null;
		}

		int bf = node.getBalanceFactor();

		// Desbalanceado à direita
		if (bf > 1) {
			if (node.getRight() != null && node.getRight().getBalanceFactor() < 0) {
				// Caso RL - dupla
				return rotateRightLeft(node);
			}
			// Caso LL - simples
			return rotateLeft(node);
		}

		// Desbalanceado à esquerda
		if (bf < -1) {
			if (node.getLeft() != null && node.getLeft().getBalanceFactor() > 0) {
				// Caso LR - dupla
				return rotateLeftRight(node);
			}
			// Caso RR - simples
			return rotateRight(node);
		}

		return node;
	}

	public void AVLremove(int data) {
		root = AVLremoveNode(root, data);
	}

	// Remove e balanceia recursivamente
	private Node AVLremoveNode(Node node, int data) {
		if (node == null) {
			throw new RuntimeException("Nó com chave " + data + " não existe na AVL!");
		}

		int diff = data - node.getData();

		if (diff < 0) {
			node.setLeft(AVLremoveNode(node.getLeft(), data));
		} else if (diff > 0) {
			node.setRight(AVLremoveNode(node.getRight(), data));
		} else {
			// Nó encontrado, remove
			node = removeNodeAVL(node);
		}

		// Balanceia após remoção
		return balanceNode(node);
	}

	// Remove um nó específico
	private Node removeNodeAVL(Node node) {
		if (node.isLeaf()) {
			return null;
		}

		if (!node.hasLeftChild()) {
			return node.getRight();
		} else if (!node.hasRightChild()) {
			return node.getLeft();
		} else {
			// Nó com dois filhos - usa predecessor (máximo da subárvore esquerda)
			Node predecessor = node.getLeft();
			while (predecessor.hasRightChild()) {
				predecessor = predecessor.getRight();
			}
			node.setData(predecessor.getData());
			node.setLeft(AVLremoveNode(node.getLeft(), predecessor.getData()));
		}

		return node;
	}



	@Override
	public String toString() {
		return "AVL - isEmpty(): " + isEmpty()
				+ ", getDegree(): " + getDegree()
				+ ", getHeight(): " + getHeight()
				+ ", root => { " + root + " }";				
	}

}