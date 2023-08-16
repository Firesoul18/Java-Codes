
//it checks the current and next element and change the location. process keeps going and after each loop the highest element is in the end



class BubbleSort{

	static void swap(int x[],int i,int j){
		int temp=x[i];
		x[i]=x[j];
		x[j]=temp;
	}

	public static void main(String args[]){
		int x[] = {3,0,23,11,8,2,19,23,32,2};
		int sorted=0;
		for(int i=0; i<x.length; i++){
			for(int j=0; j<x.length-sorted-1; j++){
				if(x[j]>x[j+1]){
					swap(x,j,j+1);
				}
			}
			sorted++;
		}
		

	for(int g:x){
		System.out.print(g+" ");
	}
	}
}