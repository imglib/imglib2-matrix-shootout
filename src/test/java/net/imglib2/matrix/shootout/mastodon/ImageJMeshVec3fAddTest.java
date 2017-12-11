package net.imglib2.matrix.shootout.mastodon;

import net.imglib2.matrix.shootout.AbstractVec3fAddTest;
import net.imglib2.matrix.shootout.Vec3fBuffer;
import org.mastodon.collection.RefList;

public class ImageJMeshVec3fAddTest extends AbstractVec3fAddTest
{
	@Override
	public void addAsVec3( final Vec3fBuffer bufA, final Vec3fBuffer bufB, final Vec3fBuffer bufC )
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
