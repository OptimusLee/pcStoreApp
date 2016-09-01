package cuanlee.pcstore_application.repository;

import java.util.List;

/**
 * Created by CuanL on 29/08/2016.
 */
public interface RestAPI <S, ID>{
    S get(ID id);

    String post(S entity);

    String put(S entity);

    String delete(S entity);

    List<S> getAll();
}
