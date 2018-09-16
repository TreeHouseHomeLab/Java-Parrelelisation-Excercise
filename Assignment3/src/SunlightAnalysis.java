import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class SunlightAnalysis {
	
	static File inputF;
    static File outputF;
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
	            		for (int k = 0; k<blockSize; k++) {
	            			 while(j<=LightData.getXSize()) {
	            				 LightData.loadMapData(j,gridData,Integer.parseInt(dataA[i])); 
	            				 k++;
	            				 if(j == LightData.getXSize()) {gridData++;}
	            				 j++;
	            		}
	            			 j=0;
	            			 }}
	            	else if (i==2) {noTrees=  Integer.parseInt(dataA[0]);
	            					treeData = new int[3];
	            	}
	            	else {
	            	for (int count = 0;count<=noTrees;count++) {
	            		while( l<3)
	            				{
	            			treeData[l-1] = Integer.parseInt(dataA[l-1]);
	            					l++;}
	            		l=0;
	            		tree = new TreeData(treeData[0],treeData[1],treeData[2]);
	            		Trees.add(tree);
	            	}
	            	}
	            	i++;            
	            }
	            input.close();
	            	
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	            System.out.println(e+" cannot load File");
	            System.exit(0);
	        }
	    }
	
	
	
	public static void main(String[] args) throws IOException {
		MostEfficient[3] = 1000;
        if (args.length !=0){
			inputF = new File(args[0]);
			readInFile(inputF);
			FileWriter writer = new FileWriter(args[1]); 
	        for(int i=-2; i<100; i++){
	            if(i==1){
	            	ParallelAnalysis.SEQUENTIAL_THRESHOLD = noTrees;
	            }
	            if(i>1){
	            	ParallelAnalysis.SEQUENTIAL_THRESHOLD = noTrees/i;

	            }
	        time.tic();
	        ParallelAnalysis Data = new ParallelAnalysis(Trees,0,noTrees);
	        double Answer = Data.compute();
	        double elapsed = time.toc(); 
	         // Average Sunlight over trees
			writer.append(i+","+ParallelAnalysis.SEQUENTIAL_THRESHOLD+","+elapsed+"\n");
	        
	        if (elapsed<MostEfficient[3]) { MostEfficient[1] = i;	MostEfficient[2]= ParallelAnalysis.SEQUENTIAL_THRESHOLD; MostEfficient[3] =elapsed;}
	        
	        }
	        writer.flush();
	        writer.close();
	        System.out.println("Most Efficient: "+"\n"+"Threads: "+MostEfficient[1]+"\n"+"Sequential Cutoff: "+MostEfficient[2]+"\n"+"Elapsed Time: "+MostEfficient[3]);
	       
	        ParallelAnalysis.SEQUENTIAL_THRESHOLD = noTrees/(int)MostEfficient[1];
	        ParallelAnalysis Data = new ParallelAnalysis(Trees,0,noTrees);
	        Double Answer = Data.compute();
	        System.out.println(Answer/(double)noTrees);
	        System.out.println(noTrees);
	        for(TreeData tree : Trees){
                System.out.printf("%.6f\n",(float)tree.getSun());
            }
	        
        
        }
	}
}