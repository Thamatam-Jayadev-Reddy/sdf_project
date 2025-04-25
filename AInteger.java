public class AInteger{
    private String int_string;

    private String remove_zeros(String s){
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)!='0'){
                return s.substring(i);
            }
        }
        return "0";
    }

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

    private String mul(String s1, String s2){
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
            result_string = add_string_to_string(result_string, car_string);
        }

        return result_string;
    }

    public AInteger multiply(AInteger other_int){
        String s1 = this.int_string;
        String s2 = other_int.int_string;
        boolean neg = false;
        String result_string;

        if((s1.charAt(0)=='-' && s2.charAt(0)!='-') || (s1.charAt(0)!='-' && s2.charAt(0)=='-')){
            neg = true;
            if(s1.charAt(0)=='-') result_string = mul(s1.substring(1), s2);
            else result_string = mul(s1, s2.substring(1));
        }
        else{
            if(s1.charAt(0)=='-') result_string = mul(s1.substring(1), s2.substring(1));
            else result_string = mul(s1,s2);
        }
        AInteger result_;
        if(neg) result_ = new AInteger('-' + result_string);
        else result_ = new AInteger(result_string);
        return result_;
    }

    private String div(String s1, String s2){
        if(compare(s1, s2)==1){
            return "0";
        }
        else if(compare(s1, s2)==0){
            return "1";
        }
        else{
            String quotient = "";
            int take_ptr;
            String vid = s1.substring(0,s2.length());
            take_ptr = s2.length();
            if(compare(vid,s2)==1){
                vid = s1.substring(0, s2.length()+1);
                take_ptr = s2.length()+1;
            }

            while(true){

                String fake_qoutient = "1";
                while(compare(vid,mul(fake_qoutient,s2))<=0){
                    fake_qoutient = add_string_to_string("1", fake_qoutient);
                }
                String to_add_qoutient = subtract_string_to_string(fake_qoutient, "1");
                to_add_qoutient = remove_zeros(to_add_qoutient);
                
                quotient+=to_add_qoutient;
                if(take_ptr==s1.length()) return quotient;

                if(to_add_qoutient.equals("0")){
                    vid = vid + s1.charAt(take_ptr);
                    take_ptr++;
                    continue;
                }
                
                String remainder = subtract_string_to_string(vid, mul(to_add_qoutient, s2));
                remainder = remove_zeros(remainder);

                vid = remainder + s1.charAt(take_ptr);
                vid = remove_zeros(vid);
                take_ptr++;
            }
        }
    }

    public AInteger divide(AInteger other_int){
        String s1 = this.int_string;
        String s2 = other_int.int_string;
        boolean neg = false;
        String result_string;

        if((s1.charAt(0)=='-' && s2.charAt(0)!='-') || (s1.charAt(0)!='-' && s2.charAt(0)=='-')){
            neg = true;
            if(s1.charAt(0)=='-') result_string = div(s1.substring(1), s2);
            else result_string = div(s1, s2.substring(1));
        }
        else{
            if(s1.charAt(0)=='-') result_string = div(s1.substring(1), s2.substring(1));
            else result_string = div(s1,s2);
        }
        AInteger result_;
        if(neg) result_ = new AInteger('-' + result_string);
        else result_ = new AInteger(result_string);
        return result_;
    }


    public static void main(String[] args){
        AInteger num1 = new AInteger("3930400000000000000000");
        AInteger num2 = new AInteger("1502934");

        AInteger result1 = num1.divide(num2);
        System.out.println(result1.int_string);
    }
}

