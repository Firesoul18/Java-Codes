class BinarySearch{
    public static void findCeil(int[] array, int num){
        boolean isAscending = false;
        if(array[0]<array[array.length-1]){
            isAscending=true;
        }
        

        if(isAscending){
          int first = array[0];
          int last = array[array.length-1];
          int mid = array.length/2;
          while(true){
            if(first>=last){
                System.out.println(-1);
                break;
            }
            if(mid>=array.length){
                if(first==num){
                    System.out.println("Found item on index " + (mid-2));
                    break;
                }
                else if(last==num){
                    System.out.println("Found item on index " +(mid-1));
                    break;
                }
                else{
                    System.out.println(-1);
                    break;
                }
            }
            System.out.println("first= "+first+" mid= "+array[mid]+" last= "+last);
            if(array[mid]==num){
                System.out.println("Found item on index " + mid);
                break;
            }
            else if(array[mid]>num){
                last=array[mid];
                mid=mid/2;
                System.out.println("Ending in elif");
                // break;
            }
            else{
                first=array[mid+1];
                mid+=mid/2;
                System.out.println("Ending in else");
                // break;
            }
            // System.out.println("first= "+first+" mid= "+mid+" last= "+last);
          }
        }
    }

    public static void main(String[] args) {
        int[] array ={7,8,9,9,10,11,12,13,17};
        findCeil(array, 10);
    }
}