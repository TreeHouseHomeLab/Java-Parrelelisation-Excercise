import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class SunlightAnalysis {
	
	static File inputF;
    static File outputF;
    static File output2;
    static Scanner input;
    static String data;
    double[] SquareMetreValues; 
    int[] size = new int[2];
    public static int noTrees;
    static int[] treeData;
    static ArrayList<TreeData> Trees = new ArrayList<TreeData>() ;
    static TreeData tree;
    static map LightData;
    static String[] dataA;   
    static int blockSize;
    static Timer time = new Timer();
    static double[] MostEfficient = new double[3];
    static int SEQUENTIAL_THRESHOLD1 = 10000/4; 
  

	private static void readInFile(File textFile){
		  
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
	            		LightData = new map(Integer.parseInt(dataA[0]),Integer.parseInt(dataA[1]));
	            		blockSize = LightData.getSize();
	            	}
	            	else if (i==1) {
	            		boolean x=false;
	            		
	            		for (int k = 0; k<blockSize; k++) {
	            			if (x==true) {k-=1; x=false;}
	            			 while(j<LightData.getXSize()) {
	            				 LightData.loadMapData(gridData,j,Double.parseDouble(dataA[k])); 
	            				 if (j==3) {x=true;}
	            					 k++;
	            				 
	            				 j++;
	            		}
	            			 if(j == LightData.getXSize()) {gridData++;}
	            			 j=0;
	            			 }}
	            	else if (i==2) {noTrees=  Integer.parseInt(dataA[0]);
	            					treeData = new int[3];
	            	}
	            	else {
	            		boolean x=false;
	         
	            	for (int count = 0;count<noTrees;count++) {
	            		if (x==true) {count-=1; x=false;}
	            		while( l<3)
	            				{
	            			treeData[l] = Integer.parseInt(dataA[l]);
	            					l++;	}
	            		l=0;
	            		
	            		tree = new TreeData(treeData[0],treeData[1],treeData[2]);
	            		Trees.add(tree);
		            	if (input.hasNext()) {
	            		data = input.nextLine();
		 	            dataA = data.split(" ");}
	            	}
	            	}
	            	i++;  
	            	if (input.hasNext()) {
	            	data = input.nextLine();
	 	            dataA = data.split(" ");
	            }}
	            
	            	
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	            System.out.println(e+" cannot load File");
	            System.exit(0);
	        }
		  input.close();   
	}
	
	
	
	public static void main(String[] args) throws IOException {
		MostEfficient[2] = 1000;
		
        if (args.length ==0){
        	//String filePathIn = "../data/" +args[0];
        	//String filePathOut = "../data/" +args[1];
			inputF = new File("D:\\Git Repository\\CSC2002-Assignment3\\Assignment3\\data\\small_sample_input _2.txt");
			readInFile(inputF);
			FileWriter writer = new FileWriter("D:\\Git Repository\\CSC2002-Assignment3\\Assignment3\\data\\sample_output.txt"); 
	        for(int i=-2; i<100; i++){
	            if(i==1){
	            	SEQUENTIAL_THRESHOLD1 = noTrees;
	            }
	            if(i>=1){
	            	SEQUENTIAL_THRESHOLD1 = noTrees/i;

	            }
	            if (i>noTrees) {break;}
	        time.tic();
	        ParallelAnalysis Data = new ParallelAnalysis(Trees,0,noTrees);
	        Double Answer = Data.compute();
	        double elapsed = time.toc(); 

	         // Average Sunlight over trees
			writer.append(i+","+SEQUENTIAL_THRESHOLD1+","+elapsed+"\n");
	        
	        if (elapsed<MostEfficient[2] && i>0) { MostEfficient[0] = i;	MostEfficient[1]= SEQUENTIAL_THRESHOLD1; MostEfficient[2] =elapsed;}
	        
	        }
	        writer.flush();
	        writer.close();
	       
	       
	        SEQUENTIAL_THRESHOLD1 = noTrees/(int)MostEfficient[0];
	        ParallelAnalysis Data = new ParallelAnalysis(Trees,0,noTrees);
	        Double Answer = Data.compute();
	        FileWriter writer2 = new FileWriter("D:\\Git Repository\\CSC2002-Assignment3\\Assignment3\\data\\sample_output_Vals.txt"); 
	        
	        writer2.append(Double.toString(Answer/(double)noTrees)+"\n");
	        writer2.append(Integer.toString(noTrees)+"\n");
	        for(TreeData tree : Trees){
	        	writer2.append(Double.toString(tree.getSun())+"\n");
            }
	        writer2.flush();
	        writer2.close();
	        System.out.println("Most Efficient: "+"\n"+"Threads: "+MostEfficient[0]+"\n"+"Sequential Cutoff: "+MostEfficient[1]+"\n"+"Elapsed Time: "+MostEfficient[2]);
        }
	}
}