package net.imglib2.matrix.shootout.mastodon;

import net.imglib2.matrix.shootout.AbstractVec3dAddTest;
import net.imglib2.matrix.shootout.Vec3dBuffer;
import org.mastodon.collection.RefList;

public class MastodonVec3dAddTest extends AbstractVec3dAddTest
{
	@Override
	public void addAsVec3( final Vec3dBuffer bufA, final Vec3dBuffer bufB, final Vec3dBuffer bufC )
	{
		// Something like this ...

//		final List< Vector3d > poolA = new Vector3dPool( bufA.getBytes() ).asList();
//		final List< Vector3d > poolB = new Vector3dPool( bufB.getBytes() ).asList();
//		final List< Vector3d > poolC = new Vector3dPool( bufC.getBytes() ).asList();
//
//		final Iterator< Vector3d > ia = poolA.iterator();
//		final Iterator< Vector3d > ib = poolB.iterator();
//		final Iterator< Vector3d > ic = poolC.iterator();
//
//		while ( ia.hasNext() )
//		{
//			final Vector3d a = ia.next();
//			final Vector3d b = ib.next();
//			final Vector3d c = ic.next();
//			c.setPosition( a );
//			c.move( b );
//		}

		// or this ...
		final RefList< Vector3d > A = new Vector3dPool( bufA.getBytes() ).asList();
		final RefList< Vector3d > B = new Vector3dPool( bufB.getBytes() ).asList();
		final RefList< Vector3d > C = new Vector3dPool( bufC.getBytes() ).asList();

		final Vector3d ref1 = A.createRef();
		final Vector3d ref2 = A.createRef();
		final Vector3d ref3 = A.createRef();

		final int size = A.size();
		for ( int i = 0; i < size; ++i )
		{
			final Vector3d a = A.get( i, ref1 );
			final Vector3d b = B.get( i, ref2 );
			final Vector3d c = C.get( i, ref3 );
			for ( int d = 0; d < 3; ++d )
				c.setPosition( a.getDoublePosition( d ) + b.getDoublePosition( d ), d );
		}

	}
}
