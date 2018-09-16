
public class TreeData {
	int X1;
	int Y1;
	int coverage;
	double Sunlight=0;
	
	public int[][] treeData;
	
	public TreeData(TreeData t) {
		// TODO Auto-generated constructor stub
		X1=t.getX(); Y1=t.getY();	coverage = t.getC();}
	
	public int getX() {return X1;}
	public int getY() {return Y1;}	
	public int getC() {return coverage;}
	
	public TreeData(int x,int y,int cover) {
		// TODO Auto-generated constructor stub
		X1=x; Y1=y;	coverage = cover;
	}
	public void setSun(double v) { 
		if(this.Sunlight == 0)
			{this.Sunlight = v;}	
		else {System.exit(0);}}
	
	public double getSun() {return this.Sunlight;}
	
	  public String toString(){
	        return "("+X1+", "+Y1+") @"+coverage+"m = "+Sunlight;
	    }
	
}
