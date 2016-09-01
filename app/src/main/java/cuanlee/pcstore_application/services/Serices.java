package cuanlee.pcstore_application.services;

import java.util.List;

/**
 * Created by CuanL on 30/08/2016.
 */
public interface Serices<S, ID> {
    public S findById(ID id);

    public String save(S entity);

    public String update(S entity);

    public String delete(S entity);

    public List<S> findAll();
}
