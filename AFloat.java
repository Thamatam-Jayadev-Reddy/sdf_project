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

        if(pos==-1) return float_.length();
        return pos;
    }

    //mod compare, -1 -> float_1 is greater, 0 -> equal, 1 -> float_2 is greater.
    private int compare_ints(String s1, String s2){
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

    private String add_string_to_string_ints(String num1, String num2){
        int max = Math.max(num1.length(),num2.length());
        int carry = 0;
        
        String res_String = "";

        for(int i=0;i<max;i++){
            int dig1 = (i>=num1.length()) ? '0' : num1.charAt(num1.length()-i-1);
            int dig2 = (i>=num2.length()) ? '0' : num2.charAt(num2.length()-i-1);
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
    private String mul_ints(String s1, String s2){
        String result_string = "0";
        
        for(int i=0;i<s2.length();i++){
            String car_string = "";
            for(int j=0;j<i;j++){
                car_string+='0';
            }
            
            int dig_1 = s2.charAt(s2.length()-i-1) - '0';
            int carry = 0;
            for(int j=0;j<s1.length();j++){
                int dig_2 = s1.charAt(s1.length()-j-1) - '0';
                int mul_ans = dig_2 * dig_1 + carry;

                car_string = (mul_ans % 10) + car_string;
                carry = mul_ans / 10;
            }
            if(carry>0){
                car_string = carry + car_string;
            }
            result_string = add_string_to_string_ints(result_string, car_string);
        }

        return result_string;
    }
    private String mul(String s1, String s2){
        int dec_pos_1 = decimal_point_pos(s1);
        int dec_pos_2 = decimal_point_pos(s2);

        int num_digs_dec_pos_1 = s1.length() - dec_pos_1 - 1;
        int num_digs_dec_pos_2 = s2.length() - dec_pos_2 - 1;

        s1 = s1.substring(0, dec_pos_1) + s1.substring(dec_pos_1+1);
        s2 = s2.substring(0, dec_pos_2) + s2.substring(dec_pos_2+1);

        String without_dec = mul_ints(s1,s2);
        int dec_pos_fnl = without_dec.length() - num_digs_dec_pos_1 - num_digs_dec_pos_2; // 1234125 -> 7-2-1 = 4
        return without_dec.substring(0,dec_pos_fnl) + '.' + without_dec.substring(dec_pos_fnl);
    }


    public AFloat multiply(AFloat other_float){
        String s1 = new String(this.float_string);
        String s2 = new String(other_float.float_string);

        boolean fnl_ans_neg = false;
        if((s1.charAt(0)=='-'&&s2.charAt(0)!='-') || (s1.charAt(0)!='-' && s2.charAt(0)=='-')){
            fnl_ans_neg = true;
            if(s1.charAt(0)=='-') s1 = s1.substring(1);
            else s2 = s2.substring(1);
        }

        if(s1.charAt(0)=='-'){
            s1 = s1.substring(1);
            s2 = s2.substring(1);
        }

        String result = mul(s1,s2);
        if(fnl_ans_neg) result = '-' + result;

        AFloat result_ = new AFloat(result);
        return result_;
    }

    private String sub_string_to_string_ints(String s1, String s2){
        boolean final_ans_is_neg = false;
        String max_string;
        String min_string;
        if(s1.charAt(0)!='-'){  //both s1 and s2 are positive
            if(compare_ints(s1, s2)==1){
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
            if(compare_ints(s1, s2)==1){
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
            int dig1 = max_string.charAt(max_string.length() - i - 1)-'0';
            int dig2 = (i>=min_string.length()) ? 0 : min_string.charAt(min_string.length() - i -1)-'0';

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
    private String remove_zeros_int(String s){
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)!='0'){
                return s.substring(i);
            }
        }
        return "0";
    }
    private String div_ints(String s1, String s2){
        s1 = remove_zeros_int(s1);
        s2 = remove_zeros_int(s2);
        if(compare_ints(s1, s2)==0){
            return "1.0";
        }
        else{
            int precision = 10;

            String quotient = "";
            int take_ptr;

            take_ptr = Math.min(s2.length(),s1.length());
            boolean dec_point_not_placed = true;
            
            String vid;
            if(s2.length()<s1.length()) vid = s1.substring(0,s2.length());
            else vid = s1;

            while(compare_ints(vid,s2)==1){
                if(take_ptr<s1.length()){
                    vid += s1.charAt(take_ptr);
                    take_ptr++;
                }
                else{
                    vid += '0';
                    take_ptr++;
                    if(dec_point_not_placed){
                        quotient = ".";
                        dec_point_not_placed = false;
                    }
                    else{
                        quotient += '0';
                    }
                }
            }

            while(true){
                String fake_qoutient = "1";
                while(compare_ints(vid,mul_ints(fake_qoutient,s2))<=0){
                    fake_qoutient = add_string_to_string_ints("1", fake_qoutient);
                }
                String to_add_qoutient = sub_string_to_string_ints(fake_qoutient, "1");
                to_add_qoutient = remove_zeros_int(to_add_qoutient);
                
                quotient+=to_add_qoutient;
                if(quotient.length()>precision) return quotient;
                
                String floor_qoutient = mul_ints(to_add_qoutient, s2);
                floor_qoutient = remove_zeros_int(floor_qoutient);
                String remainder = sub_string_to_string_ints(vid, floor_qoutient);
                remainder = remove_zeros_int(remainder);
                
                if(take_ptr<s1.length()){
                    vid = remainder + s1.charAt(take_ptr);
                    vid = remove_zeros_int(vid);
                }
                else{
                    vid = remainder + '0';
                    vid = remove_zeros_int(vid);
                    if(dec_point_not_placed){
                        quotient += '.';
                        dec_point_not_placed = false;
                    }
                }
                take_ptr++;
            }
        }
    }

    private String div(String s1, String s2){        
        int dec_pos_1 = decimal_point_pos(s1);
        int dec_pos_2 = decimal_point_pos(s2);

        int num_digs_dec_pos_1 = s1.length() - dec_pos_1 - 1;
        int num_digs_dec_pos_2 = s2.length() - dec_pos_2 - 1;

        s1 = s1.substring(0, dec_pos_1) + s1.substring(dec_pos_1+1);
        s2 = s2.substring(0, dec_pos_2) + s2.substring(dec_pos_2+1);

        String improper_dec = div_ints(s1,s2);
        int dec_point_improper = decimal_point_pos(improper_dec);
        improper_dec = improper_dec.substring(0,dec_point_improper) + improper_dec.substring(dec_point_improper+1);

        int dec_shift = num_digs_dec_pos_2 - num_digs_dec_pos_1;
        // return improper_dec;
        if(dec_shift + dec_point_improper > 0){
            int shift_temp = dec_shift + dec_point_improper;
            return improper_dec.substring(0, shift_temp) + '.' + improper_dec.substring(shift_temp);
        }
        else{
            int shift_temp = -1 * (dec_shift + dec_point_improper);
            return "0." + "0".repeat(shift_temp) + improper_dec;
        }
    }
    // 1234.1234 -> 12341234

    public AFloat divide(AFloat other_float){
        String s1 = new String(this.float_string);
        String s2 = new String(other_float.float_string);

        boolean fnl_ans_neg = false;
        if((s1.charAt(0)=='-'&&s2.charAt(0)!='-') || (s1.charAt(0)!='-' && s2.charAt(0)=='-')){
            fnl_ans_neg = true;
            if(s1.charAt(0)=='-') s1 = s1.substring(1);
            else s2 = s2.substring(1);
        }

        if(s1.charAt(0)=='-'){
            s1 = s1.substring(1);
            s2 = s2.substring(1);
        }

        String result = div(s1,s2);
        if(fnl_ans_neg) result = '-' + result;

        AFloat result_ = new AFloat(result);
        return result_;
    }
}