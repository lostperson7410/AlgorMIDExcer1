public class Davison {
    public static void main(String[] args) {
        int inpt = 5;
        String numString = "0";
        numString = Davison_Seq(numString, inpt);
        System.out.println(numString);
    }

    public static String Davison_Seq(String numString,int inpt){
        int i = 0;
        if(inpt == 0 )
            return numString;
        else{
            try{
                System.out.println(numString);
                char[] bitwise = numString.toCharArray();
                int str_length = numString.length();
                while(i < str_length){
                    if(bitwise[i] == '1')
                        bitwise[i] = '0';
                    else if (bitwise[i] == '0')
                        bitwise[i] = '1';
                    else
                        bitwise[i] = '@';
                    i++;
                }
                String newString = String.valueOf(bitwise);
                numString = numString.concat(newString);
            }
            catch (Exception e){
                System.out.println("Error in"+e);
            }
            return Davison_Seq(numString,inpt-1);
        }
    }
}