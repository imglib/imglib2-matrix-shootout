package net.imglib2.matrix.shootout.mastodon;

import org.mastodon.pool.PoolObjectLayout;

/**
 * The layout for storing a vertex in memory
 *
 * @author Tobias Pietzsch (MPI-CBG)
 * @author Kyle Harrington (University of Idaho, Moscow)
 */
public class Vector3fLayout extends PoolObjectLayout
{
	final FloatArrayField position = floatArrayField( 3 );
}