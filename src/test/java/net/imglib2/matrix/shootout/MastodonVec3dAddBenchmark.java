package net.imglib2.matrix.shootout;

import net.imglib2.matrix.shootout.mastodon.Vector3d;
import net.imglib2.matrix.shootout.mastodon.Vector3dPool;
import net.imglib2.matrix.shootout.ojalgo.OjAlgoVectorView;
import org.mastodon.collection.RefList;
import org.mastodon.collection.ref.RefArrayList;
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

	private Vector3dPool poolA;

	private Vector3dPool poolB;

	private Vector3dPool poolC;

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
		poolA = new Vector3dPool( bufA.getBytes() );
		poolB = new Vector3dPool( bufB.getBytes() );
		poolC = new Vector3dPool( bufC.getBytes() );
		A = poolA.asList();
		B = poolB.asList();
		C = poolC.asList();
	}

	@Benchmark
	public void benchmark()
	{
		final Vector3d ref1 = A.createRef();
		final Vector3d ref2 = A.createRef();
		final Vector3d ref3 = A.createRef();

		final RefList< Vector3d > as = new RefArrayList<>( poolA );
		final RefList< Vector3d > bs = new RefArrayList<>( poolB );

		final int size = A.size();
		for ( int i = 0; i < size; ++i )
		{
			as.add( A.get( i, ref1 ) );
			bs.add( B.get( i, ref2 ) );
		}
		for ( int i = 0; i < size; ++i )
			C.get( i, ref3 ).add( as.get( i, ref1 ), bs.get( i, ref2 ) );
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
