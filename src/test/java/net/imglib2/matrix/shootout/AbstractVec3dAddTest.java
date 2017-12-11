package net.imglib2.matrix.shootout;

import static net.imglib2.matrix.shootout.Vec3dBuffer.vlen;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public abstract class AbstractVec3dAddTest
{
	public abstract void addAsVec3( final Vec3dBuffer bufA, final Vec3dBuffer bufB, final Vec3dBuffer bufC );

	@Test
	public void testVec3Add()
	{
		final int size = 1000;

		final Vec3dBuffer bufA = new Vec3dBuffer( size );
		final Vec3dBuffer bufB = new Vec3dBuffer( size );
		final Vec3dBuffer bufCExpected = new Vec3dBuffer( size );
		bufA.fillRandom();
		bufB.fillRandom();
		for ( int i = 0; i < size * vlen; ++i )
			bufCExpected.doubles.put( i, bufA.doubles.get( i ) + bufB.doubles.get( i ) );

		final Vec3dBuffer bufC = new Vec3dBuffer( size );
		addAsVec3( bufA, bufB, bufC );

		for ( int i = 0; i < size * vlen; ++i )
			assertEquals( bufCExpected.doubles.get( i ), bufC.doubles.get( i ), 0.0000001 );
	}
}
