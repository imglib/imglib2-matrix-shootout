package net.imglib2.matrix.shootout;

import java.nio.DoubleBuffer;
import net.imglib2.matrix.shootout.ojalgo.OjAlgoVectorView;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import static net.imglib2.matrix.shootout.Vec3dBuffer.vlen;

@State( Scope.Benchmark )
public class BufferVec3dAddBenchmark
{
	private static final int size = 1000;

	private final Vec3dBuffer bufA;

	private final Vec3dBuffer bufB;

	private final Vec3dBuffer bufC;

	public BufferVec3dAddBenchmark()
	{
		bufA = new Vec3dBuffer( size );
		bufB = new Vec3dBuffer( size );
		bufC = new Vec3dBuffer( size );
		bufA.fillRandom();
		bufB.fillRandom();
	}

	private DoubleBuffer a;

	private DoubleBuffer b;

	private DoubleBuffer c;

	@Setup
	public void setup()
	{
		a = bufA.getDoubles();
		b = bufB.getDoubles();
		c = bufC.getDoubles();
	}

	@Benchmark
	public void benchmark()
	{
		final int len = bufA.getSize() * vlen;
		for ( int i = 0; i < len; ++i )
			c.put( i, a.get( i ) + b.get( i ) );
	}

	public static void main( final String... args ) throws RunnerException
	{
		final Options opt = new OptionsBuilder()
				.include( BufferVec3dAddBenchmark.class.getSimpleName() )
				.forks( 0 )
				.warmupIterations( 10 )
				.measurementIterations( 20 )
				.warmupTime( TimeValue.milliseconds( 500 ) )
				.measurementTime( TimeValue.milliseconds( 500 ) )
				.build();
		new Runner( opt ).run();
	}
}
