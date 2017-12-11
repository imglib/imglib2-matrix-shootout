package net.imglib2.matrix.shootout;

import static net.imglib2.matrix.shootout.Vec3dBuffer.vlen;

public class BufferVec3fAddTest extends AbstractVec3fAddTest
{
	@Override
	public void addAsVec3( final Vec3fBuffer bufA, final Vec3fBuffer bufB, final Vec3fBuffer bufC )
	{
		for ( int i = 0; i < bufA.getSize() * vlen; ++i )
			bufC.getFloats().put( i, bufA.getFloats().get( i ) + bufB.getFloats().get( i ) );
	}
}
