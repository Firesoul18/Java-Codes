public class QuickUnion {
    private int arr[];

    //it is just more structured but still it's o(n) for all operations
    public QuickUnion(int s){
        arr=new int[s];
        for(int i=0; i<s; i++){
            arr[i]=i;
        }
    }

    public int root(int i){
        while(arr[i]!=i){
            i=arr[i];
        }
        return i;
    }

    public boolean isConnected(int a, int b){
        if(root(b)==root(a)){
            return true;
        }
        else return false;
    }

    public void connect(int i, int j){
        //we will not connect directly because in case of two sets already with more than two elements it is absurd to just change arr[i] to j
        /*
            for example:-
                            1       3
                            |       |
                            2       0

                            in this example, if we call connect(2,0). it is absurd to connect 2 to 0 because it will break our root structure
                            So, we will instead add 1(2's root) to 3(0's root). Like this,
                            we have connected 2 and 0 (not directly but still connected).
         */

         int iRoot = root(i);
         int jRoot = root(j);
         arr[iRoot]=jRoot;

    }

    public static void main(String[] args) {
        QuickUnion q = new QuickUnion(5);
        q.connect(0, 3);
        q.connect(3, 4);
        System.out.println(q.isConnected(0, 4));
    }

}
