package net.imglib2.matrix.shootout;

import static net.imglib2.matrix.shootout.Vec3DBuffer.vlen;

public class FloatBufferVec3AddTest extends AbstractVec3FAddTest
{
	@Override
	public void addAsVec3( final Vec3FBuffer bufA, final Vec3FBuffer bufB, final Vec3FBuffer bufC )
	{
		for ( int i = 0; i < bufA.getSize() * vlen; ++i )
			bufC.getFloats().put( i, bufA.getFloats().get( i ) + bufB.getFloats().get( i ) );
	}
}
