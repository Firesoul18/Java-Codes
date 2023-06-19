import java.util.*;
class InsertionSort{

	static void interChange(ArrayList<Integer> y, int a, int b){
		int temp = y.get(b);
		y.set(b,y.get(a));
		y.set(a,temp);
	}
	
	public static void main(String args[]){
		
		int x[] = {12,3,4,85,20,22,3,9,0,12};
		ArrayList<Integer> y = new ArrayList<>();
		for(int i=0; i<x.length; i++){
			y.add(x[i]);
			for(int j=0; j<y.size(); j++){
				if(x[i]<y.get(j)){
					interChange(y,i,j);
				}
			}
		}
		
	for(Integer t:y){
		System.out.print(t+" ");
	}
		
	}

}