package net.imglib2.matrix.shootout.mastodon;

import org.mastodon.pool.PoolObjectLayout;

public class Vector3dLayout extends PoolObjectLayout
{
	final DoubleArrayField position = doubleArrayField( 3 );
}
