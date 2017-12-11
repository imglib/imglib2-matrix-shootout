package net.imglib2.matrix.shootout.mastodon;

import net.imglib2.RealLocalizable;
import org.mastodon.pool.BufferMappedElement;
import org.mastodon.pool.PoolObject;
import org.mastodon.pool.attributes.FloatArrayAttributeValue;

/**
 * A data structure for storing a 3D vertex
 *
 * @author Tobias Pietzsch (MPI-CBG)
 * @author Kyle Harrington (University of Idaho, Moscow)
 */
public class Vector3f extends PoolObject<Vector3f, Vector3fPool, BufferMappedElement > implements RealLocalizable
{
	private final FloatArrayAttributeValue position;

	public Vector3f(final Vector3fPool pool )
	{
		super( pool );

		/*
		 * doesn't send property change events
		 */
		position = pool.position.createQuietAttributeValue( this );
	}

	public Vector3f init(
			final float x,
			final float y,
			final float z)
	{
		// like this:
		pool.position.setQuiet( this, 0, x );
		pool.position.setQuiet( this, 1, y );
		pool.position.setQuiet( this, 2, z );

		return this;
	}

	@Override
	protected void setToUninitializedState()
	{}

	public float getX()
	{
		return position.get( 0 );
	}

	public void setX( final float x)
	{
		pool.position.setQuiet( this, 0, x );
	}
	
	public float getY()
	{
		return position.get( 1 );
	}
	
	public void setY( final float y)
	{
		pool.position.setQuiet( this, 1, y );
	}
	
	public float getZ()
	{
		return position.get( 2 );
	}

	public void setZ( final float z)
	{
		pool.position.setQuiet( this, 2, z );
	}

	
	@Override
	public String toString()
	{
		return String.format( "v(%.2f, %.2f, %.2f)", getX(), getY(), getZ() );
	}

	@Override
	public void localize(float[] position) {
		position[0] = getX();
		position[1] = getY();
		position[2] = getZ();
	}

	@Override
	public void localize(double[] position) {
		position[0] = getX();
		position[1] = getY();
		position[2] = getZ();
	}

	@Override
	public float getFloatPosition(int d) {
		return position.get( d );
	}

	@Override
	public double getDoublePosition(int d) {
		return position.get( d );
	}

	public void setFloatPosition(float val, int d) {
		position.set(d,val);
	}

	public void setDoublePosition(double val, int d) {
		position.set(d,(float)val);
	}

	@Override
	public int numDimensions() {
		return 3;
	}
}