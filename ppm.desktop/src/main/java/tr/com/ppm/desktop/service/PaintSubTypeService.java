package tr.com.ppm.desktop.service;

import org.springframework.stereotype.Service;
import tr.com.ppm.desktop.model.material.PaintSubType;

/**
 * @author Orhun Dalabasmaz
 */
@Service
public class PaintSubTypeService extends BaseService<PaintSubType> {

	@Override
	protected Class getEntityClass() {
		return PaintSubType.class;
	}
}
