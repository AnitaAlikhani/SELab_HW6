package MiniJava.codeGenerator;

public class ImmediateAddressFormatter implements TypeAddressFormatter {
    @Override
    public String format(int num) {
        return "#" + num;
    }
}