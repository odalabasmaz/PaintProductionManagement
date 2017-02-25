package tr.com.ppm.desktop.service;

import org.springframework.stereotype.Service;
import tr.com.ppm.desktop.model.common.Quantity;

/**
 * @author ykarabalkan.
 */
@Service
public class QuantityService extends BaseService {
	@Override
	protected Class getEntityClass() {
		return Quantity.class;
	}
}
