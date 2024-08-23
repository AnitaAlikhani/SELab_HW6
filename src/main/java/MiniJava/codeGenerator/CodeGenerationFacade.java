package MiniJava.codeGenerator;

import java.util.ArrayList;

public class CodeGenerationFacade {
    private CodeGenerator codeGenerator;

    public CodeGenerationFacade() {
        this.codeGenerator = new CodeGenerator();
    }

    public void addVariable(String name, varType type) {
        Address address = new Address(codeGenerator.getMemory().getNextAddress(), type);
        codeGenerator.getMemory().addVariable(name, address);
    }

    public void generateOperation(String operation, String sourceName, String destinationName) {
        Address source = codeGenerator.getMemory().getAddress(sourceName);
        Address destination = codeGenerator.getMemory().getAddress(destinationName);
        if (source != null && destination != null) {
            Operation op = new Operation(operation, source, destination);
            codeGenerator.addOperation(op);
        } else {
            System.err.println("Invalid source or destination for operation.");
        }
    }

    public ArrayList<Operation> getOperations() {
        return codeGenerator.getOperations();
    }

    public void executeCode() {
        codeGenerator.generateCode();
    }

}
