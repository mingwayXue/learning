/**
 * Created by mingway on Date:2018-12-19 17:19.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
public class HashMap<K, V> implements Map<K, V> {

	private static int defaultLenfth = 16;	//初始化容量

	private static double defaultLoader = 0.75;	//加载因子

	private Entry[] table = null;

	private int size = 0;

	public HashMap() {
		this(defaultLenfth, defaultLoader);
	}

	public HashMap(int length, double loader) {
		defaultLenfth = length;
		defaultLoader = loader;
		table = new Entry[defaultLenfth];
	}


	@Override
	public V put(K k, V v) {
		size++;
		int index = hash(k);
		Entry<K, V> entry = table[index];
		if (entry == null) {
			table[index] = newEntry(k, v, null);
		} else {
			table[index] = newEntry(k, v, entry);
		}

		return (V) table[index].getValue();
	}

	private Entry newEntry(K k, V v, Entry<K, V> next) {
		return new Entry(k, v, next);
	}

	//自行创建的哈希函数
	private int hash(K k) {
		int m = defaultLenfth;
		int i = k.hashCode() % 10;
		return i > 0 ? i : -i;
	}

	@Override
	public V get(K k) {
		int index = hash(k);
		if (table[index] == null) {
			return null;
		}

		return (V) find(k, table[index]);
	}

	//查询
	private V find(K k, Entry<K, V> entry) {
		if (k == entry.getKey() || k.equals(entry.getKey())) {
			return entry.getValue();
		} else {
			if (entry.next != null) {
				return find(k, entry.next);
			}
		}
		return null;
	}

	@Override
	public int size() {
		return this.size;
	}

	//内部类Entry，实现Map.Entry接口
	class Entry<K, V> implements Map.Entry<K, V> {

		K k;
		V v;
		Entry<K, V> next;

		public Entry(K k, V v, Entry<K, V> next) {
			this.k = k;
			this.v = v;
			this.next = next;
		}

		@Override
		public K getKey() {
			return k;
		}

		@Override
		public V getValue() {
			return v;
		}
	}
}
