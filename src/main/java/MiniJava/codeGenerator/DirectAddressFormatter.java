package MiniJava.codeGenerator;

public class DirectAddressFormatter implements TypeAddressFormatter {
    @Override
    public String format(int num) {
        return String.valueOf(num);
    }
}