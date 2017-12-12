package net.imglib2.matrix.shootout.mastodon;

import java.nio.ByteBuffer;

import org.mastodon.collection.RefList;
import org.mastodon.pool.BufferMappedElement;
import org.mastodon.pool.BufferMappedElementArray;
import org.mastodon.pool.Pool;
import org.mastodon.pool.attributes.DoubleArrayAttribute;
import org.mastodon.pool.attributes.RealPointAttribute;

public class Vector3dPool extends Pool< Vector3d, BufferMappedElement >
{
	final DoubleArrayAttribute< Vector3d > position;

//	static final Vector3dLayout layout = new Vector3dLayout();
	final Vector3dLayout layout;

	public Vector3dPool( final ByteBuffer byteBuffer )
	{
//		super( 0, layout, Vector3d.class, BufferMappedElementArray.wrappingMemPoolFactory( byteBuffer ) );
		super( 0, new Vector3dLayout(), Vector3d.class, BufferMappedElementArray.wrappingMemPoolFactory( byteBuffer ) );
		layout = new Vector3dLayout();
		position = new DoubleArrayAttribute<>( layout.position, this );
	}

	@Override
	public Vector3d create( final Vector3d obj )
	{
		throw new UnsupportedOperationException();
	}

	public Vector3d create()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void delete( final Vector3d obj )
	{
		throw new UnsupportedOperationException();
	}

	@Override
	protected Vector3d createEmptyRef()
	{
		return new Vector3d( this );
	}

	public RefList< Vector3d > asList()
	{
		return new PoolAsList<>( this, this::size );
	}
}
