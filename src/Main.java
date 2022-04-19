import java.io.IOException;
import java.util.Scanner;
public class Main
{

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        boolean key = false;
        boolean err;
        boolean rims;
        int a;
        int b;
        int answ = 0;
        while (!key){
            System.out.println("Введите выражение:");
            String txt = in.nextLine();
            String[] nums = txt.split(" ");
            a = 100;
            b = 100;
            err = false;
            rims = false;

            try {
                a = Integer.parseInt(nums[0]);
                b = Integer.parseInt(nums[2]);
            } catch (NumberFormatException e) {
                if(nums.length != 3){
                    if (nums[0] == "X" || nums[0] == "x"){ key = true; }
                    else {System.out.println("Выражение должно состоять из двух чисел и арифмитического символа, введенных через пробел!");}
                    err = true;
                }
                else{
                    try {
                        a = rim(nums[0]);
                        b = rim(nums[2]);
                        rims = true;

                    } catch (IOException ex) {
                        System.out.println("Введено неверное значение!");
                        err = true;
                    }
                }
            }
            if ((a>10 || b>10 || a<1 || b<1) && !err){
                try {throw new IOException();}
                catch (IOException e) {System.out.println("Введеннык цифры не должны быть больше 10");}
            }
            else if (!err){
                System.out.print("Ответ:");
                switch (nums[1]) {
                    case "+":
                        answ = a + b;
                        break;
                    case "-":
                        answ = a - b;
                        break;
                    case "*":
                        answ = a * b;
                        break;
                    case "/":
                        answ = a / b;
                        break;
                }
                if (rims){System.out.println(toRim(answ));}
                else {System.out.println(answ);
                }
            }
        }
    }

    public static int rim(String a) throws IOException {
        int res = 0;
        for (int i = 0; i < a.length(); i++){
            if (i < a.length() - 1){
                if(a.charAt(i) == 'I' && a.charAt(i+1) != 'I'){ res -= 1; }
                else if(a.charAt(i) == 'I'){ res += 1; }
                else if(a.charAt(i) == 'V'){ res += 5; }
                else if(a.charAt(i) == 'X'){ res += 10; }
                else{throw new IOException();}
            }
            else{
                if(a.charAt(i) == 'I'){ res += 1; }
                else if(a.charAt(i) == 'V'){ res += 5; }
                else if(a.charAt(i) == 'X'){ res += 10; }
                else{throw new IOException();}
            }
        }
        return res;
    }

    public static String toRim(int n){
        if (n > 0){
            String out = "";
            while (n>0){
                if(n>=100){out += "C"; n -= 100;}
                else if(n>=90){out += "XC"; n -= 90;}
                else if(n>=50){out += "L"; n -= 50;}
                else if(n>=40){out += "XL"; n -= 40;}
                else if(n>=10){out += "X"; n -= 10;}
                else if(n>=9){out += "IX"; n -= 9;}
                else if(n>=5){out += "V"; n -= 5;}
                else if(n>=4){out += "IV"; n -= 4;}
                else if(n>=1){out += "I"; n -= 1;}
            }
            return out;
        }
        else{
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("Результатом выражения, составленного римскими числами, может быть только число больше нуля!");
            }
            return "I";
        }

    }
}