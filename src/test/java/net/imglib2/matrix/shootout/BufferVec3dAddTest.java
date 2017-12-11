package net.imglib2.matrix.shootout;

import static net.imglib2.matrix.shootout.Vec3dBuffer.vlen;

public class BufferVec3dAddTest extends AbstractVec3dAddTest
{
	@Override
	public void addAsVec3( final Vec3dBuffer bufA, final Vec3dBuffer bufB, final Vec3dBuffer bufC )
	{
		for ( int i = 0; i < bufA.getSize() * vlen; ++i )
			bufC.getDoubles().put( i, bufA.getDoubles().get( i ) + bufB.getDoubles().get( i ) );
	}
}
