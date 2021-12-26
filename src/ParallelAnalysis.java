import java.util.ArrayList;
import java.util.concurrent.RecursiveTask;
public class ParallelAnalysis extends RecursiveTask<Double> {

	static int SEQUENTIAL_THRESHOLD;
	int start;
	int end;
	ArrayList<TreeData> trees;
	
	public ParallelAnalysis(ArrayList<TreeData> a, int s, int e) { this.start=s; this.end=e; this.trees=a; SEQUENTIAL_THRESHOLD =SunlightAnalysis.SEQUENTIAL_THRESHOLD1;}
	
	
	@Override
	public Double compute() {
		if(end - start <= SEQUENTIAL_THRESHOLD) {
			double ans = 0;
			for(int i=start; i < end; ++i) {	
				TreeData tree;
				tree = new TreeData(trees.get(i));
				
				double treeAnswer=0;
				
                for(int y = tree.getY(); y<tree.getY()+tree.getC(); y++){
                    for(int x = tree.getX(); x<tree.getX()+tree.getC(); x++){
                        if(y>=SunlightAnalysis.LightData.getYSize() || x>=SunlightAnalysis.LightData.getXSize()){
                            continue;
                        }else{
                            treeAnswer += SunlightAnalysis.LightData.getData(x, y);
                        }
                    }
                }
				trees.get(i).setSun(treeAnswer);
				ans +=treeAnswer;
			}
			return ans;
			
		} else {
			int midpoint = ((end+start)/2);
			ParallelAnalysis left = new ParallelAnalysis(trees,start,midpoint);
			ParallelAnalysis right = new ParallelAnalysis(trees,midpoint,end);
			left.fork();
			Double rightAns = right.compute();
			Double leftAns = left.join();
			return leftAns + rightAns;
			}
		
	}

}
