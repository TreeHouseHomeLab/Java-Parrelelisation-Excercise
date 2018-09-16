
public class Timer {
	private long StartTime;
	
	public void tic(){ StartTime = System.nanoTime(); }
	
	public long toc() {return System.nanoTime() - StartTime;}
	
	public double toSeconds(){
	      return ((double)(System.nanoTime()-StartTime))*Math.pow(10,-9);
	    }
}
