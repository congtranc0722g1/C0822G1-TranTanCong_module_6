public class OCA2 {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("A");
        System.out.println(sb);
        String s = "A";
        StringBuilder sb2 = new StringBuilder(s);
        System.out.println(sb2);
        if(sb.equals(sb2)){
            System.out.println("Match 1");
        }else  if (sb.toString().equals(s.toString())){
            System.out.println("Match 2");
        } else {
            System.out.println("No Match");
        }
    }
}
