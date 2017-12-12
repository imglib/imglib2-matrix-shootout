package net.imglib2.matrix.shootout;

import net.imglib2.matrix.shootout.mastodon.Vector3d;
import net.imglib2.matrix.shootout.mastodon.Vector3dPool;
import net.imglib2.matrix.shootout.ojalgo.OjAlgoVectorView;
import org.mastodon.collection.RefList;
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
public class MastodonVec3dAddBenchmark
{
	private static final int size = 1000;

	private final Vec3dBuffer bufA;

	private final Vec3dBuffer bufB;

	private final Vec3dBuffer bufC;

	private RefList< Vector3d > A;

	private RefList< Vector3d > B;

	private RefList< Vector3d > C;

	public MastodonVec3dAddBenchmark()
	{
		bufA = new Vec3dBuffer( size );
		bufB = new Vec3dBuffer( size );
		bufC = new Vec3dBuffer( size );
		bufA.fillRandom();
		bufB.fillRandom();
	}


	@Setup
	public void setup()
	{
		A = new Vector3dPool( bufA.getBytes() ).asList();
		B = new Vector3dPool( bufB.getBytes() ).asList();
		C = new Vector3dPool( bufC.getBytes() ).asList();
	}

	@Benchmark
	public void benchmark()
	{
		final Vector3d ref1 = A.createRef();
		final Vector3d ref2 = A.createRef();
		final Vector3d ref3 = A.createRef();
		final int size = A.size();
		for ( int i = 0; i < size; ++i )
			C.get( i, ref3 ).add( A.get( i, ref1 ), B.get( i, ref2 ) );
	}

	public static void main( final String... args ) throws RunnerException
	{
		final Options opt = new OptionsBuilder()
				.include( MastodonVec3dAddBenchmark.class.getSimpleName() )
				.forks( 0 )
				.warmupIterations( 10 )
				.measurementIterations( 20 )
				.warmupTime( TimeValue.milliseconds( 500 ) )
				.measurementTime( TimeValue.milliseconds( 500 ) )
				.build();
		new Runner( opt ).run();
	}
}
