package org.pitest.mutationtest.engine.gregor.mutators;

import java.util.HashMap;
import java.util.Map;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.gregor.AbstractInsnMutator;
import org.pitest.mutationtest.engine.gregor.InsnSubstitution;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;
import org.pitest.mutationtest.engine.gregor.ZeroOperandMutation;

// replace shift operator by bitwise operator

public enum OSBNMutatorAND implements MethodMutatorFactory {
    OSBN_MUTATOR_AND;
    @Override
    public MethodVisitor create(final MutationContext context, final MethodInfo methodInfo,
            final MethodVisitor methodVisitor) {
        return new OSBNMethodVisitor(this, methodInfo, context, methodVisitor);
    }

    @Override
    public String getGloballyUniqueId() {
        return this.getClass().getName();
    }

    @Override
    public String getName() {
        return name();
    }
}

class OSBNMethodVisitor extends AbstractInsnMutator {
    public OSBNMethodVisitor(final MethodMutatorFactory factory, final MethodInfo methodInfo,
            final MutationContext context, final MethodVisitor writer) {
        super(factory, methodInfo, context, writer);
    }

    private static final Map<Integer, ZeroOperandMutation> MUTATIONS = new HashMap<Integer, ZeroOperandMutation>();
    static {
        MUTATIONS.put(Opcodes.ISHL, new InsnSubstitution(Opcodes.IAND, "Replace Int Left Shift with bitwise &"));
        
        MUTATIONS.put(Opcodes.ISHR, new InsnSubstitution(Opcodes.IAND, "Replace Int Right Shift with with bitwise &"));

        MUTATIONS.put(Opcodes.LSHL, new InsnSubstitution(Opcodes.LAND, "Replace Long Left Shift with bitwise &"));
        
        MUTATIONS.put(Opcodes.LSHR, new InsnSubstitution(Opcodes.LAND, "Replace Long Right Shift with bitwise &"));
    }

    @Override
    protected Map<Integer, ZeroOperandMutation> getMutations() {
        return MUTATIONS;
    }
}
