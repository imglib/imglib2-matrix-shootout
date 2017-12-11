package net.imglib2.matrix.shootout;

import static net.imglib2.matrix.shootout.Vec3DBuffer.vlen;

public class DoubleBufferVec3AddTest extends AbstractVec3AddTest
{
	@Override
	public void addAsVec3( final Vec3DBuffer bufA, final Vec3DBuffer bufB, final Vec3DBuffer bufC )
	{
		for ( int i = 0; i < bufA.getSize() * vlen; ++i )
			bufC.getDoubles().put( i, bufA.getDoubles().get( i ) + bufB.getDoubles().get( i ) );
	}
}
