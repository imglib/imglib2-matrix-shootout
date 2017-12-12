package net.imglib2.matrix.shootout;

import cleargl.GLVector;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
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
public class GlearGLVec3fAddBenchmark
{
	private static final int size = 1000;

	private final Vec3fBuffer bufA;

	private final Vec3fBuffer bufB;

	private final Vec3fBuffer bufC;

	public GlearGLVec3fAddBenchmark()
	{
		bufA = new Vec3fBuffer( size );
		bufB = new Vec3fBuffer( size );
		bufC = new Vec3fBuffer( size );
		bufA.fillRandom();
		bufB.fillRandom();
	}

	private FloatBuffer bufferA;

	private FloatBuffer bufferB;

	private ByteBuffer bufferC;

	@Setup
	public void setup()
	{
		bufferA = bufA.getFloats();
		bufferB = bufB.getFloats();
		bufferC = bufC.getBytes();
	}

	@Benchmark
	public void benchmark()
	{
		bufferA.rewind();
		bufferB.rewind();
		bufferC.rewind();
		for(int i = 0; i < size; i++) {
			final GLVector a = new GLVector(0.0f, 0.0f, 0.0f);
			final GLVector b = new GLVector(0.0f, 0.0f, 0.0f);
			final GLVector c = new GLVector(0.0f, 0.0f, 0.0f);

			bufferA.get(a.toFloatArray(), 0, 3);
			bufferB.get(b.toFloatArray(), 0, 3);

			c.plusAssign(a.plus(b));
			c.put(bufferC);
		}
	}

	public static void main( final String... args ) throws RunnerException
	{
		final Options opt = new OptionsBuilder()
				.include( GlearGLVec3fAddBenchmark.class.getSimpleName() )
				.forks( 0 )
				.warmupIterations( 10 )
				.measurementIterations( 20 )
				.warmupTime( TimeValue.milliseconds( 500 ) )
				.measurementTime( TimeValue.milliseconds( 500 ) )
				.build();
		new Runner( opt ).run();
	}
}
