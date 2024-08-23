package MiniJava.semantic.symbol;

import MiniJava.codeGenerator.Address;
import MiniJava.codeGenerator.Memory;
import MiniJava.errorHandler.ErrorHandler;

public class SymbolTableFacade {
    private SymbolTable symbolTable;
    private Memory memory;

    public SymbolTableFacade(Memory memory) {
        this.memory = memory;
        this.symbolTable = new SymbolTable(memory);
    }

    public void addClass(String className) {
        symbolTable.addClass(className);
    }

    public void addFieldToClass(String className, String fieldName, SymbolType fieldType) {
        symbolTable.setLastType(fieldType);
        symbolTable.addField(fieldName, className);
    }

    public void addMethodToClass(String className, String methodName, SymbolType returnType) {
        symbolTable.setLastType(returnType);
        int methodAddress = memory.getCurrentCodeBlockAddress();
        symbolTable.addMethod(className, methodName, methodAddress);
    }

    public void addParameterToMethod(String className, String methodName, String parameterName, SymbolType parameterType) {
        symbolTable.setLastType(parameterType);
        symbolTable.addMethodParameter(className, methodName, parameterName);
    }

    public void addLocalVariableToMethod(String className, String methodName, String localVariableName, SymbolType variableType) {
        symbolTable.setLastType(variableType);
        symbolTable.addMethodLocalVariable(className, methodName, localVariableName);
    }

    public void startMethodCall(String className, String methodName) {
        symbolTable.startCall(className, methodName);
    }

    public Address getMethodReturnAddress(String className, String methodName) {
        return new Address(symbolTable.getMethodReturnAddress(className, methodName), varType.Address);
    }

    public int getMethodCallerAddress(String className, String methodName) {
        return symbolTable.getMethodCallerAddress(className, methodName);
    }

    public SymbolType getMethodReturnType(String className, String methodName) {
        return symbolTable.getMethodReturnType(className, methodName);
    }

    public int getMethodAddress(String className, String methodName) {
        return symbolTable.getMethodAddress(className, methodName);
    }

}
