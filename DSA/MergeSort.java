class MergeSort{

    static int[] sort(int arr[]){

        /*
        System.out.print("Called: ");
        printArray(arr);
        */

        if(arr.length<2){
            return arr;
        }
        
        int mid = arr.length/2;
        int[] a = new int[mid];
        int[] b = new int[arr.length-mid];

        for(int i=0; i<mid; i++){
            a[i] = arr[i];
        }

        for(int i=mid,k=0; i<arr.length; i++,k++){
            b[k]=arr[i];
        }

        a=sort(a);
        b=sort(b);

        return merge(a,b);
    }


    static int[] merge(int a[],int[] b){

      /*
        System.out.println("\nA Came: ");
        printArray(a);

        System.out.println("B Came: ");
        printArray(b);
        System.out.println();
        */
        int temp[] = new int[a.length+b.length];

        int k =0;

        for(int i=0, j=0; i<a.length || j<b.length;){
            if(i>=a.length){
                temp[k++]=b[j++];
                continue;
            }
            else if(j>=b.length){
                temp[k++]=a[i++];
                continue;
            }

            if (a[i]<=b[j]){
                temp[k++]=a[i++];
            }
            else{
                temp[k++] = b[j++];
            }
        }

        return temp;
    }

    static void printArray(int[] x){
        for(int s: x){
            System.out.print(s+" ");
        }
        System.out.println();
    }
    public static void main(String args[]){
        int x[] = {9,44,1,23,2};
        x=sort(x);
        printArray(x);
    }
}
