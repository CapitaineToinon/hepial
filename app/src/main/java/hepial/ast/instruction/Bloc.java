package hepial.ast.instruction;

import java.util.ArrayList;
import hepial.ast.ASTVisitor;

/*
 * Represent a group of instructions node inside the AST.
 */
public class Bloc extends Instruction {

    /**
     * The list of instructions
     */
    private ArrayList<Instruction> instructions = null;

    /**
     * Constructor with provided list
     */
    public Bloc(ArrayList<Instruction> instructions, String fl, int line, int col) {
        super(fl, line, col);
        this.setInstructions(instructions);
    }

    /**
     * Empty constructor
     */
    public Bloc(String fl, int line, int col) {
        this(new ArrayList<Instruction>(), fl, line, col);
    }

    /**
     * Get the list of instructions
     */
    public ArrayList<Instruction> getInstructions() {
        return this.instructions;
    }

    /**
     * Set the list of instructions
     */
    public void setInstructions(ArrayList<Instruction> instructions) {
        if (instructions.contains(null)) {
            throw new NullPointerException("instr cannot be null");
        }
        this.instructions = instructions;
    }

    /**
     * Add an instruction to this bloc
     */
    public void addInstruction(Instruction instr) {
        if (instr == null) {
            throw new NullPointerException("instr cannot be null");
        }
        this.instructions.add(instr);
    }

    /**
     * Get the size of instructions
     */
    public int size() {
        return this.instructions.size();
    }

    /**
     * Accepts a AST visitor
     */
    @Override
    public Object accept(ASTVisitor visitor) throws Exception {
        return visitor.visit(this);
    }
}
