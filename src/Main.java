import  java.util.Scanner;
class calc{
    public static void main(String[] args) throws Exception {
        Scanner scanner= new Scanner(System.in);
        System.out.println("Введите 2 числа (римских или арабских.):");
        String exception = scanner.nextLine();
        System.out.println(parse (exception));
    }
    public static String parse(String exception) throws Exception {
        int num1;
        int num2;
        String open;
        String result;
        boolean isRoman;
        String[] operands = exception.split("[+\\-*/]");
        if (operands.length !=2) throw new Exception("Должно быть 2 числа.");
        open =detectOperation(exception);
        if (open == null)throw new Exception("Не поддерживается.");
        if (Roman.isRoman(operands[0]) && Roman.isRoman(operands[1])){
            num1 =Roman.convertToArabian(operands[0]);
            num2 =Roman.convertToArabian(operands[1]);
            isRoman= true;
        } else if (!Roman.isRoman(operands[0]) && !Roman.isRoman(operands[1])) {
            num1= Integer.parseInt(operands[0]);
            num2= Integer.parseInt(operands[1]);
            isRoman= false;
        }
        else {
            throw new Exception("Числа должны быть в 1 формате.");
        }
        if (num1>10||num2>10){
            throw new Exception("числа должны быть от 1 до 10.");
        }
        int arabian= cal(num1, num2, open);
        if (isRoman){
            if (arabian<=0) {
                throw new Exception("число должно быть больше 0.");
            }
            result= Roman.convertToRoman(arabian);
        }else {
            result= String.valueOf(arabian);
        }
        return result;
    }
    static String detectOperation(String expression){
        if (expression.contains("+"))return "+";
        else if (expression.contains("-")) return "-";
        else if (expression.contains("*")) return "*";
        else if (expression.contains("/")) return "/";
        else return null;
        }
        static int cal(int a, int b, String oper) {
            return switch (oper) {
                case "+" -> a + b;
                case "-" -> a - b;
                case "*" -> a * b;
                default -> a / b;
            };
        }
}
class Roman{
    static String[] romanArray = new String[]{"0","I","II","III","IV","V","VI","VII","VIII","IX","X","XI","XII","XIII",
            "XIV","XV","XVI","XVII","XVIII","XIX","XX","XXI","XXII","XXIII","XXIV","XXV","XXVI","XXVII","XXVIII","XXIX",
            "XXX","XXXII","XXXV","XXXVI","XL","XLV","LIV","LVI","LX","LXIV","LXX","XC","C"};
    public static boolean isRoman(String val){
        for (String s : romanArray) {
            if (val.equals(s)) {
                return true;
            }
        }
        return false;
    }
    public static int convertToArabian(String roman) {
        for (int i = 0; i < romanArray.length; i++) {
            if (roman.equals(romanArray[i])) {
                return i;
            }
        }
        return -1;
    }
    public static String convertToRoman(int arabian){return romanArray[arabian];}
}