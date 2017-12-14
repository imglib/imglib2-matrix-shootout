package net.imglib2.matrix.shootout;

import java.nio.DoubleBuffer;
import org.joml.Vector3d;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

@State( Scope.Benchmark )
public class JOMLVec3dAddBenchmark
{
	private static final int size = 1000;

	private final Vec3dBuffer bufA;

	private final Vec3dBuffer bufB;

	private final Vec3dBuffer bufC;

	public JOMLVec3dAddBenchmark()
	{
		bufA = new Vec3dBuffer( size );
		bufB = new Vec3dBuffer( size );
		bufC = new Vec3dBuffer( size );
		bufA.fillRandom();
		bufB.fillRandom();
	}

	private DoubleBuffer bufferA;

	private DoubleBuffer bufferB;

	private DoubleBuffer bufferC;

	@Setup
	public void setup()
	{
		bufferA = bufA.getDoubles();
		bufferB = bufB.getDoubles();
		bufferC = bufC.getDoubles();
	}

	public Vector3d fromBuffer( DoubleBuffer buf, int pos )
	{
		return new Vector3d().set(pos * 3, buf);
	}
	public void toBuffer( Vector3d v, DoubleBuffer buf, int pos )
	{
		v.get(pos * 3, buf);
	}

	@Benchmark
	public void benchmark()
	{
		for(int i = 0; i < size; i++) {
			final Vector3d a = fromBuffer( bufferA, i );
			final Vector3d b = fromBuffer( bufferB, i );
			final Vector3d c = new Vector3d().set(a.add(b));
			toBuffer(c, bufferC, i);
		}
	}

	public static void main( final String... args ) throws RunnerException
	{
		final Options opt = new OptionsBuilder()
				.include( JOMLVec3dAddBenchmark.class.getSimpleName() )
				.forks( 0 )
				.warmupIterations( 10 )
				.measurementIterations( 20 )
				.warmupTime( TimeValue.milliseconds( 500 ) )
				.measurementTime( TimeValue.milliseconds( 500 ) )
				.build();
		new Runner( opt ).run();
	}
}
