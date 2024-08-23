package MiniJava.codeGenerator;

/**
 * Created by mohammad hosein on 6/28/2015.
 */

public class Address {
    private int num;
    private TypeAddress Type;
    private varType varType;
    private TypeAddressFormatter formatter;

    public Address(int num, varType varType, TypeAddress Type) {
        setNum(num);
        setType(Type);
        setVarType(varType);
        setFormatter();
    }

    public Address(int num, varType varType) {
        setNum(num);
        setType(TypeAddress.Direct);
        setVarType(varType);
        setFormatter();
    }

    public int getNum() {
        return num;
    }

    public TypeAddress getType() {
        return Type;
    }

    public MiniJava.codeGenerator.varType getVarType() {
        return varType;
    }

    public TypeAddressFormatter getFormatter() {
        return formatter;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setType(TypeAddress type) {
        Type = type;
    }

    public void setVarType(MiniJava.codeGenerator.varType varType) {
        this.varType = varType;
    }

    public void setFormatter(TypeAddressFormatter formatter) {
        this.formatter = formatter;
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
