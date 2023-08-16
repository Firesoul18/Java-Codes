import java.util.Random;
class MonteCarloPercolation{
	private static Random random;
	private static WeightedQU qu;
	private static boolean[] isOpen;
	public static void main(String args[]){
		qu = new WeightedQU(8*8+2);
		isOpen = new boolean[66];
		isOpen[0] = true;
		isOpen[65] = true;

		random = new Random();
		int topRandomIndex = random.nextInt(8)+1;
		int bottomRandomIndex = random.nextInt(8)+57;
		
		qu.connect(0,topRandomIndex);
		isOpen[topRandomIndex]=true;

		qu.connect(65,bottomRandomIndex);
		isOpen[bottomRandomIndex]=true;

		while(!qu.isConnected(0,65)){
			openRandom();
		}

		for(int i=0; i<66; i++){
			System.out.print(isOpen[i]+" ");
			if(i%8==0)
			System.out.println();
		}		

	}


	public static void openRandom(){
		int r = random.nextInt(64)+1;
		System.out.println(r);
		if(isOpen[r]){
			return;
		}
		if((r-1)%8!=0&&(r-1)>0&&isOpen[r-1]){
			qu.connect(r-1,r);
		}
		if((r-8)>0&&isOpen[r-8]){
			qu.connect(r-8,r);
		}
		if((r+8)<65&&isOpen[r+8]){
			qu.connect(r+8,r);
		}
		if((r+1)<65&&(r%8)!=0&&isOpen[r+1]){
			qu.connect(r+1,r);
		}
		isOpen[r]=true;
	}


}


/*
		   0

	1  2  3  4  5  6  7  8
	9  10 11 12 13 14 15 16
	17 18 19 20 21 22 23 24
	25 26 27 28 29 30 31 32
	33 34 35 36 37 38 39 40
	41 42 43 44 45 46 47 48
	49 50 51 52 53 54 55 56
	57 58 59 60 61 62 63 64

		  65
*/