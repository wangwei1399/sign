import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Created by Rui on 12-04-0004.
 */
public class SignTest {

    public String getToken(){

        String mytoken="";



        return mytoken;

    }

    public static void main(String[] args) throws NoSuchAlgorithmException {

        HashMap<String, String> params=new HashMap<String, String>();
        String sign="";
       // Map newparams=new HashMap();
        params.put("app_key","http_client");
        params.put("fan_id","633123");
        params.put("no","12321");
        params.put("cid","98765");

        System.out.println(params);

        SignTest sortparams=new SignTest();

        List newparams=sortparams.HashSort(params);

        System.out.println(newparams);

//      Iterator iterator=newparams.iterator();
//
//        while (iterator.hasNext())
//        {
//
//        System.out.println();
//        }

        for(int i=0;i<newparams.size();i++){
            System.out.println(newparams.get(i));
            sign=sign+(newparams.get(i).toString())+"&";}

        sign = sign.substring(0,sign.length()-1);
        if (params.containsKey("user_id"))
            sign=sign+sortparams.getToken();
        System.out.println(sign);
        System.out.println(sortparams.MD5(sign));
    }

    public List HashSort(HashMap<String, String> params){

        List<Map.Entry<String,String>> params1=new ArrayList<Map.Entry<String,String>>(params.entrySet());


        //System.out.println(params1);

        Collections.sort(params1, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return o1.getKey().toString().compareTo(o2.getKey().toString());
            }
        });

        return params1;
    }

    public String MD5(String plainText) {

        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("md5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] cipherData = md5.digest(plainText.getBytes());
        StringBuilder builder = new StringBuilder();
        for(byte cipher : cipherData) {
            String toHexStr = Integer.toHexString(cipher & 0xff);
            builder.append(toHexStr.length() == 1 ? "0" + toHexStr : toHexStr);
        }
        return builder.toString();
    }

}
