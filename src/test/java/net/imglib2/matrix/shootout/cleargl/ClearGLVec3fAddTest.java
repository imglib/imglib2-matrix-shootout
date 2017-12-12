package net.imglib2.matrix.shootout.cleargl;

import cleargl.GLVector;
import net.imglib2.matrix.shootout.AbstractVec3fAddTest;
import net.imglib2.matrix.shootout.Vec3fBuffer;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

/**
 * Float 3D vector addition test using ClearGL's GLVector
 *
 * @author Ulrik Günther <hello@ulrik.is>
 */
public class ClearGLVec3fAddTest extends AbstractVec3fAddTest {
  @Override
  public void addAsVec3(Vec3fBuffer bufA, Vec3fBuffer bufB, Vec3fBuffer bufC) {
    final int size = bufA.getFloats().remaining()/3;
    final FloatBuffer bufferA = bufA.getFloats();
    final FloatBuffer bufferB = bufB.getFloats();
    final ByteBuffer bufferC = bufC.getBytes();

    for(int i = 0; i < size; i++) {
      final GLVector a = new GLVector(0.0f, 0.0f, 0.0f);
      final GLVector b = new GLVector(0.0f, 0.0f, 0.0f);
      final GLVector c = new GLVector(0.0f, 0.0f, 0.0f);

      bufferA.get(a.toFloatArray(), 0, 3);
      bufferB.get(b.toFloatArray(), 0, 3);

      c.plusAssign(a.plus(b));
      c.put(bufferC);
    }
  }
}
