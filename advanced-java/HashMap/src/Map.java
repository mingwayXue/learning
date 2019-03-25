/**
 * Created by mingway on Date:2018-12-19 17:13.
 * 修改记录
 * 修改后版本:     修改人：  修改日期:     修改内容:
 */
public interface Map<K, V> {

	V put(K k, V v);

	V get(K k);

	int size();

	interface Entry<K, V> {
		K getKey();

		V getValue();
	}
}
