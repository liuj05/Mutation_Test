package org.pitest.mutationtest.engine.gregor.mutators;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.pitest.mutationtest.engine.MutationIdentifier;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;

public enum ANDMutator implements MethodMutatorFactory {

    ARGUMENT_NUMBER_DECREASE_MUTATOR;

    @Override
    public MethodVisitor create(final MutationContext context,
                                final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
        return new ArgumentDecreaseVisitor(context, methodVisitor, this);
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

class ArgumentDecreaseVisitor extends MethodVisitor {

    private final MethodMutatorFactory factory;
    private final MutationContext      context;

    public ArgumentDecreaseVisitor(final MutationContext context,
                                      final MethodVisitor writer, final MethodMutatorFactory factory) {
        super(Opcodes.ASM6, writer);
        this.factory = factory;
        this.context = context;
    }

    @Override
    public void visitMethodInsn(final int opcode, final String owner,
                                final String name, final String desc, final boolean itf) {

        if (hasEnoughArguments(desc)) {
            final MutationIdentifier newId = this.context.registerMutation(
                    this.factory, "removed two arguments in " + owner + "::" + name);

            Type[] arguments = Type.getArgumentTypes(desc);
            int i = arguments.length - 1;
            int j = arguments.length - 2;

            Type stackTop = arguments[i];
            Type secondStack = arguments[j];

            //pop stack top
            if (stackTop.getSize() == 1) {
                mv.visitInsn(Opcodes.POP);
                
            } else {
                mv.visitInsn(Opcodes.POP2);
            }
            
            //pop second argument in stack
            if (secondStack.getSize() == 1) {
                mv.visitInsn(Opcodes.POP);
                
            } else {
                mv.visitInsn(Opcodes.POP2);
            }

            Type[] newArguments = new Type[i];
            for (int k = 0; k < i; k++) {
                newArguments[k] = arguments[k];
            }
            
            String newDesc = Type.getMethodDescriptor(Type.getReturnType(desc), newArguments);
            this.mv.visitMethodInsn(opcode, owner, name, newDesc, itf);
        } else {
            this.mv.visitMethodInsn(opcode, owner, name, desc, itf);
        }
    }

    private boolean hasEnoughArguments(String desc) {
        return Type.getArgumentTypes(desc).length > 2 ? true : false;
    }

}