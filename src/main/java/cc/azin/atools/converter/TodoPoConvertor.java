package cc.azin.atools.converter;

public interface TodoPoConvertor<S, T> {
  T convert(S source);
}
