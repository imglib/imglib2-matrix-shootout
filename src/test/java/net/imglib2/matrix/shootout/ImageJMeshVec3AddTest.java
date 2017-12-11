package net.imglib2.matrix.shootout;

import net.imglib2.matrix.shootout.mastodon.Vector3f;
import net.imglib2.matrix.shootout.mastodon.Vector3fPool;
import org.mastodon.collection.RefList;
import org.mastodon.collection.ref.RefArrayList;

import static net.imglib2.matrix.shootout.Vec3DBuffer.vlen;

public class ImageJMeshVec3AddTest extends AbstractVec3FAddTest
{
	@Override
	public void addAsVec3( final Vec3FBuffer bufA, final Vec3FBuffer bufB, final Vec3FBuffer bufC )
	{
		//Vector3fPool vertex3Pool = new Vector3fPool();


        Vector3fPool poolA = new Vector3fPool(bufA.getBytes());
        Vector3fPool poolB = new Vector3fPool(bufB.getBytes());
        Vector3fPool poolC = new Vector3fPool(bufC.getBytes());

        //System.out.println( "FloatBuffer limit: " + poolA.getFloatBuffer().limit() );

		final RefList< Vector3f > A = poolA.asList();
		final RefList< Vector3f > B = poolB.asList();
		final RefList< Vector3f > C = poolC.asList();

		final Vector3f ref1 = A.createRef();
		final Vector3f ref2 = A.createRef();
		final Vector3f ref3 = A.createRef();

		//System.out.println( "A size: " + A.size() );

		final int size = A.size();
		for ( int i = 0; i < size; ++i )
		{
			final Vector3f a = A.get( i, ref1 );
			final Vector3f b = B.get( i, ref2 );
			final Vector3f c = C.get( i, ref3 );
			//System.out.println( "A: " + a + " B: " + b + " C: " + c );
			for ( int d = 0; d < 3; ++d )
				c.setDoublePosition( a.getDoublePosition( d ) + b.getDoublePosition( d ), d );
		}
	}
}
