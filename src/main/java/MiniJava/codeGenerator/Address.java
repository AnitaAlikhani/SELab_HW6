package MiniJava.codeGenerator;

/**
 * Created by mohammad hosein on 6/28/2015.
 */

public class Address {
    public int num;
    public TypeAddress Type;
    public varType varType;

    private TypeAddressFormatter formatter;

    public Address(int num, varType varType, TypeAddress Type) {
        this.num = num;
        this.Type = Type;
        this.varType = varType;
        setFormatter();
    }

    public Address(int num, varType varType) {
        this.num = num;
        this.Type = TypeAddress.Direct;
        this.varType = varType;
        setFormatter();
    }

    private void setFormatter() {
        switch (Type) {
            case Direct:
                this.formatter = new DirectAddressFormatter();
                break;
            case Indirect:
                this.formatter = new IndirectAddressFormatter();
                break;
            case Imidiate:
                this.formatter = new ImmediateAddressFormatter();
                break;
        }
    }

    public String toString() {
        return formatter.format(num);
    }
}
