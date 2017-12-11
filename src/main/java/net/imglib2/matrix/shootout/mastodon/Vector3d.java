package net.imglib2.matrix.shootout.mastodon;

import org.mastodon.pool.BufferMappedElement;
import org.mastodon.pool.PoolObject;
import org.mastodon.pool.attributes.RealPointAttributeValue;
import org.mastodon.util.DelegateRealLocalizable;
import org.mastodon.util.DelegateRealPositionable;

public class Vector3d extends PoolObject< Vector3d, Vector3dPool, BufferMappedElement >
	implements DelegateRealLocalizable, DelegateRealPositionable
{
	private final RealPointAttributeValue position;

	Vector3d( final Vector3dPool pool )
	{
		super( pool );

		/*
		 * doesn't send property change events
		 */
//		position = pool.position.createQuietAttributeValue( this );

		/*
		 * sends property change events
		 */
		position = pool.position.createAttributeValue( this );
	}

	public Vector3d init( final double... pos )
	{
		pool.position.setPositionQuiet( this, pos );
		return this;
	}

	@Override
	protected void setToUninitializedState()
	{}

	@Override
	public String toString()
	{
		final StringBuilder sb = new StringBuilder();
		char c = '(';
		for ( int i = 0; i < numDimensions(); i++ )
		{
			sb.append( c );
			sb.append( getDoublePosition( i ) );
			c = ',';
		}
		sb.append( ")" );
		return sb.toString();
	}

	@Override
	public RealPointAttributeValue delegate()
	{
		return position;
	}
}
