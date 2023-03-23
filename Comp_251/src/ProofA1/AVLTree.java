package ProofA1;

public class AVLTree {

    public Node root;

    public AVLTree() {
        this.root = null;
    }

    // Recursively go through the tree to find the value
    // If the value does not exist, return null, else return the node
    public Node searchNode(Node n, int value) {

        if (n == null || n.value == value) {
            return n;
        }

        if (n.value > value) {
            return searchNode(n.left, value);
        } else {
            return searchNode(n.right, value);
        }

    }

    // Shift the nodes to the right
    public Node rotateRight(Node node) {

        Node left = node.left;

        node.left = left.right;
        left.right = node;

        node.updateBalance();
        left.updateBalance();

        return left;

    }

    // Shift the nodes to the left
    public Node rotateLeft(Node node) {

        Node right = node.right;

        node.right = right.left;
        right.left = node;

        node.updateBalance();
        right.updateBalance();

        return right;

    }

    public Node rebalance(Node node) {

        int balanceFactor = node.balance;

        // Left-heavy?
        if (balanceFactor < -1) {
            if (node.left.balance <= 0) { // Case 1

                // Rotate right
                node = rotateRight(node);

            } else { // Case 2

                // Rotate left-right
                node.left = rotateLeft(node.left);
                node = rotateRight(node);
            }

        }

        // Right-heavy?
        if (balanceFactor > 1) {
            if (node.right.balance >= 0) { // Case 3

                // Rotate left
                node = rotateLeft(node);

            } else { // Case 4

                // Rotate right-left
                node.right = rotateRight(node.right);
                node = rotateLeft(node);

            }
        }

        return node;

    }

    public Node insertNode(Node n, int value) {
        // No node at current position --> store new node at current position
        if (n == null) {

            n = new Node(value);

        }

        // Otherwise, traverse the tree to the left or right depending on the key
        else if (value < n.value) {

            n.left = insertNode(n.left, value);

        } else if (value > n.value) {

            n.right = insertNode(n.right, value);

        }

        // n is equal to the newly created node or the already existing node;
        // Update and rebalance the tree
        n.updateBalance();
        rebalance(n);

        return n;
    }

    public Node deleteNode(Node node, int value) {
        // No node at current position --> go up the recursion
        if (node == null) {
            return null;
        }

        // Traverse the tree to the left or right depending on the key
        if (value < node.value) {

            node.left = deleteNode(node.left, value);

        } else if (value > node.value) {

            node.right = deleteNode(node.right, value);

        }

        // At this point, "node" is the node to be deleted

        // Node has no children --> just delete it
        else if (node.left == null && node.right == null) {

            node = null;

        }

        // Node has only one child --> replace node by its single child
        else if (node.left == null) {

            node = node.right;

        } else if (node.right == null) {

            node = node.left;

        } else {
            // Node has two children
            deleteNodeWithTwoChildren(node);
        }

        if (node == null) {
            return null;
        }

        node.updateBalance();

        rebalance(node);

        return node;
    }

    private void deleteNodeWithTwoChildren(Node node) {
        // Find minimum node of right subtree ("inorder successor" of current node)
        Node inOrderSuccessor = findMinimum(node.right);

        // Copy inorder successor's data to current node
        node.value = inOrderSuccessor.value;

        // Delete inorder successor recursively
        node.right = deleteNode(node.right, inOrderSuccessor.value);
    }

    private Node findMinimum(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }


}