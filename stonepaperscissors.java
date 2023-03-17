import java.util.Scanner;

class SPS {
    public static void main(String[] args) {
        while (true) {
            System.out.println("Welcome to the Game:");
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter 1 for stone, 2 for paper or 3 for scissors ");
            int x = sc.nextInt();
            while(x<0||x>3){
                System.out.println("Wrong Number. Enter Again: ");
                x=sc.nextInt();
            }
            int y = (int) (Math.random() * 10);
            while (y > 3 || y < 1) {
                y = (int) (Math.random() * 10);
            }

            if(x==y){
                System.out.println("It's A Draw");
            }
            else if(x==1 && y==2){
                System.out.println("You chose Stone. CPU chose Paper. ");
                System.out.println("You Lost");
            }
            else if(x==2&&y==1){
                System.out.println("You chose Paper. CPU chose Stone");
                System.out.println("You Won");
            }
            else if(x==1&&y==3){
                System.out.println("You chose Stone. CPU chose Scissors");
                System.out.println("You Won");
            }
            else if(x==2&&y==3){
                System.out.println("You chose Paper. CPU chose scissors");
                System.out.println("CPU won");
            }
            else if(x==)

            }
        }
    }
}