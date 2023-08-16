/**
 * In this kind of sorting array is divided into two parts first part is compared with second part's first element and then put in the first part sorted.
 * then the size of first part increases by one and second part decreases by one and loops keep going
 * {21,3,55,19,9}
 * {21 | 3,55,19,9}
 * {3, 21 | 55,19,9}
 * {3,21,55 | 19,9}
 * {3,19,21,55 | 9}
 * {3,9,19,21,55}
 */
class InsertionSort{

	static void interChange(int[] y, int a, int b){
		int temp = y[b];
		y[b]=y[a];
		y[a]=temp;
	}
	
	public static void main(String args[]){
		
		int x[] = {12,3,4,85,20,22,3,9,0,12};
		int loc =0;
		for(int i=0; i<x.length; i++){
			loc++;
			for(int j=0; j<loc; j++){
				if(x[i]<x[j]){
					interChange(x,i,j);
				}
			}
		}
		
	for(int t:x){
		System.out.print(t+" ");
	}
		
	}

}