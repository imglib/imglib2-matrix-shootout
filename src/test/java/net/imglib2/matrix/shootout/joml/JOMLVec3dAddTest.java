package net.imglib2.matrix.shootout.joml;

import net.imglib2.matrix.shootout.AbstractVec3dAddTest;
import net.imglib2.matrix.shootout.Vec3dBuffer;
import org.joml.Vector3d;

import java.nio.DoubleBuffer;

/**
 * Double 3D vector addition test using JOML's Vector3d
 *
 * @author Ulrik Günther <hello@ulrik.is>
 */
public class JOMLVec3dAddTest extends AbstractVec3dAddTest {
  @Override
  public void addAsVec3(Vec3dBuffer bufA, Vec3dBuffer bufB, Vec3dBuffer bufC) {
    final int size = bufA.getDoubles().remaining()/3;

    final DoubleBuffer bufferA = bufA.getDoubles();
    final DoubleBuffer bufferB = bufB.getDoubles();
    final DoubleBuffer bufferC = bufC.getDoubles();

    for(int i = 0; i < size; i++) {
      final Vector3d a = new Vector3d().set(bufferA);
      final Vector3d b = new Vector3d().set(bufferB);

      new Vector3d().set(a.add(b)).get(bufferC);

      bufferA.position(bufferA.position()+3);
      bufferB.position(bufferB.position()+3);
      bufferC.position(bufferC.position()+3);
    }
  }
}
