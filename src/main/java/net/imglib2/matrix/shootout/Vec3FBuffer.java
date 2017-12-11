package net.imglib2.matrix.shootout;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public class Vec3FBuffer
{
	public static final int vlen = 3;

	private final int size;

	private final ByteBuffer bytes;

	final FloatBuffer floats;

	public Vec3FBuffer(final int size )
	{
		this.size = size;
		final int nbytes = Double.BYTES * vlen * size;
		bytes = ByteBuffer.allocateDirect( nbytes );
		bytes.order( ByteOrder.nativeOrder() );
		floats = bytes.asFloatBuffer();
	}

	public void fillRandom()
	{
		for ( int i = 0; i < size * vlen; ++i )
			floats.put( i, (float)Math.random() );
	}

	public int getSize()
	{
		return size;
	}

	public ByteBuffer getBytes()
	{
		return bytes;
	}

	public FloatBuffer getFloats() { return floats; }
}