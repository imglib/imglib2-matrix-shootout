package net.imglib2.matrix.shootout;

import net.imglib2.matrix.shootout.mastodon.Vector3d;
import net.imglib2.matrix.shootout.mastodon.Vector3dPool;
import net.imglib2.matrix.shootout.mastodon.Vector3f;
import net.imglib2.matrix.shootout.mastodon.Vector3fPool;
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
public class ImageJMeshVec3fAddBenchmark
{
	private static final int size = 1000;

	private final Vec3fBuffer bufA;

	private final Vec3fBuffer bufB;

	private final Vec3fBuffer bufC;

	private RefList< Vector3f > A;

	private RefList< Vector3f > B;

	private RefList< Vector3f > C;

	public ImageJMeshVec3fAddBenchmark()
	{
		bufA = new Vec3fBuffer( size );
		bufB = new Vec3fBuffer( size );
		bufC = new Vec3fBuffer( size );
		bufA.fillRandom();
		bufB.fillRandom();
	}


	@Setup
	public void setup()
	{
		A = new Vector3fPool( bufA.getBytes() ).asList();
		B = new Vector3fPool( bufB.getBytes() ).asList();
		C = new Vector3fPool( bufC.getBytes() ).asList();
	}

	@Benchmark
	public void benchmark()
	{
		final Vector3f ref1 = A.createRef();
		final Vector3f ref2 = A.createRef();
		final Vector3f ref3 = A.createRef();
		for ( int i = 0; i < size; ++i )
			C.get( i, ref3 ).add( A.get( i, ref1 ), B.get( i, ref2 ) );
	}

	public static void main( final String... args ) throws RunnerException
	{
		final Options opt = new OptionsBuilder()
				.include( ImageJMeshVec3fAddBenchmark.class.getSimpleName() )
				.forks( 0 )
				.warmupIterations( 10 )
				.measurementIterations( 20 )
				.warmupTime( TimeValue.milliseconds( 500 ) )
				.measurementTime( TimeValue.milliseconds( 500 ) )
				.build();
		new Runner( opt ).run();
	}
}
