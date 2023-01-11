//Move to no package in order to run

package assignment3;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.text.*;
import java.lang.Math;

public class DecisionTree implements Serializable {

	DTNode rootDTNode;
	int minSizeDatalist; //minimum number of datapoints that should be present in the dataset so as to initiate a split
	
	// Mention the serialVersionUID explicitly in order to avoid getting errors while deserializing.
	public static final long serialVersionUID = 343L;
	
	public DecisionTree(ArrayList<Datum> datalist , int min) {
		minSizeDatalist = min;
		rootDTNode = (new DTNode()).fillDTNode(datalist);
	}

	class DTNode implements Serializable{
		//Mention the serialVersionUID explicitly in order to avoid getting errors while deserializing.
		public static final long serialVersionUID = 438L;
		boolean leaf;
		int label = -1;      // only defined if node is a leaf
		int attribute; // only defined if node is not a leaf
		double threshold;  // only defined if node is not a leaf

		DTNode left, right; //the left and right child of a particular node. (null if leaf)

		DTNode() {
			leaf = true;
			threshold = Double.MAX_VALUE;
		}

		
		// this method takes in a datalist (ArrayList of type datum). It returns the calling DTNode object 
		// as the root of a decision tree trained using the datapoints present in the datalist variable and minSizeDatalist.
		// Also, KEEP IN MIND that the left and right child of the node correspond to "less than" and "greater than or equal to" threshold
		DTNode fillDTNode(ArrayList<Datum> datalist) {

			boolean sameLabel = true;		//same label boolean

			if (datalist.size() >= minSizeDatalist) {	//if size meets the minimum requirements
				
				for (int i = 0; i < datalist.size()-1; i++) {	//Check if all the labels are the same;
					
//					if (datalist.size() == 176)
//						System.out.println(datalist.get(i).y + " " +  datalist.get(i+1).y + " " + sameLabel);
					
					if (datalist.get(i).y != datalist.get(i+1).y) {
						sameLabel = false;
						break;
					}
					
				}
				
				
				if (sameLabel) {							//If labels are uniform, create leaf node with the same label and return
					DTNode newNode = new DTNode();
					newNode.label = datalist.get(0).y;
					return newNode;
				} else {									//If labels are not uniform:
					double[] results = findBestSplit(datalist);
					DTNode newNode = new DTNode();
					newNode.leaf = false;
					newNode.attribute = (int) results[0];
					newNode.threshold = results[1];
					
					ArrayList<Datum> left = new ArrayList<>();
					ArrayList<Datum> right = new ArrayList<>();
					
					for (Datum d: datalist) {
						if (d.x[(int) results[0]] < results[1]) {
							left.add(d);
						} else if (d.x[(int) results[0]] >= results[1]) {
							right.add(d);
						}
					}
					
//					System.out.println(left.size() + " " + right.size() + " " + minSizeDatalist);
//					System.out.println(results[0] + " " + results[1]);
//					System.out.println();
//					
//					if (datalist.size() == 88) {
//						for (Datum d: datalist) {
//							System.out.println(Arrays.toString(d.x) + " " + d.y);
//						}
//					}
					
					newNode.left = fillDTNode(left);
					newNode.right = fillDTNode(right);

					
					return newNode;
					
				}
				
			} else {
				
				DTNode child = new DTNode();
				child.label = findMajority(datalist);
				
				return child;
				
			}
			
		}
		
		double[] findBestSplit(ArrayList<Datum> datalist) {
			
			//System.out.println(datalist.size());
			
			double bestAvgEntropy = Integer.MAX_VALUE;
			int bestAttribute = -1;
			double bestThreshold = -1;
			
			for (int i = 0; i < datalist.get(0).x.length; i++) {
				
				for (int z = 0; z < datalist.size(); z++) {
					
					//[0] = Below, [1] = EqualAbove 
					//[#][0] = label 0, [#][1] = label 1
					int[][] entropyData = new int[2][2]; 	
					
					for (Datum d: datalist) {
						
						if (d.x[i] < datalist.get(z).x[i]) {
							entropyData[0][d.y]++;
						}
						else
							entropyData[1][d.y]++;
						
					}
										
						//First half 
						int sectionSize = entropyData[0][0] + entropyData[0][1];
						double left = (double) entropyData[0][0]/sectionSize;
						double right = (double) entropyData[0][1]/sectionSize;
						
						
						double currentEntropy1 = 0;
						if (left != 0) 
							currentEntropy1 += left * (Math.log10(left)/Math.log10(2));
						if (right != 0)
							currentEntropy1 += right * (Math.log10(right)/Math.log10(2));
						currentEntropy1 *= sectionSize * -1;
						currentEntropy1 /= datalist.size();
						
						//Second half
						sectionSize = entropyData[1][0] + entropyData[1][1];
						left = (double) entropyData[1][0]/sectionSize;
						right = (double) entropyData[1][1]/sectionSize;
						
						double currentEntropy2 = 0;
						if (left != 0) 
							currentEntropy2 += left * (Math.log10(left)/Math.log10(2));
						if (right != 0)
							currentEntropy2 += right * (Math.log10(right)/Math.log10(2));
						currentEntropy2 *= sectionSize * -1;
						currentEntropy2 /= datalist.size();
						
						double currentEntropy = currentEntropy1 + currentEntropy2;
							
						if (bestAvgEntropy > currentEntropy) {
							bestAvgEntropy = currentEntropy;
							bestAttribute = i;
							bestThreshold = datalist.get(z).x[i];
						}
					}
				
			}
			
			//System.out.println(bestAvgEntropy + " " + bestAttribute + " " + bestThreshold);
			
			double[] results = {bestAttribute, bestThreshold};
			
			return results;
		}



