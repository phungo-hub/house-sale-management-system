package readwriteable;

import java.util.List;

public interface ReadWriteable<T> {
    List<T> read(String file);
    void save(String file, List<T> list);
}
