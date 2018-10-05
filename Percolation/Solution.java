package ADC;
 class WeightedQuickUnion
{
    private int[] id;    
    private int[] sz;    
    private int count;   
    
    public WeightedQuickUnion(int N) {
        count = N;
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }
    public int count() 
    {
        return count;
    }
    public int find(int p)
    {
        while (p != id[p])
            p = id[p];
        return p;
    }
    public boolean connected(int p, int q) 
    {
        return find(p) == find(q);
    }
    public void union(int p, int q)
    {
        int i = find(p);
        int j = find(q);
        if (i == j) return;
        if   (sz[i] < sz[j])
        {
        	id[i] = j; sz[j] += sz[i];
        }
        else 
        { 
        	id[j] = i; sz[i] += sz[j]; 
        }
        count--;
     }
}
public class Solution {
	private static final boolean OPEN = true;
	private static final boolean FULL = false;
	private int N;
	private boolean[][] grid; 
	WeightedQuickUnion uf;
	 public Solution(int n) 
	 {
		this.N=n;
		
		 grid =new boolean[N][N];
		 uf=new WeightedQuickUnion((N*N)+2);
	 }
	 public void open(int row,int col)
	 {
		 int i=row-1;
		 int j=col-1;
		 if(i<0||i>N||j<0||j>N)
		 {
		 throw new IndexOutOfBoundsException(); 
		 }
		 grid[i][j]=OPEN;
		 if(i==0)
		 {
			uf.union(0, index(i,j)); 
		 }
		 if(i==N-1)
		 {
			 uf.union((N*N)+1, index(i,j));
		 }
		 if ((i + 1) < N) 
		 { 
	       if (grid[i+1][j] == OPEN)
	        uf.union(index(i,j), index(i+1, j));
	     }
		 if((i-1)>=0)
		 {
			 if(grid[i-1][j])
			 {
				 uf.union(index(i,j), index(i-1,j));
			 }
		 }
		 if((j+1)<N)
		 {
			 if(grid[i][j+1]==OPEN)
			 {
				 uf.union(index(i,j), index(i,j+1));
			 }
		 }
		 if((j-1)>=0)
		 {
			 if(grid[i][j-1]==OPEN)
			 {
				 uf.union(index(i,j), index(i,j-1));
			 }
		 }
	 }
	 public boolean isOpen(int row, int col)
	 {
		 int i=row-1;
		 int j=col-1;
		 
		 return grid[i][j];
	 }
	public boolean isFull(int row, int col)
	{
		int i=row-1;
		int j=col-1;
		return grid[i][j];
		
	}
	public int numberOfOpenSites() 
	{
		return N;
		
	}
	
	private int index(int i, int j)
	{
		
		return (i*N)+j;
	}
	public boolean percolates() 
	{
		return uf.connected(0, (N*N)+1);
	}
}
