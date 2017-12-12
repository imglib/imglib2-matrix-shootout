package net.imglib2.matrix.shootout.joml;

import java.util.ArrayList;
import net.imglib2.matrix.shootout.AbstractVec3dAddTest;
import net.imglib2.matrix.shootout.Vec3dBuffer;
import org.joml.Vector3d;

import java.nio.DoubleBuffer;

/**
 * Double 3D vector addition test using JOML's Vector3d
 *
 * @author Ulrik Günther <hello@ulrik.is>
 */
public class JOMLVec3dAddTest extends AbstractVec3dAddTest
{
	@Override
	public void addAsVec3( Vec3dBuffer bufA, Vec3dBuffer bufB, Vec3dBuffer bufC )
	{
		final int size = bufA.getDoubles().remaining() / 3;

		final DoubleBuffer bufferA = bufA.getDoubles();
		final DoubleBuffer bufferB = bufB.getDoubles();
		final DoubleBuffer bufferC = bufC.getDoubles();

		final ArrayList< Vector3d > as = new ArrayList<>();
		final ArrayList< Vector3d > bs = new ArrayList<>();
		for(int i = 0; i < size; i++)
		{
			as.add( fromBuffer( bufferA, i ) );
			bs.add( fromBuffer( bufferB, i ) );
		}
		for(int i = 0; i < size; i++) {
			final Vector3d c = new Vector3d().set(as.get(i).add(bs.get(i)));
			toBuffer(c, bufferC, i);
		}
	}

	public Vector3d fromBuffer( DoubleBuffer buf, int pos )
	{
		buf.position( pos * 3 );
		return new Vector3d().set( buf );
	}

	public void toBuffer( Vector3d v, DoubleBuffer buf, int pos )
	{
		buf.position( pos * 3 );
		v.get( buf );
	}
}
