import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class SunlightAnalysis  extends RecursiveTask<Integer> {
	static int SEQUENTIAL_THRESHOLD = 1000;
	File inputF;
    File outputF;
    Scanner input;
    String data;
    double[] SquareMetreValues; 
    int[] size = new int[2];
    int noTrees;
    int[][] LightData;
    int[][] treeData;
    String[] dataA;   
    int blockSize;
    
    
	public SunlightAnalysis(String input,String output) {
		// TODO Auto-generated constructor stub
        inputF = new File(input);
        outputF = new File(output);
        readInFile(inputF);
	}

	  private void readInFile(File textFile){
		  
		  try {
	            input = new Scanner(textFile);

	            //read first string into variable teamName
	            data = input.nextLine();
	            dataA = data.split(" ");
	            int i=0;
	            int j = 0;
	            int l =0;
	            int gridData=0;
	            //iterate through rest of file adding it to an ArrayList
	            while(input.hasNext()){
	            	if (i==0) {
	            		size[0] = Integer.parseInt(dataA[0]); 
	            		size[1] = Integer.parseInt(dataA[1]); 
	            		blockSize = size[0]*size[1];
	            		LightData = new int[size[0]][size[1]];
	            		}
	            	else if (i==1) {
	            		for (int k = 0; k<blockSize; k++) {
	            			 while(j<=size[0]) {
	            				 LightData[j][gridData] = Integer.parseInt(dataA[i]); 
	            				 k++;
	            				 if(j == size[0]) {gridData++;}
	            				 j++;
	            		}
	            			 j=0;
	            			 }}
	            	else if (i==2) {noTrees=  Integer.parseInt(dataA[0]);
	            					treeData = new int[3][noTrees];
	            	}
	            	else {
	            	for (int count = 0;count<=noTrees;count++) {
	            		while( l<3)
	            				{treeData[l-1][count] = Integer.parseInt(dataA[l-1]);
	            					l++;}
	            		l=0;
	            	}
	            	}
	            		            
	            }
	            	i++;
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	    }
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	protected Integer compute() {
		if(hi - lo <= SEQUENTIAL_THRESHOLD) {
			int ans = 0;
			for(int i=lo; i < hi; ++i)
				ans += arr[i];
			return ans;
	} 	else {
			SumArray left = new SumArray(arr,lo,(hi+lo)/2);
			SumArray right = new SumArray(arr,(hi+lo)/2,hi);
			left.fork();
			int rightAns = right.compute();
			int leftAns = left.join();
			return leftAns + rightAns;
	}

}
