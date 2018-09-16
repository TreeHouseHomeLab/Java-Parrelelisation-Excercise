import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class SunlightAnalysis {
	static int SEQUENTIAL_THRESHOLD = 1000;
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
    Timer time = new Timer();
    
    
	public SunlightAnalysis(String input,String output) {
		// TODO Auto-generated constructor stub

	}

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
	            	
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	            System.out.println(e+" cannot load File");
	            System.exit(0);
	        }
	    }
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        inputF = new File(args[0]);
        outputF = new File(args[1]);
        readInFile(inputF);
	}

}