public class WeightedQU {
    private int arr[];
    private int size[];

    public WeightedQU(int n){
        arr=new int[n];
        size=new int[n];
        for(int i=0; i<n; i++){
            arr[i]=i;
            size[i]=1;
        }
    }

    public int root(int i){
        while(i!=arr[i]){
            i=arr[i];
            //if we want to flaten the tree and connect every node to the top node we use the following line:
            arr[i]=arr[arr[i]];
        }
        
        return i;
    }

    public boolean isConnected(int i, int j){
        return root(i)==root(j);
    }

    public void connect(int i, int j){
        int iSize = size[i];
        int jSize = size[j];
        int iRoot = root(i);
        int jRoot = root(j);
        if(iSize<jSize){
            arr[iRoot]=jRoot;
            size[jRoot]=size[iRoot]+size[jRoot];
            changeSizeOfAllBelowElements(jRoot);
        }
        else{
            arr[jRoot]=iRoot;
            size[iRoot]=size[iRoot]+size[jRoot];
            changeSizeOfAllBelowElements(iRoot);
        }



    }

    private void changeSizeOfAllBelowElements(int root) {
        for(int i=0; i<size.length; i++){
            if(root(i)==root){
                size[i]=size[root];
            }
        }
    }

    public static void main(String[] args) {
        WeightedQU wqu = new WeightedQU(8);
        wqu.connect(0, 3);
        wqu.connect(1, 2);
        wqu.connect(1, 5);
        wqu.connect(3, 5);
        System.out.println(wqu.isConnected(2, 5));
        for(int i=0; i<wqu.size.length; i++){
            System.out.println(i+"'s root: "+wqu.root(i));
            System.out.println(i+"'s size: "+wqu.size[i]);
            System.out.println();
        }
    }

}
