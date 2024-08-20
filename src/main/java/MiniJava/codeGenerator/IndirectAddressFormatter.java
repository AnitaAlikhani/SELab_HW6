package MiniJava.codeGenerator;

public class IndirectAddressFormatter implements TypeAddressFormatter {
    @Override
    public String format(int num) {
        return "@" + num;
    }
}