package net.imglib2.matrix.shootout.mastodon;

import net.imglib2.RealLocalizable;
import org.mastodon.pool.BufferMappedElement;
import org.mastodon.pool.PoolObject;
import org.mastodon.pool.attributes.DoubleArrayAttributeValue;
import org.mastodon.pool.attributes.RealPointAttributeValue;
import org.mastodon.util.DelegateRealLocalizable;
import org.mastodon.util.DelegateRealPositionable;

public class Vector3d extends PoolObject< Vector3d, Vector3dPool, BufferMappedElement > implements RealLocalizable
{
	private final DoubleArrayAttributeValue position;

	Vector3d( final Vector3dPool pool )
	{
		super( pool );

		/*
		 * doesn't send property change events
		 */
		position = pool.position.createQuietAttributeValue( this );
	}

	public Vector3d init(
			final double x,
			final double y,
			final double z )
	{
		pool.position.setQuiet( this, 0, x );
		pool.position.setQuiet( this, 1, y );
		pool.position.setQuiet( this, 2, z );
		return this;
	}

	public void add( Vector3d a, Vector3d b )
	{
		access.putDouble( a.access.getDouble( 0 ) + b.access.getDouble( 0 ), 0 );
		access.putDouble( a.access.getDouble( 8 ) + b.access.getDouble( 8 ), 8 );
		access.putDouble( a.access.getDouble( 16 ) + b.access.getDouble( 16 ), 16 );
//		setX( a.getX() + b.getX() );
//		setY( a.getY() + b.getY() );
//		setZ( a.getZ() + b.getZ() );
	}

	@Override
	protected void setToUninitializedState()
	{
	}

	public double getX()
	{
		return position.get( 0 );
	}

	public void setX( final double x )
	{
		pool.position.setQuiet( this, 0, x );
	}

	public double getY()
	{
		return position.get( 1 );
	}

	public void setY( final double y )
	{
		pool.position.setQuiet( this, 1, y );
	}

	public double getZ()
	{
		return position.get( 2 );
	}

	public void setZ( final double z )
	{
		pool.position.setQuiet( this, 2, z );
	}

	@Override
	public String toString()
	{
		return String.format( "v(%.2f, %.2f, %.2f)", getX(), getY(), getZ() );
	}

	@Override
	public void localize( float[] position )
	{
		position[ 0 ] = ( float ) getX();
		position[ 1 ] = ( float ) getY();
		position[ 2 ] = ( float ) getZ();
	}

	@Override
	public void localize( double[] position )
	{
		position[ 0 ] = getX();
		position[ 1 ] = getY();
		position[ 2 ] = getZ();
	}

	@Override
	public float getFloatPosition( int d )
	{
		return ( float ) position.get( d );
	}

	@Override
	public double getDoublePosition( int d )
	{
		return ( float ) position.get( d );
	}

	public void setFloatPosition( float val, int d )
	{
		position.set( d, val );
	}

	public void setDoublePosition( double val, int d )
	{
		position.set( d, val );
	}

	@Override
	public int numDimensions()
	{
		return 3;
	}
}
