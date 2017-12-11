package net.imglib2.matrix.shootout;

import org.junit.Test;

import static net.imglib2.matrix.shootout.Vec3DBuffer.vlen;
import static org.junit.Assert.assertEquals;

public abstract class AbstractVec3FAddTest
{
	public abstract void addAsVec3( final Vec3FBuffer bufA, final Vec3FBuffer bufB, final Vec3FBuffer bufC );

	@Test
	public void testVec3Add()
	{
		final int size = 1000;

		final Vec3FBuffer bufA = new Vec3FBuffer( size );
		final Vec3FBuffer bufB = new Vec3FBuffer( size );
		final Vec3FBuffer bufCExpected = new Vec3FBuffer( size );
		bufA.fillRandom();
		bufB.fillRandom();
		for ( int i = 0; i < size * vlen; ++i )
			bufCExpected.floats.put( i, bufA.floats.get( i ) + bufB.floats.get( i ) );

		final Vec3FBuffer bufC = new Vec3FBuffer( size );
		addAsVec3( bufA, bufB, bufC );

		for ( int i = 0; i < size * vlen; ++i )
			assertEquals( bufCExpected.floats.get( i ), bufC.floats.get( i ), 0.0000001 );
	}
}
