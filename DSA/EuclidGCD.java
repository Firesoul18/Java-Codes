public class EuclidGCD {

    public static int findGCD(int x, int y){
        if(x%y==0){
            return y;
        }

        return findGCD(y,x%y);

    }
    public static void main(String[] args) {
        System.out.print(findGCD(1680,640));
    }
}
