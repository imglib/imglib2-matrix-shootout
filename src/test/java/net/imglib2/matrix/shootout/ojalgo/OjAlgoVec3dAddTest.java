package net.imglib2.matrix.shootout.ojalgo;

import net.imglib2.matrix.shootout.AbstractVec3dAddTest;
import net.imglib2.matrix.shootout.Vec3dBuffer;

public class OjAlgoVec3dAddTest extends AbstractVec3dAddTest
{

	@Override
	public void addAsVec3( final Vec3dBuffer bufA, final Vec3dBuffer bufB, final Vec3dBuffer bufC )
	{

		final OjAlgoVectorView a = new OjAlgoVectorView( bufA.getDoubles(), Vec3dBuffer.vlen );
		final OjAlgoVectorView b = new OjAlgoVectorView( bufB.getDoubles(), Vec3dBuffer.vlen );
		final OjAlgoVectorView c = new OjAlgoVectorView( bufC.getDoubles(), Vec3dBuffer.vlen );
		final int numVectors = a.numVectors();
		final int nDim = ( int ) a.count();
		for ( int i = 0; i < numVectors; ++i )
		{
			a.setIndex( i );
			b.setIndex( i );
			c.setIndex( i );
			for ( int k = 0; k < nDim; ++k )
				c.set( k, a.doubleValue( k ) + b.doubleValue( k ) );
		}
	}
}
