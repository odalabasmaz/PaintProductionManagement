package tr.com.ppm.desktop.service;

import org.springframework.stereotype.Service;
import tr.com.ppm.desktop.model.material.PaintType;

/**
 * @author Orhun Dalabasmaz
 */
@Service
public class PaintTypeService extends BaseService<PaintType> {

	@Override
	protected Class getEntityClass() {
		return PaintType.class;
	}
}
