package net.imglib2.matrix.shootout;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;

public class Vec3DBuffer
{
	public static final int vlen = 3;

	private final int size;

	private final ByteBuffer bytes;

	final DoubleBuffer doubles;

	public Vec3DBuffer( final int size )
	{
		this.size = size;
		final int nbytes = Double.BYTES * vlen * size;
		bytes = ByteBuffer.allocateDirect( nbytes );
		bytes.order( ByteOrder.nativeOrder() );
		doubles = bytes.asDoubleBuffer();
	}

	public void fillRandom()
	{
		for ( int i = 0; i < size * vlen; ++i )
			doubles.put( i, Math.random() );
	}

	public int getSize()
	{
		return size;
	}

	public ByteBuffer getBytes()
	{
		return bytes;
	}

	public DoubleBuffer getDoubles()
	{
		return doubles;
	}

}