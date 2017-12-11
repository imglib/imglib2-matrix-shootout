package net.imglib2.matrix.shootout;

public class OjAlgoVec3AddTest extends AbstractVec3AddTest
{

	@Override
	public void addAsVec3( final Vec3DBuffer bufA, final Vec3DBuffer bufB, final Vec3DBuffer bufC )
	{

		final OjAlgoVectorView a = new OjAlgoVectorView( bufA.getDoubles(), Vec3DBuffer.vlen );
		final OjAlgoVectorView b = new OjAlgoVectorView( bufB.getDoubles(), Vec3DBuffer.vlen );
		final OjAlgoVectorView c = new OjAlgoVectorView( bufC.getDoubles(), Vec3DBuffer.vlen );
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
