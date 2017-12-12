package net.imglib2.matrix.shootout.ojalgo;

import java.nio.DoubleBuffer;

import org.ojalgo.access.Access1D;
import org.ojalgo.access.Mutate1D;

public class OjAlgoVectorView implements Access1D< Double >, Mutate1D
{

	private final DoubleBuffer buf;

	private final int nDim;

	private final int numVectors;

	private int offset;

	public OjAlgoVectorView( final DoubleBuffer buf, final int nDim )
	{
		super();
		assert buf.capacity() % nDim == 0;
		this.buf = buf;
		this.nDim = nDim;
		this.numVectors = buf.capacity() / nDim;
		this.offset = 0;
	}

	public void add( Access1D< Double > x, Access1D< Double > y )
	{
		loopAll( i -> set( i, x.get( i ) + y.get( i ) ) );
	}

	public void setIndex( final int index )
	{
		this.offset = nDim * index;
	}

	public int numVectors()
	{
		return numVectors;
	}

	@Override
	public long count()
	{
		return nDim;
	}

	@Override
	public double doubleValue( final long index )
	{
		return this.buf.get( offset + ( int ) index );
	}

	@Override
	public Double get( final long index )
	{
		return doubleValue( index );
	}

	@Override
	public void add( final long index, final double addend )
	{
		final int pos = offset + ( int ) index;
		this.buf.put( pos, this.buf.get( pos ) + addend );
	}

	@Override
	public void add( final long index, final Number addend )
	{
		add( index, addend.doubleValue() );
	}

	@Override
	public void set( final long index, final double value )
	{
		final int pos = offset + ( int ) index;
		this.buf.put( pos, value );
	}

	@Override
	public void set( final long index, final Number value )
	{
		set( index, value.doubleValue() );
	}

}
