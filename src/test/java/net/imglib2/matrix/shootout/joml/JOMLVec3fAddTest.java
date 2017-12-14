package net.imglib2.matrix.shootout.joml;

import net.imglib2.matrix.shootout.AbstractVec3fAddTest;
import net.imglib2.matrix.shootout.Vec3fBuffer;
import org.joml.Vector3f;

import java.nio.FloatBuffer;

/**
 * Float 3D vector addition test using JOML's Vector3f
 *
 * @author Ulrik Günther <hello@ulrik.is>
 */
public class JOMLVec3fAddTest extends AbstractVec3fAddTest {
  @Override
  public void addAsVec3(Vec3fBuffer bufA, Vec3fBuffer bufB, Vec3fBuffer bufC) {
    final int size = bufA.getFloats().remaining()/3;

    final FloatBuffer bufferA = bufA.getFloats();
    final FloatBuffer bufferB = bufB.getFloats();
    final FloatBuffer bufferC = bufC.getFloats();

    for(int i = 0; i < size; i++) {
      final Vector3f a = new Vector3f().set(i*3, bufferA);
      final Vector3f b = new Vector3f().set(i*3, bufferB);

      new Vector3f().set(a.add(b)).get(i*3, bufferC);
    }
  }
}
