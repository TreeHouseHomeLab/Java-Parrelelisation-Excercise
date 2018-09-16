import java.util.ArrayList;
import java.util.concurrent.RecursiveTask;
public class ParallelAnalysis extends RecursiveTask<Double> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static int SEQUENTIAL_THRESHOLD = 1000000/4;
	int start;
	int end;
	ArrayList<TreeData> trees = new ArrayList<TreeData>();
	TreeData tree;
	double treeAnswer;
	
	public ParallelAnalysis(ArrayList<TreeData> a, int s, int e) { this.start=s; this.end=e; this.trees=a; }
	
	
	@Override
	public Double compute() {
		if(end - start <= SEQUENTIAL_THRESHOLD) {
			double ans = 0;
			for(int i=start; i < end; ++i) {	
				tree = new TreeData(trees.get(i));
				
                for(int y = tree.getY(); y<tree.getY()+tree.getC(); y++){
                    for(int x = tree.getX(); x<tree.getX()+tree.getC(); x++){
                        if(y>=SunlightAnalysis.LightData.getYSize() || x>=SunlightAnalysis.LightData.getXSize()){
                            break;
                        }else{
                            treeAnswer += SunlightAnalysis.LightData.getData(tree.getX(), tree.getY());
                        }
                    }
                }
				tree.setSun(treeAnswer);
				ans +=treeAnswer;
			}
			return ans;
			
		} else {
			
			ParallelAnalysis left = new ParallelAnalysis(trees,start,(end+start)/2);
			ParallelAnalysis right = new ParallelAnalysis(trees,(end+start)/2,end);
			
			
			left.fork();
			Double rightAns = right.compute();
			Double leftAns = left.join();
			return leftAns + rightAns;
			}
		
	}

}
