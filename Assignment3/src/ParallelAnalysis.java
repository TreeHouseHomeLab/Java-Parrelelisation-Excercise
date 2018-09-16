import java.util.ArrayList;
import java.util.concurrent.RecursiveTask;
public class ParallelAnalysis extends RecursiveTask<Double> {
	static int SEQUENTIAL_THRESHOLD = 1000000/4;
	int start;
	int end;
	ArrayList<TreeData> trees = new ArrayList<TreeData>();
	
	
	public ParallelAnalysis(ArrayList<TreeData> a, int s, int e) { this.start=s; this.end=e; this.trees=a; }
	
	
	@Override
	public Double compute() {
		if(end - start <= SEQUENTIAL_THRESHOLD) {
			double ans = 0;
			for(int i=start; i < end; ++i)	
			ans += trees[i];
			return ans;
			
		} else {
			
			ParallelAnalysis left = new ParallelAnalysis(trees,start,(end+start)/2);
			ParallelAnalysis right = new ParallelAnalysis(trees,(end+start)/2,end);
			
			
			left.fork();
			Double rightAns = right.compute();
			Double leftAns = left.join();
			return leftAns + rightAns;
			}
		return null;
	}

}
