package net.imglib2.matrix.shootout;

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

@State( Scope.Benchmark )
public class OjAlgoVec3dAddBenchmark
{
	private static final int size = 1000;

	private final Vec3dBuffer bufA;

	private final Vec3dBuffer bufB;

	private final Vec3dBuffer bufC;

	public OjAlgoVec3dAddBenchmark()
	{
		bufA = new Vec3dBuffer( size );
		bufB = new Vec3dBuffer( size );
		bufC = new Vec3dBuffer( size );
		bufA.fillRandom();
		bufB.fillRandom();
	}

	private OjAlgoVectorView a;

	private OjAlgoVectorView b;

	private OjAlgoVectorView c;

	@Setup
	public void setup()
	{
		a = new OjAlgoVectorView( bufA.getDoubles(), Vec3dBuffer.vlen );
		b = new OjAlgoVectorView( bufB.getDoubles(), Vec3dBuffer.vlen );
		c = new OjAlgoVectorView( bufC.getDoubles(), Vec3dBuffer.vlen );
	}

	@Benchmark
	public void benchmark()
	{
		final int numVectors = a.numVectors();
		final int nDim = ( int ) a.count();
		for ( int i = 0; i < numVectors; ++i )
		{
			a.setIndex( i );
			b.setIndex( i );
			c.setIndex( i );
//			c.add( a, b );
			for ( int k = 0; k < nDim; ++k )
				c.set( k, a.doubleValue( k ) + b.doubleValue( k ) );
		}
	}

	public static void main( final String... args ) throws RunnerException
	{
		final Options opt = new OptionsBuilder()
				.include( OjAlgoVec3dAddBenchmark.class.getSimpleName() )
				.forks( 0 )
				.warmupIterations( 10 )
				.measurementIterations( 20 )
				.warmupTime( TimeValue.milliseconds( 500 ) )
				.measurementTime( TimeValue.milliseconds( 500 ) )
				.build();
		new Runner( opt ).run();
	}
}
