package net.imglib2.matrix.shootout;

import net.imagej.mesh.Vertex3;
import net.imagej.mesh.Vertex3Pool;
import org.mastodon.collection.RefList;
import org.mastodon.collection.ref.RefArrayList;

import static net.imglib2.matrix.shootout.Vec3DBuffer.vlen;

public class ImageJMeshVec3AddTest extends AbstractVec3AddTest
{
	@Override
	public void addAsVec3( final Vec3DBuffer bufA, final Vec3DBuffer bufB, final Vec3DBuffer bufC )
	{
		//Vertex3Pool vertex3Pool = new Vertex3Pool();

		final RefList< Vertex3 > A = new RefArrayList<>( new Vertex3Pool( bufA.getBytes() ) );
		final RefList< Vertex3 > B = new RefArrayList<>( new Vertex3Pool( bufB.getBytes() ) );
		final RefList< Vertex3 > C = new RefArrayList<>( new Vertex3Pool( bufC.getBytes() ) );

		final Vertex3 ref1 = A.createRef();
		final Vertex3 ref2 = A.createRef();
		final Vertex3 ref3 = A.createRef();

		final int size = A.size();
		for ( int i = 0; i < size; ++i )
		{
			final Vertex3 a = A.get( i, ref1 );
			final Vertex3 b = B.get( i, ref2 );
			final Vertex3 c = C.get( i, ref3 );
			for ( int d = 0; d < 3; ++d )
				c.setDoublePosition( a.getDoublePosition( d ) + b.getDoublePosition( d ), d );
		}
	}
}
