/*
 * Copyright (c) 2006 JMockit developers
 * This file is subject to the terms of the MIT license (see LICENSE.txt).
 */
package mockit.internal.classGeneration;

import javax.annotation.*;

import mockit.asm.classes.*;
import mockit.asm.jvmConstants.*;

/**
 * Generates a concrete subclass for an <tt>abstract</tt> base class.
 */
public final class ConcreteSubclass<T> extends ImplementationClass<T>
{
   public ConcreteSubclass(@Nonnull Class<?> baseClass) { super(baseClass); }

   @Nonnull @Override
   protected ClassVisitor createMethodBodyGenerator(@Nonnull ClassReader cr) {
      return new BaseSubclassGenerator(sourceClass, cr, null, generatedClassName, false) {
         @Override
         protected void generateMethodImplementation(
            String className, int access, @Nonnull String name, @Nonnull String desc, @Nullable String signature,
            @Nullable String[] exceptions
         ) {
            mw = cw.visitMethod(Access.PUBLIC, name, desc, signature, exceptions);
            generateEmptyImplementation(desc);
         }
      };
   }
}
