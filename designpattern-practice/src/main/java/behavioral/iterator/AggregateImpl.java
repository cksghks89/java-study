package behavioral.iterator;

public class AggregateImpl<E> implements Aggregate<E> {
	private E[] datas = null;

	public AggregateImpl(E[] datas) {
		this.datas = datas;
	}

	@Override
	public Iterator<E> createIterator() {
		return new IteratorImpl();
	}

	
	// Iterator 는 한 번만 순회하고 끝내야 한다 (재사용 x)
	private class IteratorImpl implements Iterator<E> {
		int index = 0;

		@Override
		public E next() {
			return datas[index++];
		}

		@Override
		public boolean hasNext() {
			return index < datas.length;
		}
	}
}
