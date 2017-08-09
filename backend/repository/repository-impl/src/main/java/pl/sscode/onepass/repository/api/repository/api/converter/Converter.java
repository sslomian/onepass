package pl.sscode.onepass.repository.api.repository.api.converter;

/**
 * Created by sscode on 2017-06-15.
 */
public interface Converter<K, V> {
    V convertTo(K src);

    K convertFrom(V src);
}
