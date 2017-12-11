package net.imglib2.matrix.shootout;

import org.junit.Test;

import static net.imglib2.matrix.shootout.Vec3dBuffer.vlen;
import static org.junit.Assert.assertEquals;

public abstract class AbstractVec3fAddTest
{
	public abstract void addAsVec3( final Vec3fBuffer bufA, final Vec3fBuffer bufB, final Vec3fBuffer bufC );

	@Test
	public void testVec3Add()
	{
		final int size = 1000;

		final Vec3fBuffer bufA = new Vec3fBuffer( size );
		final Vec3fBuffer bufB = new Vec3fBuffer( size );
		final Vec3fBuffer bufCExpected = new Vec3fBuffer( size );
		bufA.fillRandom();
		bufB.fillRandom();
		for ( int i = 0; i < size * vlen; ++i )
			bufCExpected.floats.put( i, bufA.floats.get( i ) + bufB.floats.get( i ) );

		final Vec3fBuffer bufC = new Vec3fBuffer( size );
		addAsVec3( bufA, bufB, bufC );

		for ( int i = 0; i < size * vlen; ++i )
			assertEquals( bufCExpected.floats.get( i ), bufC.floats.get( i ), 0.0000001 );
	}
}
