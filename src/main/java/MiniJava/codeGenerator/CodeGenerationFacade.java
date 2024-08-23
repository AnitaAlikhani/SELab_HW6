package MiniJava.codeGenerator;

import MiniJava.semantic.symbol.Symbol;
import MiniJava.semantic.symbol.SymbolTable;
import MiniJava.semantic.symbol.SymbolType;

public class CodeGenerationFacade {
    private CodeGenerator codeGenerator;
    private Memory memory;
    private SymbolTable symbolTable;

    public CodeGenerationFacade() {
        this.memory = new Memory();
        this.symbolTable = new SymbolTable(memory);
        this.codeGenerator = new CodeGenerator(symbolTable, memory);
    }

    public void addVariable(String className, String methodName, String varName, SymbolType type) {
        symbolTable.setLastType(type);
        symbolTable.addMethodLocalVariable(className, methodName, varName);
    }

    public void generateAssignment(String className, String methodName, String variableName, int value) {
        Symbol symbol = symbolTable.get(className, methodName, variableName);
        if (symbol != null) {
            Address varAddress = new Address(symbol.address, varType.Int, TypeAddress.Direct);
            Address valueAddress = new Address(value, varType.Int, TypeAddress.Imidiate);
            codeGenerator.generateAssignment(varAddress, valueAddress);
        } else {
            System.err.println("Variable not found in symbol table.");
        }
    }

    public void generateArithmeticOperation(String className, String methodName, String destVar, String srcVar1, String srcVar2, Operation operation) {
        Symbol destSymbol = symbolTable.get(className, methodName, destVar);
        Symbol srcSymbol1 = symbolTable.get(className, methodName, srcVar1);
        Symbol srcSymbol2 = symbolTable.get(className, methodName, srcVar2);

        if (destSymbol != null && srcSymbol1 != null && srcSymbol2 != null) {
            Address destAddress = new Address(destSymbol.address, varType.Int, TypeAddress.Direct);
            Address srcAddress1 = new Address(srcSymbol1.address, varType.Int, TypeAddress.Direct);
            Address srcAddress2 = new Address(srcSymbol2.address, varType.Int, TypeAddress.Direct);
            codeGenerator.generateOperation(destAddress, srcAddress1, srcAddress2, operation);
        } else {
            System.err.println("One or more variables not found in symbol table.");
        }
    }

    public void printGeneratedCode() {
        memory.pintCodeBlock();
    }
}
