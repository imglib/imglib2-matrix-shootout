package net.imglib2.matrix.shootout.mastodon;


import org.mastodon.pool.BufferMappedElement;
import org.mastodon.pool.BufferMappedElementArray;
import org.mastodon.pool.Pool;
import org.mastodon.pool.SingleArrayMemPool;
import org.mastodon.pool.attributes.FloatArrayAttribute;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

/**
 * A class for storing vertices in a RefPool
 *
 * @author Tobias Pietzsch (MPI-CBG)
 * @author Kyle Harrington (University of Idaho, Moscow)
 */
public class Vector3fPool extends Pool<Vector3f, BufferMappedElement >
{
	final FloatArrayAttribute<Vector3f> position;

	final Vector3fLayout vertexLayout;

	public Vector3fPool(final int initialCapacity )
	{
		super( initialCapacity, new Vector3fLayout(), Vector3f.class, SingleArrayMemPool.factory( BufferMappedElementArray.factory ) );
		this.vertexLayout = new Vector3fLayout();// we need to make these twice
		position = new FloatArrayAttribute<>( vertexLayout.position, this );
	}

	public Vector3fPool(ByteBuffer bb)
	{
		super( bb.limit() / 12, new Vector3fLayout(), Vector3f.class, SingleArrayMemPool.factory( BufferMappedElementArray.wrappingFactory(bb) ) );
		this.vertexLayout = new Vector3fLayout();// we need to make these twice
		position = new FloatArrayAttribute<>( vertexLayout.position, this );
		for( int k = 0; k < bb.limit() / 12; k++ ) {
			this.create();
		}
	}

	public Vector3f create()
	{
		return super.create( createRef() );
	}

	@Override
	public Vector3f create(final Vector3f obj )
	{
		return super.create( obj );
	}

	@Override
	public void delete( final Vector3f obj )
	{
		super.delete( obj );
	}

	@Override
	protected Vector3f createEmptyRef()
	{
		return new Vector3f( this );
	}

	public FloatBuffer getFloatBuffer()
	{
		final SingleArrayMemPool< BufferMappedElementArray, ? > memPool = ( SingleArrayMemPool< BufferMappedElementArray, ? > ) getMemPool();
		final BufferMappedElementArray dataArray =  memPool.getDataArray();
		return dataArray.getBuffer().asFloatBuffer();
	}
}