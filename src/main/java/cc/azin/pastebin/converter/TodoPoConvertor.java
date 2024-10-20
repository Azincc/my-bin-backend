package cc.azin.pastebin.converter;

public interface TodoPoConvertor<S, T> {
  T convert(S source);
}
