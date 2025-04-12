public class AFloat{
    private String float_string;


    // improve usr_string handling, pad 0's at start if ".1234" or "-.1234", add decimal point and 0 if "123" -> "123.0"
    AFloat(){
        this.float_string = "0.0";
    }
    AFloat(String s){
        this.float_string = new String(s);
    }
    AFloat(AFloat other_int){
        this.float_string = new String(other_int.float_string);
    }

    public AFloat parse(String string_String){
        AFloat new_int = new AFloat(string_String);
        return new_int;
    }


    private int decimal_point_pos(String float_){
        int pos = -1;

        for(int i=0;i<float_.length();i++){
            if(float_.charAt(i)=='.'){
                pos = i;
                break;
            }
        }

        return pos;
    }

    //mod compare, -1 -> float_1 is greater, 0 -> equal, 1 -> float_2 is greater.
    private int compare_floats(String float_1, String float_2){

        String float1_ = float_1.substring(0, decimal_point_pos(float_1));
        String float2_ = float_2.substring(0, decimal_point_pos(float_2));
        String _float1 = float_1.substring(decimal_point_pos(float_1+1));
        String _float2 = float_2.substring(decimal_point_pos(float_2)+1);

        if(float1_.length()>float2_.length()) return -1;
        else if(float1_.length()<float2_.length()) return 1;
        else{
            if(float1_.compareTo(float2_)!=0){
                if(float1_.compareTo(float2_)>0) return -1;
                else return 1;
            }
            else{
                if(_float1.compareTo(_float2)>0) return -1;
                else if(_float1.compareTo(_float2)<0) return 1;
                else return 0;
            }
        }
    }


    // both s_1 and s_2 are of same sign
    private String sub_string_to_string(String s_1, String s_2){
        String s_1_new = new String(s_1);
        String s_2_new = new String(s_2);

        String s1;
        String s2;
        boolean fnl_ans_neg = false;
        if(s_1_new.charAt(0)=='-'){
            if(compare_floats(s_1_new, s_2_new)==-1){
                fnl_ans_neg = true;
                s1 = s_1_new.substring(1);
                s2 = s_2_new.substring(1);
            }
            else{
                s1 = s_2_new.substring(1);
                s2 = s_1_new.substring(1);
            }
        }
        else{
            if(compare_floats(s_1_new, s_2_new)==-1){
                s1 = s_1_new;
                s2 = s_2_new;
            }
            else{
                fnl_ans_neg = true;
                s1 = s_2_new;
                s2 = s_1_new;
            }
        }

        int dot_pos_1 = decimal_point_pos(s1);
        int dot_pos_2 = decimal_point_pos(s2);

        int num_after_dot_1 = s1.length() - dot_pos_1;
        int num_after_dot_2 = s2.length() - dot_pos_2;

        for(int i=0;i<Math.abs(num_after_dot_2-num_after_dot_1);i++){
            if(num_after_dot_1>num_after_dot_2) s2+='0';
            else s1+='0';
        }

        boolean take = false;
        String fnl_string = "";
        int max = Math.max(s1.length(),s2.length());

        for(int i=0;i<max;i++){

            if(s1.charAt(s1.length()-i-1)=='.'){ 
                fnl_string = '.' + fnl_string;
                continue;
            }

            int dig1 = s1.charAt(s1.length() - i - 1) - '0';
            int dig2 = (i>=s2.length()) ? 0 : s2.charAt(s2.length() - i -1)-'0';
            
            int reslt = dig1 - dig2;

            if(take){
                reslt -= 1;
            }

            if(reslt<0){
                take = true;
                reslt += 10;
            }
            else{
                take = false;
            }

            fnl_string = reslt + fnl_string;
        }
        
        if(fnl_ans_neg) return '-' + fnl_string;
        return fnl_string;
    }

    private String add_string_to_string(String s_1, String s_2){
        String s1 = new String(s_1);
        String s2 = new String(s_2);

        int dot_pos_1 = decimal_point_pos(s1);
        int dot_pos_2 = decimal_point_pos(s2);
        // dot_pos ranges from 0 to s.length()-2;

        int num_after_dot_1 = s1.length() - dot_pos_1;
        int num_after_dot_2 = s2.length() - dot_pos_2;

        for(int i=0;i<Math.abs(num_after_dot_2-num_after_dot_1);i++){
            if(num_after_dot_1>num_after_dot_2) s2+='0';
            else s1+='0';
        }

        int carry=0;
        int max = Math.max(s1.length(), s2.length());
        String res_String = "";

        for(int i=0;i<max;i++){

            if(i<s1.length()){
                if(s1.charAt(s1.length() - i -1)=='.'){
                    res_String = '.' + res_String;
                    continue;
                }
            }

            int dig1 = (i>=s1.length()) ? 0 : s1.charAt(s1.length()-i-1)-'0';
            int dig2 = (i>=s2.length()) ? 0 : s2.charAt(s2.length()-i-1)-'0';

            int reslt = dig1 + dig2 + carry;
            carry = reslt / 10;
            res_String = (reslt % 10) + res_String;
        }
        if(carry>0){
            res_String = carry + res_String;
        }

        return res_String;
    }


    
    private AFloat sub_string_to_afloat(String s1, String s2){

        if((s1.charAt(0)=='-' && s2.charAt(0)!='-') || (s1.charAt(0)!='-' && s2.charAt(0)=='-')){
            if(s1.charAt(0)=='-') return add_string_to_afloat(s1,'-'+s2);
            else return add_string_to_afloat(s1, s2.substring(1));
        }

        String result = sub_string_to_string(s1,s2);
        AFloat result_ = new AFloat(result);
        return result_;
    }


    private AFloat add_string_to_afloat(String s1, String s2){

        if((s1.charAt(0)=='-' && s2.charAt(0)!='-') || (s1.charAt(0)!='-' && s2.charAt(0)=='-')){
            if(s1.charAt(0)=='-') return sub_string_to_afloat(s2,s1.substring(1));
            else return sub_string_to_afloat(s1,s2.substring(1));
        }

        String result;
        if(s1.charAt(0)=='-') result = '-' + add_string_to_string(s1.substring(1),s2.substring(1));
        else result = add_string_to_string(s1,s2);

        AFloat result_ = new AFloat(result);
        return result_;
    }


    public AFloat add(AFloat other_float){
        return add_string_to_afloat(this.float_string, other_float.float_string);
    }

    public AFloat subtract(AFloat other_float){
        return sub_string_to_afloat(this.float_string, other_float.float_string);
    }
}