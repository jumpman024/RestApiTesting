
public class Strings_exercise {

    public static void main(String[] args) {


        String s = "Something";
        String t = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            t = t + (s.charAt(i));
        }

        for (int i = s.length(); i > 0; i--) {
            t = t + (s.charAt(i - 1));
        }

        System.out.println(t);
        }


    }


