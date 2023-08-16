class SelectionSort{

	/*
	 * find smallest and put in the beginning
	 */

	static void swap(int x[], int i, int j){
		System.out.println("Before Swapping: "+x[i]+""+x[j]);
		int temp=x[i];
		x[i]=x[j];
		x[j]=temp;
	System.out.println("After Swapping: "+x[i]+""+x[j]);
	}
	
	public static void main(String[] args){
		int x[]={3,4,55,34,23,2,32,55,32,22,0};
		
		for(int i=0; i<x.length; i++){
			int minIndex = i;
			for(int j=i; j<x.length; j++){
				if(x[j]<=x[minIndex]){
					minIndex=j;
				}
			}
			swap(x,i,minIndex);
		}

	
	for(int t:x){
		System.out.print(t+" ");
	}		

	}
	
}