package org.pitest.mutationtest.engine.gregor.mutators;

import java.util.HashMap;
import java.util.Map;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.gregor.AbstractJumpMutator;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;

public enum RORMutator implements MethodMutatorFactory {

    ROR_MUTATOR_GT;

    @Override
    public MethodVisitor create(final MutationContext context, final MethodInfo methodInfo,
            final MethodVisitor methodVisitor) {
        return new RORMethodVisitor(this, context, methodVisitor);
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

class RORMethodVisitor extends AbstractJumpMutator {

    private static final String                     DESCRIPTION = "changed relational operators with >";
    private static final Map<Integer, Substitution> MUTATIONS = new HashMap<Integer, Substitution>();

    static {
        MUTATIONS.put(Opcodes.IF_ICMPLT, new Substitution(Opcodes.IF_ICMPGT, DESCRIPTION));
        MUTATIONS.put(Opcodes.IF_ICMPGE, new Substitution(Opcodes.IF_ICMPGT, DESCRIPTION));
        MUTATIONS.put(Opcodes.IF_ICMPLE, new Substitution(Opcodes.IF_ICMPGT, DESCRIPTION));
        MUTATIONS.put(Opcodes.IF_ICMPEQ, new Substitution(Opcodes.IF_ICMPGT, DESCRIPTION));
        MUTATIONS.put(Opcodes.IF_ICMPNE, new Substitution(Opcodes.IF_ICMPGT, DESCRIPTION));
    }

    RORMethodVisitor(final MethodMutatorFactory factory, final MutationContext context,
            final MethodVisitor delegateMethodVisitor) {
        super(factory, context, delegateMethodVisitor);
    }

    @Override
    protected Map<Integer, Substitution> getMutations() {
        return MUTATIONS;
    }
}