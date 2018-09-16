
public class Timer {
	private long StartTime;
	
	public void tic(){ StartTime = System.nanoTime(); }
	
	public double toc() {return ((double) (System.nanoTime()-StartTime))*Math.pow(10,-9);}
	

}
