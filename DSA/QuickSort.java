public class QuickSort {

    static void printArray(int p[]) {
        for (int s : p)
            System.out.print(s + " ");
    }

    static int[] sort(int arr[]) {

        if (arr.length < 2) {
            return arr;
        }

        int pLoc = 0;
        int pivot = arr[pLoc];

        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= pivot && i != pLoc)
                count++;
        }

        int x[] = new int[count];
        int y[] = new int[arr.length - count - 1];

        for (int i = 0, j = 0, k = 0; i < arr.length; i++) {
            if (i == pLoc) {
                continue;
            }

            if (pivot >= arr[i]) {
                x[j++] = arr[i];
            } else {
                y[k++] = arr[i];
            }
        }

        x = sort(x);
        y = sort(y);

        arr = merge(x, y, pivot);
        return arr;
    }

    static int[] merge(int x[], int y[], int pivot) {
        int temp[] = new int[x.length + y.length + 1];

        for (int i = 0, j = 0, k = 0; i <= x.length || j < y.length;) {
            if (i <= x.length) {
                if (i == x.length) {
                    temp[k++] = pivot;
                    i++;
                } else {
                    temp[k++] = x[i++];
                }
                continue;
            } else if (j < y.length) {
                temp[k++] = y[j++];
            }
        }
        return temp;
    }

    public static void main(String[] args) {
        int x[] = { 12, 23, 17, 2, 12, 0 };
        x = sort(x);
        printArray(x);
    }

}
