public class EagerAlgorithm {
    //this is also called QuickFind
    private int arr[];
    public EagerAlgorithm(int n){
        arr=new int[n];
        for(int i=0; i<n; i++){
            arr[i]=i;
        }
    }

    public boolean isConnected(int a, int b){
        if(arr[a]==arr[b]){
            return true;
        }
        return false;
    }

    public void connect(int a, int b){
        int aVal = arr[a];
        for(int i=0; i<arr.length; i++){
            if(arr[i]==aVal){
                arr[i]=arr[b];
            }
        }
    }

    public static void main(String args[]){
        EagerAlgorithm ea = new EagerAlgorithm(9);
        ea.connect(1, 3);
        ea.connect(7, 1);
        ea.connect(5, 0);
        System.out.println(ea.isConnected(3, 7));
    }
}
