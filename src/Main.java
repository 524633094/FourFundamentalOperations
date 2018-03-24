import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;

public class Main {

    static int N =20;
    static element ele = new element();

    //�����沨��ʽ�Ľ��
    private static boolean evoe(ArrayList<String> strArr){
        String str = ele.getOperations();
        boolean flag = true;//�жϹ������Ƿ��и�����С����
        int temp = 0;//�����ʱ������
        Stack<String> stack = new Stack<String>();
        for(String s : strArr){
            if(!str.contains(s)){//���������,����ջ��
                stack.push(s);
            }else{
                int a = Integer.valueOf(stack.pop());
                int b = Integer.valueOf(stack.pop());
                try {
                    switch(s){
                        case "+" :
                            stack.push(String.valueOf(a+b));
                            break;
                        case "-" :
                            temp = b-a;
                            if(temp < 0) flag=false;
                            stack.push(String.valueOf(temp));
                            break ;
                        case "*" :
                            stack.push(String.valueOf(a*b));
                            break;
                        case "/" :
                            if(a == 0) {a = 1;flag = false;}
                            temp = b/a;
                            if(a*temp != b) flag = false;
                            stack.push(String.valueOf(temp));
                            break ;
                    }
                } catch (Exception e) { }
            }
        }
        ele.setResult(Integer.parseInt(stack.pop()));
        return flag;
    }

	//���沨��ʽת��Ϊ����ʽ
    private static void pro_exp(ArrayList<String> strArr){
        String str = ele.getOperations();
        String ea,eb;
        String fh = " ";//��ʱ�ķ���
        boolean lastisnum = false;//��¼��ʱ���ź��һλ�Ƿ�Ϊ����
        Stack<String> expstack = new Stack<String>();
        for(String s : strArr){
            if(!str.contains(s)){//���������,����ջ��
                expstack.push(s);
                lastisnum = true;
            }else{
                ea = expstack.pop();
                eb = expstack.pop();
                switch(s){
                    case "+" :
                    	expstack.push(eb+"+"+ea);
                        break;
                    case "-" :
                    	expstack.push(eb+"-"+ea);
                        break ;
                    case "*" :
                    	if("+-".contains(fh)){
                    		if(!lastisnum) ea = "("+ea+")";
                    		else eb = "("+eb+")";
                    	}
                    	expstack.push( eb+"*"+ea);
                        break;
                    case "/" :
                    	if("+-".contains(fh)){
                    		if(!lastisnum) ea = "("+ea+")";
                    		else eb = "("+eb+")";
                    	}
                    	expstack.push( eb+"/"+ea);
                        break ;
                }
                fh = s;
                lastisnum = false;
            }
        }
        ele.setExp(expstack.pop());
    }
	//��������沨��ʽ
    public static void test(){
        ArrayList<String> strArr=new ArrayList<String>();
        List list = Collections.synchronizedList(strArr);
        N = new Random().nextInt(4)+2;
        synchronized(list) {
            strArr.clear();
            for (int i = 0; i < N; i++) {
                strArr.add(ele.random_N());
            }
            for (int i = 0; i < N - 2; i++) {
                strArr.add(new Random().nextInt(strArr.size() - N + 1) + N - 1, ele.random_E());
            }
            strArr.add(strArr.size(), ele.random_E());
        }
        ele.setStrArr(strArr);
        //System.out.println(strArr.toString());

    }


    public static void main(String[] args) {

        //��������Ľ��
        String result = "201571030141\r\n";
        //����ʱ��������Ŀ������Ĭ��Ϊ5
        String x = "";
        if(args.length == 0) x = "5";
        else x = args[0];

        Scanner in = new Scanner(System.in);

        boolean b = true;
        while(b){
            int flage = 1;
            for (int i = x.length();--i >= 0;){
                if (!Character.isDigit(x.trim().charAt(i))){
                    flage = 0;
                }
            }
            if(flage == 1) b = false;
            else {
                System.out.println("����ȷ������Ŀ��������");
                x = in.nextLine();
            }
        }
        int k = 0;
        while (k < Integer.parseInt(x)) {
            test();
            pro_exp(ele.getStrArr());

            b = evoe(ele.getStrArr());

            if (!b) {
                continue;
            }
            k++;
            System.out.println(ele.getExp()+"="+ele.getResult());
            //System.out.println(ele.getResult());
            //if(ele.getExp().contains("(")||ele.getExp().contains(")"))
            //	result += ele.getExp()+"= "+ele.getResult()+" 2"+"\r\n";
            //else result += ele.getExp()+"= "+ele.getResult()+" 1"+"\r\n";
            result += ele.getExp()+"= "+ele.getResult()+"\r\n";
        }

        File file = new File("./result.txt");
        try {
            PrintStream ps = new PrintStream(new FileOutputStream(file));
            ps.println(result);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}