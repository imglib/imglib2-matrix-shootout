package net.imglib2.matrix.shootout.mastodon;

import java.nio.ByteBuffer;

import org.mastodon.collection.RefList;
import org.mastodon.pool.BufferMappedElement;
import org.mastodon.pool.BufferMappedElementArray;
import org.mastodon.pool.Pool;
import org.mastodon.pool.attributes.RealPointAttribute;

public class Vector3dPool extends Pool< Vector3d, BufferMappedElement >
{

	static final Vector3dLayout layout = new Vector3dLayout();

	final RealPointAttribute< Vector3d > position;

	public Vector3dPool( final ByteBuffer byteBuffer )
	{
		super( 0, layout, Vector3d.class, BufferMappedElementArray.wrappingMemPoolFactory( byteBuffer ) );
		position = new RealPointAttribute<>( layout.position, this );
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
