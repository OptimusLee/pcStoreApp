package cuanlee.pcstore_application.services.Impl;

import java.util.List;

import cuanlee.pcstore_application.model.RAM;
import cuanlee.pcstore_application.repository.RestAPI;
import cuanlee.pcstore_application.repository.rest.RestRAMAPI;
import cuanlee.pcstore_application.services.RAMService;

/**
 * Created by CuanL on 30/08/2016.
 */
public class RAMServiceImpl implements RAMService {

    final RestAPI<RAM,Long> rest = new RestRAMAPI();

    @Override
    public RAM findById(Long aLong) {
        return rest.get(aLong);
    }

    @Override
    public String save(RAM entity) {
        return rest.post(entity);
    }

    @Override
    public String update(RAM entity) {
        return rest.put(entity);
    }

    @Override
    public String delete(RAM entity) {
        return rest.delete(entity);
    }

    @Override
    public List<RAM> findAll() {
        return rest.getAll();
    }
}
