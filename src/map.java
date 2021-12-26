
public class map {
int x;
int y;
double Data[][];
	
	
	
	public map(int a, int b) { x=a; y=b; Data = new double[a][b];
		// TODO Auto-generated constructor stub
	}
	public int getSize() {return x*y;}
	public int getXSize() {return x;}
	public int getYSize() {return y;}
	
	public double getData(int xx,int yy) { return Data[xx][yy];}
	
	
	public void loadMapData(int x1, int y1, double Val) {
		Data[x1][y1] = Val;
		
	}

}
