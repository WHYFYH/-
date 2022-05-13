import java.util.*;

public class Life {
	public static void main(String [] arges) throws InterruptedException {
		int length=0,high=0;
		Scanner in=new Scanner(System.in);
		System.out.print("请输入棋盘大小(m,n):");
		length=in.nextInt();
		high=in.nextInt();
		Map map=new Map(length,high);
		map.Init();
		System.out.print("请输入细胞更新时间:");
		int t;
		t=in.nextInt();
		Time time=new Time(t);
		while(true) {
			map.Show();
			map.Update();
			time.sleep();
			System.out.println("-------------------------------");
		}
	}
}
class Map{
	private int[][] Cells;
	private int row;
	private int col;
	Map(int length,int high){
		this.row=high;
		this.col=length;
		Cells=new int[this.row][this.col];
	}
	public void Init() {
		for(int i=0;i<row;i++)
			for(int j=0;j<col;j++)
			{
				Cells[i][j] = (int)(Math.random()*200)%2;
			}
	}
	public int neighbors(int y, int x) {
		int num=0;
        //左边
        if(x!=0){
            num+=Cells[y][x-1];
        }
        //左上角
        if(x!=0&&y!=0){
            num+=Cells[y-1][x-1];
        }
        //上边
        if(y!=0){
            num+=Cells[y-1][x];
        }
        //右上
        if(x!=col-1&&y!=0){
            num+=Cells[y-1][x+1];
        }
        //右边
        if(x!=col-1){
            num+=Cells[y][x+1];
        }
        //右下
        if(x!=col-1&&y!=row-1){
            num+=Cells[y+1][x+1];
        }
        //下边
        if(y!=row-1){
            num+=Cells[y+1][x];
        }
        //左下
        if(x!=0&&y!=row-1){
            num+=Cells[y+1][x-1];
        }
        return num;
	} 
	public void Update() {
		int[][] tempCells=new int[this.row][this.col]; 
		for (int row = 0; row < this.row; row++)
	    {
	      for (int col = 0; col < this.col; col++)
	      {
	        switch (neighbors(row, col))
	        {
	          case 0:
	          case 1:
	          case 4:
	          case 5:
	          case 6:
	          case 7:
	          case 8:
	        	  tempCells[row][col] = 0;
	            break;
	          case 2:
	        	  tempCells[row][col] = Cells[row][col];
	            break;
	          case 3:
	        	  tempCells[row][col] = 1;
	            break;
	        }
	      }
	    }
		for (int i = 0; i <this.row ; ++i) 
			for (int j = 0; j <this.col ; ++j) 
				Cells[i][j] = tempCells[i][j];
	}
	/*public void Update() {
		int[][] tempCells=new int[high][length]; 
		int NeighbourNum;   //周边细胞的数目
		//边界处理
		NeighbourNum=Cells[0][1]+Cells[1][1]+Cells[1][0];
		if (NeighbourNum == 3)  //通过修改这里的条件, 可以改变生命游戏中的繁衍情况 
			tempCells[0][0] = 1;
		else if (NeighbourNum == 2)  //通过修改这里的条件, 可以改变生命游戏中的繁衍情况 
			tempCells[0][0] = Cells[0][0];
		else tempCells[0][0] = 0;
		
		NeighbourNum=Cells[0][length-2]+Cells[1][length-2]+Cells[1][length-1];
		if (NeighbourNum == 3)  //通过修改这里的条件, 可以改变生命游戏中的繁衍情况 
			tempCells[0][length-1] = 1;
		else if (NeighbourNum == 2)  //通过修改这里的条件, 可以改变生命游戏中的繁衍情况 
			tempCells[0][length-1] = Cells[0][length-1];
		else tempCells[0][length-1] = 0;
		
		NeighbourNum=Cells[high-2][0]+Cells[high-1][1]+Cells[high-2][1];
		if (NeighbourNum == 3)  //通过修改这里的条件, 可以改变生命游戏中的繁衍情况 
			tempCells[high-1][0] = 1;
		else if (NeighbourNum == 2)  //通过修改这里的条件, 可以改变生命游戏中的繁衍情况 
			tempCells[high-1][0] = Cells[high-1][0];
		else tempCells[high-1][0] = 0;
		
		NeighbourNum=Cells[high-1][length-2]+Cells[high-2][length-2]+Cells[high-2][length-1];
		if (NeighbourNum == 3)  //通过修改这里的条件, 可以改变生命游戏中的繁衍情况 
			tempCells[high-1][length-1] = 1;
		else if (NeighbourNum == 2)  //通过修改这里的条件, 可以改变生命游戏中的繁衍情况 
			tempCells[high-1][length-1] = Cells[high-1][length-1];
		else tempCells[high-1][length-1] = 0;
		
		for(int i=1;i<length-1;i++) {
			NeighbourNum=Cells[0][i-1]+Cells[0][i+1]+Cells[1][i-1]+Cells[1][i]+Cells[1][i+1];
			if (NeighbourNum == 3)  //通过修改这里的条件, 可以改变生命游戏中的繁衍情况 
				tempCells[0][i] = 1;
			else if (NeighbourNum == 2)  //通过修改这里的条件, 可以改变生命游戏中的繁衍情况 
				tempCells[0][i] = Cells[0][i];
			else tempCells[0][i] = 0;
		}
		
		for(int i=1;i<length-1;i++) {
			NeighbourNum=Cells[high-1][i-1]+Cells[high-1][i+1]+Cells[high-2][i-1]+Cells[high-2][i]+Cells[high-2][i+1];
			if (NeighbourNum == 3)  //通过修改这里的条件, 可以改变生命游戏中的繁衍情况 
				tempCells[high-1][i] = 1;
			else if (NeighbourNum == 2)  //通过修改这里的条件, 可以改变生命游戏中的繁衍情况 
				tempCells[high-1][i] = Cells[high-1][i];
			else tempCells[high-1][i] = 0;
		}
		
		for(int i=1;i<high-1;i++) {
			NeighbourNum=Cells[i-1][0]+Cells[i+1][0]+Cells[i-1][1]+Cells[i][1]+Cells[i+1][1];
			if (NeighbourNum == 3)  //通过修改这里的条件, 可以改变生命游戏中的繁衍情况 
				tempCells[i][0] = 1;
			else if (NeighbourNum == 2)  //通过修改这里的条件, 可以改变生命游戏中的繁衍情况 
				tempCells[i][0] = Cells[i][0];
			else tempCells[i][0] = 0;
		}
		for(int i=1;i<high-1;i++) {
			NeighbourNum=Cells[i-1][length-1]+Cells[i+1][length-1]+Cells[i-1][length-2]+Cells[i][length-2]+Cells[i+1][length-2];
			if (NeighbourNum == 3)  //通过修改这里的条件, 可以改变生命游戏中的繁衍情况 
				tempCells[i][length-1] = 1;
			else if (NeighbourNum == 2)  //通过修改这里的条件, 可以改变生命游戏中的繁衍情况 
				tempCells[i][length-1] = Cells[i][length-1];
			else tempCells[i][length-1] = 0;
		}
		
		for (int i = 1; i < high-1 ; ++i) {
			for (int j = 1; j < length-1 ; ++j) {
				NeighbourNum = Cells[i - 1][j - 1] + Cells[i - 1][j] + Cells[i - 1][j + 1] +
							   Cells[i][j - 1] + Cells[i][j] + Cells[i][j + 1] +
							   Cells[i + 1][j - 1] + Cells[i + 1][j] + Cells[i + 1][j + 1]; 
				if (NeighbourNum == 3)  //通过修改这里的条件, 可以改变生命游戏中的繁衍情况 
					tempCells[i][j] = 1;
				else if (NeighbourNum == 2)  //通过修改这里的条件, 可以改变生命游戏中的繁衍情况 
					tempCells[i][j] = Cells[i][j];
				else tempCells[i][j] = 0;
			}
		}
		for (int i = 0; i <high ; ++i) 
			for (int j = 0; j <length ; ++j) 
				Cells[i][j] = tempCells[i][j]; 
	}*/
	public void Show() {
		for(int i=0;i<this.row;i++) {
			for(int j=0;j<this.col;j++) {
				if (Cells[i][j]==1)
					System.out.print("*");
				else
					System.out.print("0");
			}
			System.out.print("\n");
		}
	}
}

class Rules{
	
}

class Time{
	private int time;
	Time(int time){
		this.time=time;
	}
	public void sleep() throws InterruptedException
	{
		Thread.sleep(time);
	}
}

