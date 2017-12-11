package net.imglib2.matrix.shootout;

import static net.imglib2.matrix.shootout.Vec3DBuffer.vlen;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public abstract class AbstractVec3AddTest
{
	public abstract void addAsVec3( final Vec3DBuffer bufA, final Vec3DBuffer bufB, final Vec3DBuffer bufC );

	@Test
	public void testVec3Add()
	{
		final int size = 1000;

		final Vec3DBuffer bufA = new Vec3DBuffer( size );
		final Vec3DBuffer bufB = new Vec3DBuffer( size );
		final Vec3DBuffer bufCExpected = new Vec3DBuffer( size );
		bufA.fillRandom();
		bufB.fillRandom();
		for ( int i = 0; i < size * vlen; ++i )
			bufCExpected.doubles.put( i, bufA.doubles.get( i ) + bufB.doubles.get( i ) );

		final Vec3DBuffer bufC = new Vec3DBuffer( size );
		addAsVec3( bufA, bufB, bufC );

		for ( int i = 0; i < size * vlen; ++i )
			assertEquals( bufCExpected.doubles.get( i ), bufC.doubles.get( i ), 0.0000001 );
	}
}
