package ProofA1;

import java.io.IOException;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;

public class AVLProof {

	public static void main(String[] args) {
        // number of sample executions
        int samples = 100;
        double[] ns = new double[samples];
        double[] execution_times = new double[samples];
        int n = 10000000;
        for (int i=0; i<samples; i++) {
            // Create AVL tree with n nodes
        	AVLTree tree = createAVL(n);
        	// Delete a random node and time the deletion
            execution_times[i] = removeRandom(tree, n);
            ns[i] = n;
            n += 10000000;
        }

        // create chart
        XYChart chart = QuickChart.getChart("Execution Time of AVL Deletion", "Number of Nodes", "Execution Time (us)", "AVL Delete Runtime", ns, execution_times);
        double[] n2s = new double[samples];
        // add reference quadratic
        for (int i=0; i<samples; i++) {
            n2s[i] = (Math.log(ns[i]) + 500);
        }
        chart.addSeries("nlog(n) + 500", ns, n2s);
        // display chart
        new SwingWrapper<XYChart>(chart).displayChart();
        
        // save chart
        try {
            BitmapEncoder.saveBitmapWithDPI(chart, "./Run_Time_Chart", BitmapFormat.PNG, 300);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public static AVLTree createAVL(int size) {
		
		// Create a tree with the size of n
		AVLTree tree = new AVLTree();
		
		for (int i = 0; i < size; i++) {
			tree.insertNode(tree.root, i);
		}
		
		return tree;
		
	}
	
	public static double removeRandom(AVLTree tree, int size) {
		
		// Create a number somewhere between the size of the tree
		int randomNum = (int) Math.floor(Math.random() * size);
		//int randomNum = size-1;
		
		// Time the deletion
		double start = System.nanoTime();
		tree.deleteNode(tree.root, randomNum);
		double end = System.nanoTime();
		
		//System.out.println(randomNum + " " + end + " " +  start);
		
		return end - start;
	}

}
