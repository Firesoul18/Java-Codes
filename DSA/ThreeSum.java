import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ThreeSum{
    public static void main(String[] args) {
        List<List<Integer[]>> indexes = new ArrayList<>(); 
        int target = 30;
        int arr[]={-40,-30,-20,-20,-10,0,10,20,30,40,50};
                  /*0    1   2   3   4 5  6  7  8  9 10*/

        for(int i=0; i<arr.length-2; i++){
            if(i>0&&arr[i]==arr[i+1]){
                continue;
            }
            List<Integer[]> triplets = new ArrayList<>();
            int left = i+1;
            int right = arr.length-1;
            int toFind = target-arr[i];
            
            while(right>left){
                if(arr[right]+arr[left]==toFind){
                    triplets.add(new Integer[]{arr[i],arr[left],arr[right]});
                    left++;
                    right--;
                }
                else if(arr[right]+arr[left]>toFind){
                    right--;
                }
                else{
                    left++;
                }
            }
            if(triplets.size()>0){
                indexes.add(triplets);
            }
        }


        for(List<Integer[]> x:indexes){
            x.forEach(e->{System.out.println(Arrays.asList(e));});
            System.out.println();
        }


    }

}