package net.imglib2.matrix.shootout.mastodon;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.function.IntSupplier;

import org.mastodon.RefPool;
import org.mastodon.collection.RefList;

/**
 * TODO: should move to mastodon-collection
 *
 * @author Tobias Pietzsch
 */
public class PoolAsList< O > implements RefList< O >
{
	private final RefPool< O > pool;

	private final IntSupplier size;

	public PoolAsList( final RefPool< O > pool, final IntSupplier size )
	{
		this.pool = pool;
		this.size = size;
	}

	@Override
	public O createRef()
	{
		return pool.createRef();
	}

	@Override
	public void releaseRef( final O obj )
	{
		pool.releaseRef( obj );
	}

	@Override
	public int size()
	{
		return size.getAsInt();
	}

	@Override
	public boolean isEmpty()
	{
		return size() == 0;
	}

	@Override
	public O get( final int index )
	{
		return get( index, createRef() );
	}

	@Override
	public O get( final int index, final O obj )
	{
		return pool.getObject( index, obj );
	}

	@Override
	public Iterator< O > iterator()
	{
		return new Iterator< O >()
		{
			int i = 0;

			final O obj = createRef();

			@Override
			public boolean hasNext()
			{
				return i < size();
			}

			@Override
			public O next()
			{
				return get( i++, obj );
			}

			@Override
			public void remove()
			{
				throw new UnsupportedOperationException();
			}
		};
	}

	// === not (yet) implemented operations ===========================

	@Override
	public boolean contains( final Object o )
	{
		throw new UnsupportedOperationException( "TODO ???" );
	}

	@Override
	public boolean containsAll( final Collection< ? > c )
	{
		throw new UnsupportedOperationException( "TODO ???" );
	}

	@Override
	public Object[] toArray()
	{
		throw new UnsupportedOperationException( "TODO ???" );
	}

	@Override
	public < T > T[] toArray( final T[] a )
	{
		throw new UnsupportedOperationException( "TODO ???" );
	}

	@Override
	public int indexOf( final Object o )
	{
		throw new UnsupportedOperationException( "TODO ???" );
	}

	@Override
	public int lastIndexOf( final Object o )
	{
		throw new UnsupportedOperationException( "TODO ???" );
	}

	@Override
	public ListIterator< O > listIterator()
	{
		throw new UnsupportedOperationException( "TODO ???" );
	}

	@Override
	public ListIterator< O > listIterator( final int index )
	{
		throw new UnsupportedOperationException( "TODO ???" );
	}

	@Override
	public List< O > subList( final int fromIndex, final int toIndex )
	{
		throw new UnsupportedOperationException( "TODO ???" );
	}

	// === unsupported operations ===========================

	@Override
	public void add( final int index, final O element )
	{
		throw new UnsupportedOperationException( "list is unmodifiable" );
	}

	@Override
	public O remove( final int index )
	{
		throw new UnsupportedOperationException( "list is unmodifiable" );
	}

	@Override
	public boolean add( final O e )
	{
		throw new UnsupportedOperationException( "list is unmodifiable" );
	}

	@Override
	public boolean remove( final Object o )
	{
		throw new UnsupportedOperationException( "list is unmodifiable" );
	}

	@Override
	public boolean addAll( final Collection< ? extends O > c )
	{
		throw new UnsupportedOperationException( "list is unmodifiable" );
	}

	@Override
	public boolean removeAll( final Collection< ? > c )
	{
		throw new UnsupportedOperationException( "list is unmodifiable" );
	}

	@Override
	public boolean retainAll( final Collection< ? > c )
	{
		throw new UnsupportedOperationException( "list is unmodifiable" );
	}

	@Override
	public void clear()
	{
		throw new UnsupportedOperationException( "list is unmodifiable" );
	}

	@Override
	public boolean addAll( final int index, final Collection< ? extends O > c )
	{
		throw new UnsupportedOperationException( "list is unmodifiable" );
	}

	@Override
	public O remove( final int index, final O obj )
	{
		throw new UnsupportedOperationException( "list is unmodifiable" );
	}

	@Override
	public void shuffle( final Random rand )
	{
		throw new UnsupportedOperationException( "list is unmodifiable" );
	}

	@Override
	public void sort( final Comparator< ? super O > comparator )
	{
		throw new UnsupportedOperationException( "list is unmodifiable" );
	}

	@Override
	public O set( final int index, final O element )
	{
		throw new UnsupportedOperationException( "list is unmodifiable" );
	}

	@Override
	public O set( final int index, final O obj, final O replacedObj )
	{
		throw new UnsupportedOperationException( "list is unmodifiable" );
	}

	@Override
	public void swap( final int i, final int j )
	{
		throw new UnsupportedOperationException( "list is unmodifiable" );
	}
}