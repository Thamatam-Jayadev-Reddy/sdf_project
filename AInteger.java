public class AInteger{
    private String int_string;
    AInteger(){
        this.int_string = "0";
    }
    AInteger(String s){
        this.int_string = new String(s);
    }
    AInteger(AInteger other_int){
        this.int_string = new String(other_int.int_string);
    }

    public AInteger parse(String string_String){
        AInteger new_int = new AInteger(string_String);
        return new_int;
    }

    // both s1 and s2 should be positive
    // -1 for s1, 0 equal, 1 for s2 (greater)
    private int compare(String s1, String s2){
        if(s1.length()>s2.length()){
            return -1;
        }
        else if(s2.length() > s1.length()){
            return 1;
        }
        else{
            if(s1.compareTo(s2)>0) return -1;
            else if(s1.compareTo(s2)<0) return 1;
            else return 0;
        }
    }
    
    
    private String add_string_to_string(String num1, String num2){
        int max = Math.max(num1.length(),num2.length());
        int carry = 0;
        
        String res_String = "";

        for(int i=0;i<max;i++){
            int dig1 = (i>num1.length()) ? 0 : num1.charAt(num1.length()-i-1);
            int dig2 = (i>num2.length()) ? 0 : num2.charAt(num2.length()-i-1);
            dig1 -= '0'; 
            dig2 -= '0';

            int res_dig = dig1 + dig2 + carry;

            res_String = (res_dig%10) + res_String;
            carry = res_dig / 10;
        }
        if(carry>0){
            res_String = carry + res_String;
        }
        return res_String;
    }

    private AInteger add_string_to_aint(String s1, String s2){
        
        // if((s1.charAt(0)=='-' || s2.charAt(0)=='-') && (s1.charAt(0)!='-' && s2.charAt(0)!='-')){
        if((s1.charAt(0)=='-' && s2.charAt(0)!='-') || (s1.charAt(0)!='-' && s2.charAt(0)=='-')){
            if(s1.charAt(0)=='-') return subtract_string_to_aint(s2, s1.substring(1));
            else return subtract_string_to_aint(s1, s2.substring(1));
        }

        String result;

        if(s1.charAt(0)!='-') result = add_string_to_string(s1, s2);
        else result = '-' + add_string_to_string(s1.substring(1),s2.substring(1));

        AInteger result_ = new AInteger(result);
        return result_;
    }

    public AInteger add(AInteger other_int){
        return add_string_to_aint(other_int.int_string, this.int_string);
    }

    // both s1 and s2 have same sign
    private String subtract_string_to_string(String s1, String s2){
        boolean final_ans_is_neg = false;
        String max_string;
        String min_string;
        if(s1.charAt(0)!='-'){  //both s1 and s2 are positive
            if(compare(s1, s2)==1){
                final_ans_is_neg = true;
                max_string = s2;
                min_string = s1;
            }
            else{
                max_string = s1;
                min_string = s2;
            }
        }
        else{   //both s1 and s2 are negative
            if(compare(s1, s2)==1){
                max_string = s2.substring(1);
                min_string = s1.substring(1);
            }
            else{
                final_ans_is_neg = true;
                max_string = s1.substring(1);
                min_string = s2.substring(1);
            }
        }

        // we need to do max_string - min_string
        boolean take = false;
        String fnl_string = "";

        for(int i=0;i<max_string.length();i++){
            int dig1 = max_string.charAt(max_string.length() - i - 1);
            int dig2 = (i>min_string.length())? 0 : min_string.charAt(min_string.length() - i -1);

            int res_dig = dig1 - dig2;

            if(take){
                res_dig-=1;
            }

            if(res_dig<0){
                take = true;
                res_dig += 10;
            }
            else{
                take = false;
            }

            fnl_string = res_dig + fnl_string;
        }

        if(final_ans_is_neg) return '-'+fnl_string;
        return fnl_string;
    }

    private AInteger subtract_string_to_aint(String s1, String s2){

        // if((s1.charAt(0)=='-'||s2.charAt(0)=='-') && (s1.charAt(0)!='-' && s2.charAt(0)!='-')){
        if((s1.charAt(0)=='-' && s2.charAt(0)!='-') || (s1.charAt(0)!='-' && s2.charAt(0)=='-')){
            if(s2.charAt(0)=='-') return add_string_to_aint(s1,s2.substring(1));
            else return add_string_to_aint(s1, '-' + s2);
        }

        String result = subtract_string_to_string(s1, s2);

        AInteger result_ = new AInteger(result);
        return result_;
    }

    public AInteger subtract(AInteger other_int){
        return subtract_string_to_aint(this.int_string, other_int.int_string);
    }
}