		// This is a helper method. Given a datalist, this method returns the label that has the most
		// occurrences. In case of a tie it returns the label with the smallest value (numerically) involved in the tie.
		int findMajority(ArrayList<Datum> datalist) {
			
			int [] votes = new int[2];

			//loop through the data and count the occurrences of datapoints of each label
			for (Datum data : datalist)
			{
				votes[data.y]+=1;
			}
			
			if (votes[0] >= votes[1])
				return 0;
			else
				return 1;
		}




		// This method takes in a datapoint (excluding the label) in the form of an array of type double (Datum.x) and
		// returns its corresponding label, as determined by the decision tree
		int classifyAtNode(double[] xQuery) {
			
			DTNode currentNode = rootDTNode;
				
				while (currentNode.leaf == false) {
					
					if (xQuery[currentNode.attribute] < currentNode.threshold) {
						currentNode = currentNode.left;
					} else {
						currentNode = currentNode.right;
					}
					
				}

			
			return currentNode.label;
			
		}


		//given another DTNode object, this method checks if the tree rooted at the calling DTNode is equal to the tree rooted
		//at DTNode object passed as the parameter
		public boolean equals(Object dt2)
		{

			if (dt2 instanceof DTNode ) {
				
				DTNode Node = (DTNode) dt2;
					
					if (Node.leaf == this.leaf) {	//Check if leafs are equal
						
						if (Node.leaf == true) {	//If leaf, check labels
							if (Node.label == this.label)
								return true;
							else
								return false;
							
						} else {						//If internal node, check threshold and attributes and traversal

							//Checks previous and next node to see if equal
							//Edge cases of null
							if (Node.right == null || this.right == null) {
								if (Node.right == null && this.right == null) {
									return true;
								}
							}
							else if (!((Node.right).equals(this.right))) {	
								return false;
							}

							if (Node.left == null || this.left == null) {
								if (Node.left == null && this.left == null) {
									return true;
								}
							}
							else if (!((Node.left).equals(this.left))){
								return false;
							}
							


							if (!(Node.attribute == this.attribute && (double) Node.threshold == (double) this.threshold))	//Check attribute and threshold
								return false;
							
							return true;
					}
					
				}
				
				
			}

			return false; 
		}
	}



	//Given a dataset, this returns the entropy of the dataset
	double calcEntropy(ArrayList<Datum> datalist) {
		double entropy = 0;
		double px = 0;
		float [] counter= new float[2];
		if (datalist.size()==0)
			return 0;
		double num0 = 0.00000001,num1 = 0.000000001;

		//calculates the number of points belonging to each of the labels
		for (Datum d : datalist)
		{
			counter[d.y]+=1;
		}
		//calculates the entropy using the formula specified in the document
		for (int i = 0 ; i< counter.length ; i++)
		{
			if (counter[i]>0)
			{
				px = counter[i]/datalist.size();
				entropy -= (px*Math.log(px)/Math.log(2));
			}
		}

		return entropy;
	}


	// given a datapoint (without the label) calls the DTNode.classifyAtNode() on the rootnode of the calling DecisionTree object
	int classify(double[] xQuery ) {
		return this.rootDTNode.classifyAtNode( xQuery );
	}

	// Checks the performance of a DecisionTree on a dataset
	// This method is provided in case you would like to compare your
	// results with the reference values provided in the PDF in the Data
	// section of the PDF
	String checkPerformance( ArrayList<Datum> datalist) {
		DecimalFormat df = new DecimalFormat("0.000");
		float total = datalist.size();
		float count = 0;

		for (int s = 0 ; s < datalist.size() ; s++) {
			double[] x = datalist.get(s).x;
			int result = datalist.get(s).y;
			if (classify(x) != result) {
				count = count + 1;
			}
		}

		return df.format((count/total));
	}


	//Given two DecisionTree objects, this method checks if both the trees are equal by
	//calling onto the DTNode.equals() method
	public static boolean equals(DecisionTree dt1,  DecisionTree dt2)
	{
		boolean flag = true;
		flag = dt1.rootDTNode.equals(dt2.rootDTNode);
		return flag;
	}

}
